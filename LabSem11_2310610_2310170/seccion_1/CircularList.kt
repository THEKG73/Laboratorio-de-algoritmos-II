class CircularList {
    var cabeza: HashTableEntry? = null
    var size: Int = 0

    
    fun agregar(clave: Int, valor: String) {
        val nodoExistente = buscar(clave)
        if (nodoExistente != null) {
            nodoExistente.valor = valor
            return
        }

        val nuevoNodo = HashTableEntry(clave, valor)

        if (cabeza == null) {
            nuevoNodo.siguiente = nuevoNodo
            nuevoNodo.anterior = nuevoNodo
            cabeza = nuevoNodo
        } else {
            val cola = cabeza!!.anterior
            
            nuevoNodo.siguiente = cabeza
            nuevoNodo.anterior = cola
            
            cola!!.siguiente = nuevoNodo
            cabeza!!.anterior = nuevoNodo
            
            cabeza = nuevoNodo 
        }
        size++
    }

    fun buscar(clave: Int): HashTableEntry? {
        if (cabeza == null) return null

        var actual = cabeza

        while (true) {
            if (actual!!.clave == clave) {
                return actual
            }
            actual = actual.siguiente
            if (actual == cabeza) break
        }

        return null
    }

    fun eliminar(clave: Int): Boolean {
        val nodoAEliminar = buscar(clave) ?: return false

        if (nodoAEliminar.siguiente == nodoAEliminar) {
            cabeza = null
        } else {
            val nodoAnterior = nodoAEliminar.anterior
            val nodoSiguiente = nodoAEliminar.siguiente

            nodoAnterior!!.siguiente = nodoSiguiente
            nodoSiguiente!!.anterior = nodoAnterior

            if (cabeza == nodoAEliminar) {
                cabeza = nodoSiguiente
            }
        }
        size--
        return true
    }
}