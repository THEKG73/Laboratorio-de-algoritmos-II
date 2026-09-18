//Nodo para el árbol de decisión del Código Morse.
class NodoMorse(var valor: Char? = null) {
    var izq: NodoMorse? = null
    var der: NodoMorse? = null
}

//Implementación del TAD Código Morse modelado como un árbol binario de decisión
class CodigoMorseAD {

    // La raíz del árbol inicia con un carácter vacío (null)
    private val raiz = NodoMorse()

    init {
        val alfabeto = mapOf(
            'a' to ".-", 'b' to "-...", 'c' to "-.-.", 'd' to "-..", 'e' to ".",
            'f' to "..-.", 'g' to "--.", 'h' to "....", 'i' to "..", 'j' to ".---",
            'k' to "-.-", 'l' to ".-..", 'm' to "--", 'n' to "-.", 'o' to "---",
            'p' to ".--.", 'q' to "--.-", 'r' to ".-.", 's' to "...", 't' to "-",
            'u' to "..-", 'v' to "...-", 'w' to ".--", 'x' to "-..-", 'y' to "-.--",
            'z' to "--.."
        )

        // Se construye el árbol de decisión mapeando cada letra
        for ((letra, codigo) in alfabeto) {
            insertar(letra, codigo)
        }
    }

    // Construye los caminos del árbol basándose en la secuencia de puntos y rayas
    private fun insertar(letra: Char, codigo: String) {
        var actual = raiz
        for (simbolo in codigo) {
            if (simbolo == '.') {
                if (actual.izq == null) actual.izq = NodoMorse()
                actual = actual.izq!!
            } else if (simbolo == '-') {
                if (actual.der == null) actual.der = NodoMorse()
                actual = actual.der!!
            }
        }
        actual.valor = letra
    }

    /**
     * Decodifica una secuencia individual en Código Morse para obtener la letra.
     * Retorna la letra en formato String, o null si la secuencia es inválida.
     */
    fun decodificarLetra(secuencia: String): String? {
        var actual = raiz
        for (simbolo in secuencia) {
            if (simbolo == '.') {
                actual = actual.izq ?: return null
            } else if (simbolo == '-') {
                actual = actual.der ?: return null
            } else {
                return null
            }
        }
        return actual.valor?.toString()
    }

    /**
     * Decodifica una frase completa
     * Las letras se separan por " " (espacio) y las palabras por "/"
     * Retorna la traducción, o null si alguna letra es inválida
     */
    fun decodificarMensaje(frase: String): String? {
        val palabras = frase.split("/")
        val resultado = StringBuilder()

        for ((indexPalabra, palabra) in palabras.withIndex()) {
            val letras = palabra.split(" ")
            
            for (codigo in letras) {
                if (codigo.isEmpty()) continue
                
                val letraDecodificada = decodificarLetra(codigo)
                if (letraDecodificada != null) {
                    resultado.append(letraDecodificada)
                } else {
                    return null
                }
            }
            
            // Agregar el espacio entre palabras traducidas
            if (indexPalabra < palabras.size - 1) {
                resultado.append(" ")
            }
        }
        return resultado.toString()
    }
}