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
}

//TAD Gestor de Escena (SceneManager)
class SceneManager(val maxMovimientos: Int) {
    //Inicializamos el tablero con sus regiones
    val tableroRegiones: Array<MapRegion> = Array(100) {MapRegion(it)}

    //Historial de movimientos realizados
    val historial: Array<IntArray> = Array(maxMovimientos) {IntArray(720) {-1} }
}