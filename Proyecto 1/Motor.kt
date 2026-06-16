//Tipos de Datos (TADs) a Implementar

//TAD Elemento Espacial (SpatialElement)
class SpatialElement(val id: Int, val x: Int, val y: Int, val z: Double) : Comparable<SpatialElement> {
    //Declaración de la comparación entre elementos espaciales
    override fun compareTo(other: SpatialElement): Int {
        //Comparamos por coordenada z
        if (this.z < other.z) return -1
        if (this.z > other.z) return 1

        //Comparamos por coordenada y en orden inverso
        if (this.y > other.y) return -1
        if (this.y < other.y) return 1

        // Si todo es igual, se consideran iguales
        return 0
    }
}

//TAD Región Estructural (MapRegion)
class MapRegion(val sectorId: Int) {
    //Almacenamiento de los cuadrantes
    val almacenamiento: Array<SpatialElement?> = Array(100) { null }
    //Contador de elementos
    var cantidadElementos: Int = 0

    //Funcion encargada de recibir un objeto en la region
    fun insertarElemento(elemento: SpatialElement): Boolean {
        //Verificamos el Invariante de Representación
        if (cantidadElementos >= 80) {
            println("Error: El elemento con ID ${elemento.id} no se puede insertar en la región $sectorId porque se ha alcanzado el límite de densidad")
            return false
        }
        almacenamiento[cantidadElementos] = elemento
        cantidadElementos++
        return true
    }
}

//TAD Gestor de Escena (SceneManager)
class SceneManager(val maxMovimientos: Int) {
    //Inicializamos el tablero con sus regiones
    val tableroRegiones: Array<MapRegion> = Array(100) {MapRegion(it)}

    //Historial de movimientos realizados
    val historial: Array<IntArray> = Array(maxMovimientos) {IntArray(720) {-1}}

    var movimientoActual: Int = 0
    var maxIdAsignado: Int = 0

    //Funcion encargada de gestionar un elemento hacia su correspondiente región
    fun insertarElementoGlobal(elemento: SpatialElement): Boolean {
        //Validamos coordenadas validas

        if (elemento.x !in 0..99 || elemento.y !in 0..99) {
            println("Error: El elemento con ID ${elemento.id} tiene coordenadas inválidas (x: ${elemento.x}, y: ${elemento.y})")
            return false
        }

        //Calculamos el índice de la región correspondiente
        val indiceRegion = (elemento.y/10)*10 + (elemento.x/10)

        //Insertamos el elemento en la región correspondiente
        val insercionExitosa = tableroRegiones[indiceRegion].insertarElemento(elemento)

        if (insercionExitosa && elemento.id > maxIdAsignado) {
            maxIdAsignado = elemento.id
        }
        return insercionExitosa
    }

    fun desplazarCamara(camX: Int, camY: Int) {
        val xMin = kotlin.math.max(0, camX - 8)
        val xMax = kotlin.math.min(99, camX + 8)
        val yMin = kotlin.math.max(0, camY - 4)
        val yMax = kotlin.math.min(99, camY + 4)

        val colMin = xMin / 10
        val colMax = xMax / 10
        val filaMin = yMin / 10
        val filaMax = yMax / 10

        val elementosVisiblesTemp = Array<SpatialElement?>(480) { null }
        var indiceVisible = 0

        //Filtro de Visibilidad
        for (fila in filaMin..filaMax) {
            for (col in colMin..colMax) {
                val region = tableroRegiones[fila * 10 + col]
                for (i in 0 until region.cantidadElementos){
                    val elemento = region.almacenamiento[i]!!
                    val dentroX = elemento.x in xMin..xMax
                    val dentroY = elemento.y in yMin..yMax
                    if (dentroX && dentroY) {
                        elementosVisiblesTemp[indiceVisible] = elemento
                        indiceVisible++
                    }
                }
            }
        }

        if (indiceVisible == 0) {
            return
        }

        val arregloOrdenado = Array<SpatialElement>(indiceVisible) {elementosVisiblesTemp[it]!!}

        //Selección de algoritmo de ordenamiento y ordenamiento de los elementos visibles
        var nombreAlgoritmo = ""
        if (indiceVisible <= 50){
            Ordenamiento.insertionSort(arregloOrdenado)
            nombreAlgoritmo = "InsertionSort"
        }
        else if (indiceVisible <= 200) {
            Ordenamiento.mergesort(arregloOrdenado)
            nombreAlgoritmo = "MergeSort"
        }
        else {
            Ordenamiento.quickSort(arregloOrdenado)
            nombreAlgoritmo = "QuickSort"
        }

        println("Algoritmo utilizado: $nombreAlgoritmo")
        println("Cantidad de elementos procesados: $indiceVisible")

        if (movimientoActual < maxMovimientos) {
            for (i in 0 until indiceVisible) {
                historial[movimientoActual][i] = arregloOrdenado[i].id
            }
            movimientoActual++
        }
    }

    fun reiniciarMapa() {
        for (region in tableroRegiones) {
            region.cantidadElementos = 0
            for (i in region.almacenamiento.indices) {
                region.almacenamiento[i] = null
            }
        }

        for (fila in historial.indices) {
            for (columna in historial[fila].indices) {
                historial[fila][columna] = -1
            }
        }
        movimientoActual = 0
        maxIdAsignado = 0
    }
}