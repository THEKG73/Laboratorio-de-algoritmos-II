class HashTableChaining {

    private var capacidad: Int = 7
    
    private var tabla: Array<CircularList> = Array(capacidad) { CircularList() }
    
    private var numElementos: Int = 0

    /**
     * Función de Hash: Método de la división.
     * Garantiza un índice positivo dentro de los límites del arreglo.
     */
    private fun hash(clave: Int): Int {
        return Math.abs(clave.hashCode() % capacidad)
    }

    fun agregar(clave: Int, valor: String) {
        val factorCarga = numElementos.toDouble() / capacidad
        if (factorCarga >= 0.7) {
            rehashing()
        }

        val indice = hash(clave)
        val listaDestino = tabla[indice]

        val sizeAntes = listaDestino.size
        
        listaDestino.agregar(clave, valor)

        if (listaDestino.size > sizeAntes) {
            numElementos++
        }
    }

    fun eliminar(clave: Int) {
        val indice = hash(clave)
        val fueEliminado = tabla[indice].eliminar(clave)
        
        if (fueEliminado) {
            numElementos--
        }
    }

    fun buscar(clave: Int): String? {
        val indice = hash(clave)
        val nodo = tabla[indice].buscar(clave)
        return nodo?.valor
    }

    fun existe(clave: Int): Boolean {
        return buscar(clave) != null
    }

    fun numElementos(): Int {
        return numElementos
    }

    fun toStringDiccionario(): String {
        val sb = StringBuilder()
        sb.append("{")
        var primero = true

        for (lista in tabla) {
            if (lista.cabeza != null) {
                var actual = lista.cabeza
                do {
                    if (!primero) sb.append(", ")
                    sb.append("(${actual!!.clave}, ${actual.valor})")
                    primero = false
                    actual = actual.siguiente
                } while (actual != lista.cabeza)
            }
        }
        sb.append("}")
        return sb.toString()
    }

    /**
     * Operación interna para expandir la tabla y redistribuir los elementos
     * cuando el factor de carga supera el umbral crítico.
     */
    private fun rehashing() {
        val nuevaCapacidad = ((capacidad + 16) * 3) / 2
        
        val tablaAntigua = tabla
        
        capacidad = nuevaCapacidad
        tabla = Array(capacidad) { CircularList() }
        numElementos = 0
        
        // Iteramos sobre todos los anillos antiguos y reinsertamos cada nodo
        for (listaAntigua in tablaAntigua) {
            if (listaAntigua.cabeza != null) {
                var actual = listaAntigua.cabeza
                while (true) {
                    agregar(actual!!.clave, actual.valor)
                    actual = actual.siguiente
                    if (actual == listaAntigua.cabeza) break
                }
            }
        }
    }
}