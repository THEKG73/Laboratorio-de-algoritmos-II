/**
 * Implementación del Tipo Abstraído de Datos (TAD) PMLI (Palabras que Mencionan la Misma Inicial).
 *
 * Encapsula y gestiona un conjunto de palabras que comienzan con un carácter alfabético minúsculo específico [letra].
 *
 * @property letra Carácter alfabético en minúscula que define la inicial de las palabras de esta colección.
 * @property palabras Estructura interna de datos que almacena las palabras mediante una instancia de [ConjuntoPalabras].
 *
 * **Precondición:** [letra] debe ser un carácter alfabético (`isLetter()`) y estar en minúscula (`isLowerCase()`). Además, todas las palabras iniciales deben ser válidas y comenzar con [letra].
 * **Postcondición:** Se crea e inicializa la estructura [TAD_PMLI] asociada a la [letra] especificada.
 */
 class TAD_PMLI (val letra: Char) {
    val palabras: ConjuntoPalabras = ConjuntoPalabras()
    init {
        require(letra in 'a'..'z' || letra == 'ñ') { "La letra debe ser un carácter alfabético en minuscula." }
    }
    /**
     * Agrega una palabra al conjunto de la inicial correspondiente.
     *
     * @param palabra Cadena de texto a insertar.
     * @throws IllegalArgumentException Si [palabra] no es válida o no inicia con [letra].
     *
     * **Precondición:** [esPalabraValida](palabra) debe ser `true` y `palabra.startsWith(letra)` debe ser `true`.
     * **Postcondición:** [palabra] queda agregada en el conjunto [palabras] si no existía previamente.
     */
    fun agregarPalabra(palabra: String) {
        require(esPalabraValida(palabra)) { "La palabra debe ser valida." }
        require(palabra.startsWith(letra)) { "La palabra debe empezar con la letra ${letra}." }
        palabras.agregar(palabra)
    }

    /**
     * Elimina una palabra del conjunto si esta se encuentra presente.
     *
     * @param palabra Cadena de texto a remover.
     * @throws IllegalArgumentException Si [palabra] no es válida o no inicia con [letra].
     *
     * **Precondición:** [esPalabraValida](palabra) debe ser `true` y `palabra.startsWith(letra)` debe ser `true`.
     * **Postcondición:** Si [palabra] estaba presente en [palabras], es removida; en caso contrario, la estructura no cambia.
     */
    fun eliminarPalabra(palabra: String) {
        require(esPalabraValida(palabra)) { "La palabra debe ser valida." }
        require(palabra.startsWith(letra)) { "La palabra debe empezar con la letra ${letra}." }
        palabras.eliminar(palabra)
    }

    /**
     * Busca y determina si una palabra pertenece a este conjunto.
     *
     * @param palabra Cadena de texto que se desea consultar.
     * @return `true` si [palabra] pertenece al conjunto de este conjunto; `false` en caso contrario.
     * @throws IllegalArgumentException Si [palabra] no es válida o no inicia con [letra].
     *
     * **Precondición:** [esPalabraValida](palabra) debe ser `true` y `palabra.startsWith(letra)` debe ser `true`.
     * **Postcondición:** Devuelve el resultado de la búsqueda sin alterar los elementos del conjunto.
     */
    fun buscarPalabra(palabra: String): Boolean {
        require(esPalabraValida(palabra)) { "La palabra debe ser valida." }
        require(palabra.startsWith(letra)) { "La palabra debe empezar con la letra ${letra}." }
        return palabras.contiene(palabra)
    }

    /**
     * Muestra en la salida estándar todas las palabras almacenadas ordenadas lexicográficamente.
     *
     * **Precondición:** Ninguna.
     * **Postcondición:** Imprime las palabras en orden alfabético por consola sin modificar el estado interno del conjunto.
     */
    fun mostrarPalabras(){
        val listaPalabras = palabras.obtenerTodas()
        listaPalabras.sort() // Ordenamiento lexicográfico
        for (palabra in listaPalabras) {
            println(palabra)
        }
    }
}