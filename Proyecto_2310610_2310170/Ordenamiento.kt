/**
 * ARCHIVO: Ordenamiento.kt
 * Contiene implementaciones de algoritmos de ordenamiento necesarios para el proyecto.
 */

/**
 * Ordena un arreglo mediante el algoritmo de ordenamiento por inserción (Insertion Sort).
 *
 * Divide virtualmente el arreglo en una sección ordenada y una desordenada. Toma los elementos
 * de la sección desordenada uno a uno y los desplaza hacia atrás hasta insertarlos en la posición 
 * correcta dentro de la sección ordenada.
 *
 * Precondiciones:
 * - El objeto [arr] no debe ser nulo.
 * - El tipo [T] debe ser comparable consigo mismo para permitir su ordenamiento.
 *
 * Postcondiciones:
 * - El arreglo original [arr] queda ordenado en forma ascendente.
 *
 * @param T Tipo genérico comparable de los elementos contenidos en el arreglo.
 * @param arr Arreglo desordenado de elementos de tipo [T].
 * @return El mismo arreglo [arr] ordenado ascendentemente.
 */
fun <T : Comparable<T>> insertionSort(arr: Array<T>): Array<T> {
    for (i in 1 until (arr.size)) {
        var j = i
        while (j>=1 && arr[j] < arr[j-1]) {
            var temp = arr[j]
            arr[j] = arr[j-1]
            arr[j-1] = temp
            j = j - 1
        }
    }
    return arr
}

/**
 * Combina dos subarreglos ordenados de manera independiente en un único arreglo ordenado.
 *
 * Utiliza evalua de manera secuencial las posiciones más bajas de ambos subarreglos dados 
 * ([U] y [V]), insertando el menor elemento en el arreglo destino [A].
 *
 * Precondiciones:
 * - Los arreglos [U] y [V] deben estar previamente ordenados de forma ascendente ascendente.
 * - La suma de las longitudes de los subarreglos debe ser igual o menor a la capacidad del destino (`U.size + V.size <= A.size`).
 * - El tipo [T] debe ser comparable consigo mismo para permitir su ordenamiento.
 *
 * Postcondiciones:
 * - El arreglo destino [A] se reescribe de forma ordenada basándose en la intercalación de los elementos de [U] y [V].
 *
 * @param T Tipo genérico comparable de los elementos.
 * @param U Primer subarreglo ordenado de forma ascendente.
 * @param V Segundo subarreglo ordenado de forma ascendente.
 * @param A Arreglo de destino encargado de recibir la fusión unificada de elementos.
 * @return El arreglo destino [A] con la combinación final ordenada.
 */
fun <T: Comparable<T>> merge(U: Array<T>, V: Array<T>, A: Array<T>): Array<T> {
    var i = 0 ; var j = 0
    for (k in 0 until A.size) {
        if (i < U.size && (j >= V.size || U[i] <= V[j])) {
            A[k] = U[i]
            i++
        }
        else {
            A[k] = V[j]
            j++
        }
    }
    return A
}

/**
 * Punto de entrada para el ordenamiento por Merge Sort.
 *
 * Inicializa el proceso del merge sort especificando su tamaño.
 *
 * Precondiciones:
 * - El objeto [arr] no debe ser nulo.
 * - El tipo [T] debe ser comparable consigo mismo para permitir su ordenamiento.
 *
 * Postcondiciones:
 * - El arreglo original se modifica para obtener el mismo arreglo en un orden ascendente.
 *
 * @param T Tipo genérico de datos comparables.
 * @param arr Arreglo inicial desordenado.
 * @return El mismo arreglo [arr] completamente ordenado.
 * @see mergesortEjecucion
 */
fun <T: Comparable<T>> mergesort(arr: Array<T>): Array<T> {
    return mergesortEjecucion(arr, 0, arr.size - 1)
}

