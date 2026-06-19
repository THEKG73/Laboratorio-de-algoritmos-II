import java.io.File


/**
 * Punto de entrada principal de la aplicación.
 *
 * Se encarga de validar los argumentos de la línea de comandos, inicializar el gestor de la escena
 * (`SceneManager`), cargar los elementos espaciales iniciales desde un archivo y arrancar el menú interactivo.
 *
 * El programa requiere exactamente un argumento: la cantidad inicial de movimientos permitidos.
 * 
 * Precondición: 
 * - Debe recibir un array de strings con un solo elemento, dicho elemento tiene que ser un entero positivo.
 * 
 * Postcondición: 
 * - La función no devuelve nada, solo termina el programa si algo ocurrió mal o llama a la función menú.
 *
 * @param args Arreglo de argumentos pasados por la línea de comandos. Se espera un único string convertible a entero.
 */
fun main (args:Array<String>){
    if (args.size != 1){
        println("Se debe dar solo la cantidad de movimientos")
        System.exit(-1)
    }
    var mov = args[0].toInt()
    if (mov <= 0){
        println("Se debe dar una cantidad de movimientos mayor a 0")
        System.exit(-1)
    }
    var scene = SceneManager(mov)
    cargarObjetosDesdeArchivo(scene)
    menu(mov,scene)
}

/**
 * Controla el ciclo principal de la aplicación mediante un menú interactivo en consola de forma recursiva.
 *
 * Permite al usuario interactuar con la escena a través de 5 opciones principales:
 * 1. Desplazar la cámara de visualización (consume un movimiento).
 * 2. Insertar un nuevo elemento espacial (consume un movimiento).
 * 3. Reiniciar el mapa al estado inicial (mantiene los movimientos actuales).
 * 4. Exportar el histórico a un archivo de texto (`historico.txt`).
 * 5. Salir del programa de manera ordenada.
 * 
 * Precondición: 
 * - mov2 debe ser un número entero y scene2 debe ser un SceneManager
 * 
 * Postcondición: 
 * - La función no devuelve nada, solo se llama a ella de manera recursiva.
 *
 * @param mov2 Cantidad actual de movimientos disponibles para el usuario.
 * @param scene2 Instancia actual de [SceneManager] que gestiona los elementos y la lógica del mapa.
 */
fun menu(mov2: Int, scene2: SceneManager){
    var mov = mov2
    var scene = scene2
    println("\n\nOpciones\n")
    println("1. Desplazar cámara")
    println("2. Insertar elemento")
    println("3. Rieniciar el mapa")
    println("4. Exportar histórico")
    println("5. Salir\n")

    println("Ingrese el número de la opción deseada: ")
    var option = readln().toInt()
    println("")

    when (option){
        1 -> {
            if (mov <= 0){
                println("No tiene movimientos disponibles")
                menu(mov,scene)
            } else {
                println("Ingrese el centro de la cámara en X: ")
                var camX = readln().toInt()
                println("Ingrese el centro de la cámara en Y: ")
                var camY = readln().toInt()
                if (camX !in 0..99 || camY !in 0..99){
                    println("Debe ingresar X y Y en el rango [0,99]")
                    menu(mov,scene)
                } else {
                    scene.desplazarCamara(camX,camY)
                    mov -= 1
                    menu(mov,scene)
                }
            }
        }

        2 -> {
            if (mov <= 0){
                println("No tiene movimientos disponibles")
                menu(mov,scene)
            } else {
                println("Ingrese la coordenada del elemento en X: ")
                val posX = readln().toInt()
                println("Ingrese la coordenada del elemento en Y: ")
                val posY = readln().toInt()
                println("Ingrese el valor de z: ")
                val z = readln().toDouble()
                val id = scene.maxIdAsignado + 1

                if (posX !in 0..99 || posY !in 0..99 || z !in 0.0..10.9){
                    println("Debe ingresar X y Y en el rango [0,99] y z en [0.0,10.9]")
                    menu(mov,scene)
                } else {
                    val objAct = SpatialElement(id, posX, posY, z)
                    if (!scene.insertarElementoGlobal(objAct)) {
                        println("Ocurrió un error al insertar el objeto")
                        menu(mov,scene)
                    } else {
                        mov -= 1
                        menu(mov,scene)
                    }
                }
            }
        }

        3 -> {
            scene=SceneManager(mov)
            cargarObjetosDesdeArchivo(scene)
            menu(mov,scene)
        }

        4 -> {
            val file = File("historico.txt")
            file.writeText("")
            for (i in scene.historial){
                val text = i.filter { it != -1 }.joinToString(separator = ", ")
                if (text != ""){
                    if (file.readText() == "") {
                        file.appendText(text)
                    } else {
                        file.appendText("\n\n"+text)
                    }
                }
            }
            menu(mov,scene)
        }

        5 -> {
            System.exit(0)
        }

        else -> {
            println("Opción inválida, intente de nuevo \n")
            menu(mov,scene)
        }
    }
}

/**
 * Lee la información de un archivo de texto llamado `objetos.txt` para poblar la escena con elementos espaciales iniciales.
 *
 * El formato esperado del archivo por cada línea no vacía es: `id, posX, posY, z`.
 * 
 * Se verifica que cada linea tenga el formato esperado y después se inserta en la escena.
 *
 * Si la inserción de algún elemento falla, el programa imprimirá un error por consola y finalizará inmediatamente su ejecución.
 * 
 * Precondición: 
 * - El parámetro debe ser un SceneManager, el archivo "objetos.txt" debe existir em la carpeta 
 * - y objetos.txt debe tener líneas con el formato `id, posX, posY, z`.
 * 
 * Postcondición: 
 * - Se devuelve un SceneManager con todos los SpacialElement encontrados en el archivo objetos.txt
 *
 * @param scene Instancia de [SceneManager] donde se cargarán los elementos leídos.
 * @return La misma instancia de [SceneManager] modificada con los nuevos objetos añadidos.
 */
fun cargarObjetosDesdeArchivo(scene: SceneManager): SceneManager {
    val file = File("objetos.txt")
    
    file.forEachLine { line ->
        // Ignoramos líneas vacías
        if (line.isNotBlank()) {
            
            // Separamos los elementos por coma
            val partes = line.split(",")
            
            if (partes.size != 4){
                println("Cada línea debe tener la forma `ID,posX,posY,z`")
                System.exit(-1)
            }

            // Creamos el objeto con los datos de esta línea específica
            val objetoActual = SpatialElement(partes[0].trim().toInt(),partes[1].trim().toInt(), partes[2].trim().toInt(),partes[3].trim().toDouble())
            
            // Verificamos que se puede agregar correctamente
            if (!(scene.insertarElementoGlobal(objetoActual))){
                println("Error: no se pudo agregar el elemento ID: ${partes[0].trim().toInt()}")
                System.exit(-1)
            }
        }
    }
    return scene
}