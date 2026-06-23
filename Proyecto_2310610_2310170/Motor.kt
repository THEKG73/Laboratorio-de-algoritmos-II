//Tipos de Datos (TADs) a Implementar

//TAD Elemento Espacial (SpatialElement)

/**
 * 
 * Implementa la interfaz [Comparable] para definir un orden
 * basado en sus coordenadas de profundidad y posición vertical.
 *
 * Invariante de Representación (IR):
 * - El [id] debe ser un entero único positivo.
 * - Las coordenadas [x] e [y] deben estar en el rango `[0, 99]` para ser consideradas válidas en el mapa.
 * - La coordenada [z] debe pertenecer al rango `[0.0, 10.9]`.
 *
 * @property id Identificador único del elemento espacial.
 * @property x Coordenada en el eje X.
 * @property y Coordenada en el eje Y.
 * @property z Coordenada en el eje Z.
 */
class SpatialElement(val id: Int, val x: Int, val y: Int, val z: Double) : Comparable<SpatialElement> {
    //Declaración de la comparación entre elementos espaciales

    /**
     * Compara este elemento espacial con otro para determinar su ordenamiento.
     *
     * El criterio de ordenación es:
     * 1. Menor valor de [z] primero.
     * 2. A igual [z], mayor valor de [y] primero.
     *
     * Precondición: 
     * - [other] debe ser un SpacialElement no nulo.
     *
     * Postcondición:
     * - Retorna -1 si este objeto es menor que [other].
     * - Retorna 1 si este objeto es mayor que [other].
     * - Retorna cero si ambos objetos se consideran iguales bajo los criterios evaluados.
     *
     * @param other El otro elemento espacial con el que se va a comparar.
     * @return Un entero que indica la relación de orden.
     */
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

/**
 * Representa una región dentro del tablero del mapa.
 *
 * Encargada de agrupar y almacenar un subconjunto de elementos espaciales de manera local.
 *
 * Invariante de Representación (IR):
 * - El arreglo [almacenamiento] siempre tiene un tamaño fijo de 100 posiciones.
 * - [cantidadElementos] debe ser un valor en el rango [0,80]. Nunca debe superar el límite de densidad de 80.
 *
 * @property sectorId Identificador numérico único de la región dentro del tablero.
 */
class MapRegion(val sectorId: Int) {
    //Almacenamiento de los cuadrantes
    var almacenamiento: Array<SpatialElement?> = Array(100) { null }
    //Contador de elementos
    var cantidadElementos: Int = 0
    
    //Funcion encargada de recibir un objeto en la region

    /**
     * Inserta un elemento espacial en el almacenamiento local de la región si no se ha superado el límite de densidad.
     *
     * Precondición:
     * - [elemento] debe ser una instancia válida de [SpatialElement].
     * - El estado actual de la región debe cumplir con el invariante de densidad (`cantidadElementos < 80`).
     *
     * Postcondición:
     * - Si `cantidadElementos >= 80`, la estructura no cambia, se imprime un mensaje de error y retorna `false`.
     * - Si hay espacio, [elemento] se guarda en la posición [cantidadElementos] de [almacenamiento], [cantidadElementos] se incrementa en 1 y retorna `true`.
     *
     * @param elemento El objeto espacial que se desea agregar a esta región.
     * @return `true` si la inserción fue exitosa; `false` en caso contrario.
     */
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

/**
 * Administrador central de la escena que gestiona el tablero global compuesto por múltiples regiones,
 * procesa la visibilidad de la cámara y almacena el historial de ejecuciones.
 *
 * Invariante de Representación (IR):
 * - [tableroRegiones] es una matriz aplanada fija de exactamente 100 instancias de [MapRegion].
 * - [movimientoActual] debe cumplir con `0 <= movimientoActual <= maxMovimientos`.
 * - [historial] es una matriz de dimensiones `maxMovimientos x 480` inicializada con `-1`.
 *
 * @property maxMovimientos Límite máximo de movimientos interactivos permitidos en la simulación.
 */
class SceneManager(val maxMovimientos: Int) {
    //Inicializamos el tablero con sus regiones
    val tableroRegiones: Array<MapRegion> = Array(100) {MapRegion(it)}

