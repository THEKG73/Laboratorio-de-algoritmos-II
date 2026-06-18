import java.io.File

fun main (args:<String>){
    if (args.size != 1){
        println("Se debe dar solo la cantidad de movimientos")
        System.exit(-1)
    }
    if (args[0].trim().toInt() <= 0){
        println("Se debe dar una cantidad de movimientos mayor a 0")
        System.exit(-1)
    }
    var scene = SceneManager(args[0].trim().toInt())
    cargarObjetosDesdeArchivo(scene)
    menu(args[0].trim().toInt())
}

fun menu(mov: Int, scene: SceneManager): SceneManager{
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
            } else {
                println("Ingrese el centro de la cámara en X: ")
                var camX = readln().toInt()
                println("Ingrese el centro de la cámara en Y: ")
                var camY = readln().toInt()
                if (camX !in 0..99 || camY !in 0..99){
                    println("Debe ingresar X y Y en el rango [0,99]")
                    menu(scene,mov)
                } else {
                    scene.desplazarCamara(camX,camY)
                    mov -= 1
                }
            }
        }

        2 -> {
            if (mov <= 0){
                println("No tiene movimientos disponibles")
            } else {
                println("Ingrese la coordenada del elemento en X: ")
                val posX = readln().totInt()
                println("Ingrese la coordenada del elemento en Y: ")
                val posY = readln().totInt()
                println("Ingrese el valor de z: ")
                val z = readln().toDouble()
                val id = scene.maxIdAsignado + 1

                if (posX !in 0..99 || posY !in 0..99 || z !in 0.0..10.9){
                    println("Debe ingresar X y Y en el rango [0,99] y z en [0.0,10.9]")
                    menu(scene,mov)
                } else {
                    val objAct = SpatialElement(id, posX, posY, z)
                    if (!scene.insertarElementoGlobal(objAct)) {
                        println("Ocurrió un error al insertar el objeto")
                        menu(mov,scene)
                    } else {
                        mov -= 1
                    }
                }
            }
        }

        3 -> {
            cargarObjetosDesdeArchivo(scene)
        }

        4 -> {
            val file = File("historico.txt")
            file.writeText("")
            for (i in scene.historial){
                val text = i.filter { it != "-1" }.joinToString(separator = ", ")
                if (text != "" && text !=null){
                    file.appendText(text+"\n\n")
                }
            }
        }

        5 -> {
            System.exit(0)
        }

        default -> {
            println("Opción inválida, intente de nuevo \n")
            menu(mov,scene)
        }
    }

    return scene
}

fun cargarObjetosDesdeArchivo(scene: SceneManager): SceneManager {
    val file = File("objetos.txt")
    
    file.forEachLine { line ->
        // Ignoramos líneas vacías
        if (line.isNotBlank()) {
            
            // Separamos los elementos por coma
            val partes = line.split(",")
            
            // Creamos el objeto con los datos de esta línea específica
            val objetoActual = SpatialElement(partes[0].trim().toInt(),partes[1].trim().toInt(), partes[2].trim().toInt(),partes[3].trim().toDouble())
            
            // Verificamos que se puede agregar correctamente
            if !(insertarElementoGlobal(objetoActual)){
                println("Error: no se pudo agregar el elemento ID: $partes[0].trim().toInt().toString()")
                System.exit(-1)
            }
        }
    }
}