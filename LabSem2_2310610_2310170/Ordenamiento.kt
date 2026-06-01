/** Esto es una prueba
 * ARCHIVO: Ordenamiento.kt
 * Contiene implementaciones de algoritmos de ordenamiento necesarios para el laboratorio.
 */

 /** 
  * 1. Bubble Sort: La funcion recorre el arreglo desde el ultimo elemento hasta el primero, en cada iteracion se verifica si el 
                    elemento anterior es mayor al elemento actual, en caso de serlo se intercambian las posiciones de ambos 
                    elementos, asi con cada uno hasta llegar al primer elemento del arreglo, como dicho elemento se garantiza
                    que es menor o igual a cada elemento de la lista, para la siguiente iteracion no se tomara en cuenta.
  * * Parámetros:
        La funcion recibe un array desordenado de un tipo comparable, para este laboratorio sera entero o double, y devuelve un 
        arreglo del mismo tipo ordenado ascendentemente.
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
 * 2. Insertion Sort: La funcion recorre el arreglo comenzando desde el segundo elemento hasta el ultimo, se selecciona un elemento
                      y se recorre el arreglo hacia atras para ver en cual posicion va dicho elemento, observemos que desde el 
                      elemento escogido hacia atras estara ordenada, se van rodando los elementos a la derecha para hacer espacio
                      al elemento a insertar y no perder los datos.
 * * Parámetros:
        La funcion recibe un array desordenado de un tipo comparable, para este laboratorio sera entero o double, y devuelve un 
        arreglo del mismo tipo ordenado ascendentemente.
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
 * 3. Selection Sort: La funcion recorre el arreglo desde el primer elemento hasta el ultimo, busca cual es el menor de todos
                      los elementos e intercambia ese elemento con el que estaba inicialmente en dicha posicion, asi se 
                      garantiza que los elementos mas pequeños estaran a la derecha y los mayores a la izquierda.
 * * Parámetros:
        La funcion recibe un array desordenado de un tipo comparable, para este laboratorio sera entero o double, y devuelve 
        un arreglo del mismo tipo ordenado ascendentemente.
 */
fun <T : Comparable<T>> selectionSort(arr: Array<T>): Array<T> {
    for (i in 0 until (arr.size - 1)) {
        var temp = arr[i]
        var loc = i
        for (j in i+1 until (arr.size)){
            if (arr[j] < arr[loc]){
                loc = j
            } 
        }
        arr[i]=arr[loc]
        arr[loc]= temp
    }
    return arr
}
