/**
 * ARCHIVO: Ordenamiento.kt
 * Contiene implementaciones de algoritmos de ordenamiento necesarios para el laboratorio.
 */

 /** 
  * 1. Bubble Sort: -- Ingrese Breve Descripcion del Algoritmo --
  * * Parámetros:
        -- Inserte descripcion de los parametros de entrada y salida --
 */
fun <T : Comparable<T>> bubbleSort(arr: Array<T>): Array<T> {
    for(i in 0 until arr.size) {
        for(j in (arr.size - 1) downTo (i + 1)) {
            if(arr[j] < arr[j - 1]) {
                var temp = arr[j]
                arr[j] = arr[j - 1]
                arr[j - 1] = temp
            }
        }
    }
    return arr
 }

/**
 * 2. Insertion Sort: -- Ingrese Breve Descripcion del Algoritmo
 * * Parámetros:
        -- Inserte descripcion de los parametros de entrada y salida --
 */
fun <T : Comparable<T>> insertionSort(arr: Array<T>): Array<T> {
    for (i in 1 until (arr.size-1)) {
        var temp = arr[i]
        var j = i-1
        while (j>=0 && arr[j] > temp) {
            arr[j+1] = arr[j]
            j -= -1
        }
        arr[i+1] = temp
    }
    return arr
}

/**
 * 3. Selection Sort: -- Ingrese Breve Descripcion del Algoritmo
 * * Parámetros:
        -- Inserte descripcion de los parametros de entrada y salida --
 */
fun <T : Comparable<T>> selectionSort(arr: Array<T>): Array<T> {
    for (i in 0 until (arr.size - 2)) {
        var temp = arr[i]
        var loc = i
        for (j in i+1 until (arr.size - 1)){
            if (arr[j] < arr[loc]){
                loc = j
            } 
        }
        arr[i]=arr[loc]
        arr[loc]= temp
    }
    return arr
}