    //Historial de movimientos realizados
    var historial: Array<IntArray> = Array(maxMovimientos) {IntArray(480) {-1}}

    var movimientoActual: Int = 0
    var maxIdAsignado: Int = 0

    //Funcion encargada de gestionar un elemento hacia su correspondiente región

    /**
     * Valida las restricciones de un elemento y calcula a qué región pertenece para permitir su inserción local.
     *
     * Precondición:
     * - [elemento] debe tener sus atributos inicializados.
     *
     * Postcondición:
     * - Si las coordenadas `x`, `y` o `z` están fuera de rango, no se modifica el estado del mapa y retorna `false`.
     * - Si el elemento es válido y se inserta exitosamente en su región correspondiente, se actualiza [maxIdAsignado] y retorna `true`.
     *
     * @param elemento El objeto espacial que se desea incorporar al mapa global.
     * @return `true` si el elemento superó las validaciones y fue aceptado por la región destino; `false` en caso contrario.
     */
    fun insertarElementoGlobal(elemento: SpatialElement): Boolean {
        //Validamos coordenadas validas

        if (elemento.x !in 0..99 || elemento.y !in 0..99) {
            println("Error: El elemento con ID ${elemento.id} tiene coordenadas inválidas (x: ${elemento.x}, y: ${elemento.y})")
            return false
        }

        if (elemento.z !in 0.0..10.9){
            println("Error: El elemento con ID ${elemento.id} tiene el valor de z fuera de rango [0.0,10.9]")
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

    /**
     * Simula el desplazamiento de una cámara centrado en las coordenadas dadas.
     * Filtra los elementos dentro de dicho rango, selecciona un algoritmo de ordenamiento según la densidad
     * de elementos encontrados y registra sus IDs en el historial.
     *
     * Los algoritmos de ordenación se seleccionan bajo las siguientes reglas:
     * - `<= 50` elementos: **InsertionSort**
     * - `51..200` elementos: **MergeSort**
     * - `> 200` elementos: **QuickSort**
     *
     * Precondición:
     * - Las coordenadas centrales de la cámara [camX] y [camY] deben ser válidas dentro del plano.
     *
     * Postcondición:
     * - Si no se encuentran elementos visibles en el rango de la cámara, la función termina de inmediato sin mutar estados.
     * - Si hay elementos visibles, se imprimen estadísticas por consola, se consume un slot en el [historial] 
     * - guardando el orden de los IDs encontrados, y se incrementa [movimientoActual].
     *
     * @param camX Centro de la coordenada X de la cámara.
     * @param camY Centro de la coordenada Y de la cámara.
     */
    fun desplazarCamara(camX: Int, camY: Int) {
        val xMin = kotlin.math.max(0, camX - 8)
        val xMax = kotlin.math.min(99, camX + 8)
        val yMin = kotlin.math.max(0, camY - 4)
        val yMax = kotlin.math.min(99, camY + 4)

        val colMin = xMin / 10
        val colMax = xMax / 10
        val filaMin = yMin / 10
        val filaMax = yMax / 10

        var elementosVisiblesTemp = Array<SpatialElement?>(480) { null }
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
            insertionSort(arregloOrdenado)
            nombreAlgoritmo = "InsertionSort"
        }
        else if (indiceVisible <= 200) {
            mergesort(arregloOrdenado)
            nombreAlgoritmo = "MergeSort"
        }
        else {
            quickSort(arregloOrdenado, 0, arregloOrdenado.size - 1)
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

    /**
     * Limpia por completo el estado del gestor de escena, vaciando todas las regiones y el historial registrado.
     *
     * Precondición:
     * - Ninguna (se puede invocar en cualquier momento del ciclo de vida de la clase).
     *
     * Postcondición:
     * - Todas las instancias dentro de [tableroRegiones] reducen su `cantidadElementos` a 0 y limpian sus arreglos de almacenamiento con `null`.
     * - La matriz completa de [historial] vuelve a llenarse con su valor por defecto `-1`.
     * - [movimientoActual] y [maxIdAsignado] se resetean a 0.
     */
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