/**
 * Función recursiva encargada de ejecutar la lógica de Merge Sort.
 *
 * Divide el rango delimitado por [p] y [r] calculando un punto medio [q], se llama recursivamente 
 * para ambas mitades y combina los resultados.
 *
 * Precondiciones:
 * - Los límites dados deben ser válidos con respecto al índice del arreglo (0 <= p <= r < arr.size).
 * - El tipo [T] debe ser comparable consigo mismo para permitir su ordenamiento.
 *
 * Postcondiciones:
 * - El segmento comprendido desde el índice [p] hasta el índice [r] queda ordenado ascendentemente.
 *
 * @param T Tipo genérico de datos comparables.
 * @param arr Arreglo que esta siendo manipulado.
 * @param p Índice de inicio del segmento actual a procesar.
 * @param r Índice final del segmento actual a procesar.
 * @return El arreglo [arr] modificado dentro del rango especificado [p, r].
 */
fun <T: Comparable<T>> mergesortEjecucion(arr: Array<T>, p: Int, r: Int): Array<T> {
    if (p < r) {
        val q = (p + r) / 2
        mergesortEjecucion(arr, p, q)
        mergesortEjecucion(arr, q + 1, r)

        val arrTemporal = arr.copyOfRange(p, r + 1)
        merge(arr.copyOfRange(p, q + 1), arr.copyOfRange(q + 1, r + 1), arrTemporal)
        for (i in arrTemporal.indices) {
            arr[p + i] = arrTemporal[i]
        }
    }
    return arr
}

/**
 * Ordena un arreglo utilizando el algoritmo de ordenamiento Quick Sort.
 *
 * Selecciona un pivote (generalmente el último elemento del segmento) y particiona el arreglo
 * de manera que los elementos menores al pivote queden a su izquierda y los mayores a su derecha.
 * Luego, aplica recursivamente el mismo proceso sobre las sub-secciones resultantes.
 *
 * Precondiciones:
 * - El objeto [arr] no debe ser nulo.
 * - El tipo [T] debe ser comparable consigo mismo para permitir su ordenamiento.
 *
 * Postcondiciones:
 * - El arreglo original [arr] queda ordenado en forma ascendente.
 *
 * @param T Tipo genérico de datos comparables.
 * @param arr Arreglo inicial desordenado.
 * @param p Índice de inicio del segmento a ordenar.
 * @param r Índice final del segmento a ordenar.
 * @return El mismo arreglo [arr] ordenado ascendentemente dentro del rango especificado.
 */
fun <T: Comparable<T>> quickSort(arr: Array<T>, p: Int, r: Int): Array<T> {
    if (p < r) {
        val q = partition(arr, p, r)
        quickSort(arr, p, q - 1)
        quickSort(arr, q + 1, r)
    }
    return arr
}

/**
 * Particiona un segmento del arreglo alrededor de un pivote.
 *
 * Toma el último elemento del segmento como pivote, reordena los elementos de manera que
 * los menores al pivote queden a su izquierda y los mayores a su derecha, y devuelve la posición final del pivote.
 *
 * Precondiciones:
 * - El objeto [arr] no debe ser nulo.
 * - Los índices [p] y [r] deben delimitar un segmento válido dentro de [arr].
 * - El tipo [T] debe ser comparable consigo mismo para permitir la comparación entre elementos.
 *
 * Postcondiciones:
 * - El segmento del arreglo queda particionado alrededor del pivote, con el pivote en su posición final.
 *
 * @param T Tipo genérico de datos comparables.
 * @param arr Arreglo que contiene el segmento a particionar.
 * @param p Índice de inicio del segmento a particionar.
 * @param r Índice final del segmento a particionar (pivote).
 * @return Índice final del pivote después de la partición.
 */
fun <T: Comparable<T>> partition(arr: Array<T>, p: Int, r: Int): Int {
    val x = arr[r]
    var i = p - 1
    for (j in p until r) {
        if (arr[j] <= x) {
            i++
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }
    val temp = arr[i + 1]
    arr[i + 1] = arr[r]
    arr[r] = temp
    return i + 1
}