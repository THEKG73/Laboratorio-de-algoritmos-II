import java.io.File

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
                    file.appendText(text+"\n\n")
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
            if (!(scene.insertarElementoGlobal(objetoActual))){
                println("Error: no se pudo agregar el elemento ID: $partes[0].trim().toInt().toString()")
                System.exit(-1)
            }
        }
    }
    
    /*for (fila in scene.historial.indices) {
        for (columna in scene.historial[fila].indices) {
            scene.historial[fila][columna] = -1
        }
    }*/
    return scene
}