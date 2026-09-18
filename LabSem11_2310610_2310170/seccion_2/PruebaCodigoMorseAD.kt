fun main(args: Array<String>) {
    // Verificar que se haya pasado un argumento
    if (args.isEmpty()) {
        println("Error, codigo Morse no valido")
        return
    }

    val codigoMorseEntrada = args[0]
    val ayudanteMorse = CodigoMorseAD()
    
    val mensajeTraducido = ayudanteMorse.decodificarMensaje(codigoMorseEntrada)

    if (mensajeTraducido != null) {
        println(mensajeTraducido)
    } else {
        println("Error, codigo Morse no valido")
    }
}