/**
 * Implementación de un conjunto de Strings utilizando una tabla de hash
 * con resolución de colisiones por encadenamiento (chaining).
 *
 * @property capacidadInicial Tamaño inicial del arreglo de buckets.
 *
 * Precondición: [capacidadInicial] debe ser mayor a 0.
 * Postcondición: Instancia del conjunto inicializada sin elementos.
 */
class ConjuntoPalabras(capacidadInicial: Int = 16) {

    // Arreglo de buckets. Cada bucket contiene una lista de Strings.
    private var tabla: Array<MutableList<String>> = Array(capacidadInicial) { mutableListOf() }

    // Cantidad total de elementos almacenados en la tabla de hash
    private var numElementos: Int = 0

    /**
     * Calcula el índice del arreglo correspondiente a una palabra dada mediante su hash.
     *
     * @param p Palabra a la cual se le calculará el índice.
     * @param capacidad Tamaño de la tabla sobre la cual se calculará el índice.
     * @return Índice entero dentro del rango de la tabla (0 <= idx < capacidad).
     *
     * Precondición: [capacidad] debe ser estrictamente mayor que 0.
     * Postcondición: Se calcula un índice no negativo sin alterar la tabla.
     */
    private fun obtenerIndice(p: String, capacidad: Int = tabla.size): Int {
        // Math.floorMod garantiza un índice en el rango 0 <= idx < capacidad
        return Math.floorMod(p.hashCode(), capacidad)
    }

   /**
     * Calcula el factor de carga actual de la tabla de hash.
     *
     * @return Factor de carga como un valor de tipo [Double].
     *
     * Precondición: La tabla debe estar inicializada.
     * Postcondición: Devuelve la relación entre el número de elementos y el tamaño de la tabla sin modificar la estructura.
     */
    fun obtenerFactorDeCarga(): Double {
        return numElementos.toDouble() / tabla.size
    }

    /**
     * Agrega una palabra al conjunto si no existe previamente.
     * Si el factor de carga alcanza o supera el umbral de 0.7, se desencadena un rehashing.
     *
     * @param p Palabra que se desea agregar al conjunto.
     *
     * Precondición: [p] debe ser no nulo (si p no es valido no se encontrara).
     * Postcondición: Si [p] no estaba en el conjunto, se inserta y el total de elementos se incrementa en 1.
     *                   Si el factor de carga alcanza o supera 0.7, la capacidad de la tabla se duplica.
     */
    fun agregar(p: String) {
        val idx = obtenerIndice(p)
        val bucket = tabla[idx]

        // Si la palabra ya está en el conjunto, no se duplica
        if (bucket.contains(p)) return

        bucket.add(p)
        numElementos++

        // Si el factor de carga es >= 0.7, realizamos rehashing duplicando el tamaño
        if (obtenerFactorDeCarga() >= 0.7) {
            rehashing()
        }
    }

    /**
     * Elimina una palabra específica del conjunto si esta se encuentra.
     *
     * @param p Palabra que se desea eliminar.
     * @return `true` si la palabra estaba presente y fue eliminada; `false` en caso contrario.
     *
     * Precondición: [p] debe ser no nulo (si p no es valido no se encontrara).
     * Postcondición: Si la palabra existe, se elimina de su cubeta y el total de elementos se disminuye en 1;
     *                   si no existe, la estructura no se modifica.
     */
    fun eliminar(p: String): Boolean {
        val idx = obtenerIndice(p)
        val bucket = tabla[idx]
        val removido = bucket.remove(p)
        if (removido) {
            numElementos--
        }
        return removido
    }

    /**
     * Verifica si una palabra se encuentra dentro del conjunto.
     *
     * @param p Palabra a buscar.
     * @return `true` si [p] pertenece al conjunto; `false` de lo contrario.
     *
     * Precondición: [p] debe ser no nulo (si p no es valido no se encontrara).
     * Postcondición: Retorna verdadero o falso segun la existencia de la palabra en el conjunto.
     */
    fun contiene(p: String): Boolean {
        val idx = obtenerIndice(p)
        return tabla[idx].contains(p)
    }

    /**
     * Redimensiona la tabla al doble de su capacidad actual y reorganiza los elementos almacenados.
     *
     * Precondición: El factor de carga debe haber alcanzado o superado 0.7.
     * Postcondición: La capacidad de la tabla se duplica y todos los elementos quedan reubicados
     *                   según el nuevo tamaño, conservando la misma cantidad de elementos.
     */
    private fun rehashing() {
        val nuevaCapacidad = tabla.size * 2
        val nuevaTabla: Array<MutableList<String>> = Array(nuevaCapacidad) { mutableListOf() }

        // Reinsertar todas las palabras guardadas en la nueva tabla
        for (bucket in tabla) {
            for (palabra in bucket) {
                val nuevoIdx = obtenerIndice(palabra, nuevaCapacidad)
                nuevaTabla[nuevoIdx].add(palabra)
            }
        }

        tabla = nuevaTabla
    }

    /**
     * Retorna una lista con todas las palabras almacenadas en el conjunto.
     *
     * @return Una colección con todas las palabras guardadas.
     *
     * Precondición: Ninguna.
     * Postcondición: Devuelve una nueva lista con todos los elementos presentes sin alterar el conjunto original.
     */
    fun obtenerTodas(): MutableList<String> {
        val todas = mutableListOf<String>()
        for (bucket in tabla) {
            todas.addAll(bucket)
        }
        return todas
    }

    /**
     * Retorna la cantidad total de elementos almacenados en el conjunto.
     *
     * @return Cantidad de palabras en la tabla.
     *
     * Precondición: Ninguna.
     * Postcondición: Devuelve el total de elementos sin modificar el estado del objeto.
     */
    fun tamano(): Int = numElementos
}