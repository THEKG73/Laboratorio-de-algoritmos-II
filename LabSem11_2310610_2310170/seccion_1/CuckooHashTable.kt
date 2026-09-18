class CuckooHashTable {

    private var capacidad: Int = 7
    
    private var tabla1: Array<CuckooHashTableEntry?> = Array(capacidad) { null }
    private var tabla2: Array<CuckooHashTableEntry?> = Array(capacidad) { null }
    
    private var numElementos: Int = 0

    //Constante de Knuth para el método de la multiplicación
    private val A = 0.6180339887

    //h1 Método de la división para la Tabla 1
    private fun h1(clave: Int): Int {
        return Math.abs(clave.hashCode() % capacidad)
    }

    //h2 Método de la multiplicación para la Tabla 2
    private fun h2(clave: Int): Int {
        val k = Math.abs(clave.toDouble())
        val kA = k * A
        val fraccion = kA - Math.floor(kA)
        return Math.floor(capacidad * fraccion).toInt()
    }

    fun agregar(clave: Int, valor: String) {
        //Si ya existe, actualizamos el valor y abortamos
        val idx1 = h1(clave)
        if (tabla1[idx1]?.clave == clave) {
            tabla1[idx1]!!.valor = valor
            return
        }
        val idx2 = h2(clave)
        if (tabla2[idx2]?.clave == clave) {
            tabla2[idx2]!!.valor = valor
            return
        }

        // Control de factor de carga global (Elementos totales/Capacidad de una tabla)
        val factorCarga = numElementos.toDouble()/(capacidad)
        if (factorCarga >= 0.7) {
            rehashing()
        }

        // Empieza el intento de inserción y desplazamiento
        var actualNodo = CuckooHashTableEntry(clave, valor)
        
        // Límite de saltos "MaxLoop" según paper de Cuckoo Hashing
        // Relación con el factor de carga 0.7 del enunciado, r >= (1 + epsilon)n
        // Si n/r = 0.7 => r = n/0.7 => r = 1.428n => epsilon es aprox 0.428
        val epsilon = 0.428 
        val baseLog = 1.0+epsilon

        // Cambio de base logaritmo para conseguir log_(1+epsilon)(r)
        val logBaseEpsilon = Math.log(capacidad.toDouble())/Math.log(baseLog)

        // Fórmula del paper de Cuckoo Hashing, ceil(3*log)
        val formulaMaxLoop = Math.ceil(3*logBaseEpsilon).toInt()

        // Piso de 10 para cuando la capacidad inicial es pequeña (como el tamaño inicial de 7)
        val maxLoop = Math.max(10, formulaMaxLoop)

        for (i in 0 until maxLoop) {
            // Intento en Tabla 1
            val pos1 = h1(actualNodo.clave)
            if (tabla1[pos1] == null) {
                tabla1[pos1] = actualNodo
                numElementos++
                return
            }
            // Si hay colision, empujamos al ocupante y seguimos con el desplazado
            var ocupanteDesplazado = tabla1[pos1]
            tabla1[pos1] = actualNodo
            actualNodo = ocupanteDesplazado!!

            // Intento en Tabla 2 para el nodo desplazado
            val pos2 = h2(actualNodo.clave)
            if (tabla2[pos2] == null) {
                tabla2[pos2] = actualNodo
                numElementos++
                return
            }
            // Si hay colision, empujamos al ocupante y seguimos con el desplazado
            ocupanteDesplazado = tabla2[pos2]
            tabla2[pos2] = actualNodo
            actualNodo = ocupanteDesplazado!!
        }

        // Si el bucle termina, significa que entramos en un ciclo cerrado. 
        // Forzamos un rehash e intentamos agregar el nodo desplazado nuevamente.
        rehashing()
        agregar(actualNodo.clave, actualNodo.valor)
    }

    fun buscar(clave: Int): String? {
        val idx1 = h1(clave)
        if (tabla1[idx1]?.clave == clave) return tabla1[idx1]!!.valor
        
        val idx2 = h2(clave)
        if (tabla2[idx2]?.clave == clave) return tabla2[idx2]!!.valor
        
        return null
    }

    fun eliminar(clave: Int) {
        val idx1 = h1(clave)
        if (tabla1[idx1]?.clave == clave) {
            tabla1[idx1] = null
            numElementos--
            return
        }
        
        val idx2 = h2(clave)
        if (tabla2[idx2]?.clave == clave) {
            tabla2[idx2] = null
            numElementos--
        }
    }

    fun existe(clave: Int): Boolean {
        return buscar(clave) != null
    }

    fun numElementos(): Int {
        return numElementos
    }

    private fun rehashing() {
        val nuevaCapacidad = ((capacidad + 16) * 3) / 2
        
        val viejaTabla1 = tabla1
        val viejaTabla2 = tabla2
        
        capacidad = nuevaCapacidad
        tabla1 = Array(capacidad) { null }
        tabla2 = Array(capacidad) { null }
        numElementos = 0
        
        
        for (nodo in viejaTabla1) {
            if (nodo != null) agregar(nodo.clave, nodo.valor)
        }
        for (nodo in viejaTabla2) {
            if (nodo != null) agregar(nodo.clave, nodo.valor)
        }
    }
}