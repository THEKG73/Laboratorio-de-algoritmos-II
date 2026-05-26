/**
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

/**
 * 4. ordenaDos: La funcion verifica si el primer elemento del arreglo es mayor al segundo elemento, en caso de que sea cierto
                 se intercambian los elementos.
 * * Parámetros:
        La funcion recibe un array de dos elementos de un tipo comparable, para este laboratorio sera entero o double, y devuelve 
        un arreglo del mismo tipo ordenado ascendentemente.
 */
fun <T: Comparable<T>> ordenaDos(arr: Array<T>): Array<T> {
    if (arr.size != 2) {
        println("Error: La función ordenaDos solo acepta arreglos de dos elementos.")
        System.exit(1)
    }

    if (arr[0]>arr[1]){
        val temp = arr[0]
        arr[0]=arr[1]
        arr[1]=temp
    }
    return arr
}

/**
 * 5. ordenaTres: La funcion verifica si el primer elemento del arreglo es mayor al segundo elemento, en caso de que sea cierto
                  se intercambian los elementos; después se verifica si el segundo elemento es mayor al tercero, en caso de que
                  sea cierto se intercambian; por último se verifica si el primer elemento del arreglo es mayor al segundo elemento, 
                  en caso de que sea cierto se intercambian los elementos.
 * * Parámetros:
        La funcion recibe un array de tres elementos de un tipo comparable, para este laboratorio sera entero o double, y devuelve 
        un arreglo del mismo tipo ordenado ascendentemente.
 */
fun <T: Comparable<T>> ordenaTres(arr: Array<T>): Array<T> {
    if (arr.size != 3) {
        println("Error: La función ordenaTres solo acepta arreglos de tres elementos.")
        System.exit(1)
    }

    if (arr[0]>arr[1]){
        val temp = arr[0]
        arr[0]=arr[1]
        arr[1]=temp
    }
    if (arr[1]>arr[2]){
        val temp = arr[1]
        arr[1]=arr[2]
        arr[2]=temp
    }
    if (arr[0]>arr[1]){
        val temp = arr[0]
        arr[0]=arr[1]
        arr[1]=temp
    }
    return arr
}

/**
 * 6. ordenaCuatro: La funcion verifica si el primer elemento del arreglo es mayor al segundo elemento, en caso de que sea cierto
                    se intercambian los elementos; después se verifica si el segundo elemento es mayor al tercero, en caso de que
                    sea cierto se intercambian; posteriormente se verifica si el tercer elemento del arreglo es mayor al cuarto 
                    elemento, en caso de que sea cierto se intercambian los elementos; luego se verifica si el primer elemento del 
                    arreglo es mayor al segundo elemento, en caso de que sea cierto se intercambian los elementos; seguidamente se 
                    verifica si el segundo elemento es mayor al tercero, en caso de que sea cierto se intercambian; por último 
                    se verifica si el primer elemento del arreglo es mayor al segundo elemento, en caso de que sea cierto se 
                    intercambian los elementos.
 * * Parámetros:
        La funcion recibe un array de cuatro elementos de un tipo comparable, para este laboratorio sera entero o double, y devuelve 
        un arreglo del mismo tipo ordenado ascendentemente.
 */
fun <T: Comparable<T>> ordenaCuatro(arr: Array<T>): Array<T> {
    if (arr.size != 4) {
        println("Error: La función ordenaCuatro solo acepta arreglos de cuatro elementos.")
        System.exit(1)
    }

    if (arr[0]>arr[1]){
        val temp = arr[0]
        arr[0]=arr[1]
        arr[1]=temp
    }
    if (arr[1]>arr[2]){
        val temp = arr[1]
        arr[1]=arr[2]
        arr[2]=temp
    }
    if (arr[2]>arr[3]){
        val temp = arr[2]
        arr[2]=arr[3]
        arr[3]=temp
    }
    if (arr[0]>arr[1]){
        val temp = arr[0]
        arr[0]=arr[1]
        arr[1]=temp
    }
    if (arr[1]>arr[2]){
        val temp = arr[1]
        arr[1]=arr[2]
        arr[2]=temp
    }
    if (arr[0]>arr[1]){
        val temp = arr[0]
        arr[0]=arr[1]
        arr[1]=temp
    }
    return arr
}

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

fun <T: Comparable<T>> mergesortInsertion(arr: Array<T>): Array<T> {
    when (arr.size) {
        0, 1 -> return arr
        2 -> return ordenaDos(arr)
        3 -> return ordenaTres(arr)
        4 -> return ordenaCuatro(arr)
        in 5..100 -> return insertionSort(arr)
    }
    val U : Array<T> = arr.copyOfRange(0, arr.size/2)
    val V : Array<T> = arr.copyOfRange(arr.size/2, arr.size)
    mergesortInsertion(U)
    mergesortInsertion(V)
    //Hacemos Merge de U y V
    merge(U, V, arr)
    return arr
}

fun <T: Comparable<T>> mergesort(arr: Array<T>): Array<T> {
    return mergesortEjecucion(arr, 0, arr.size - 1)
}

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



fun <T: Comparable<T>> mergesortIterativo(arr: Array<T>): Array<T> {
    val n = arr.size
    var k = 1
    while (k < n) {
        var a = 0
        var b = k
        var c = kotlin.math.min(2*k, n)
        while (b < n) {
            var p = a
            var q = b
            var r = a
            val z = @Suppress("UNCHECKED_CAST") (Array<Any?>(c-a) { null } as Array<T>)
            while (p != b && q != c) {
                if (arr[p] <= arr[q]) {
                    z[r - a] = arr[p]
                    p++
                    r++
                }
                else {
                    z[r - a] = arr[q]
                    q++
                    r++
                }
            }
            while (p != b) {
                z[r - a] = arr[p]
                p++
                r++
            }
            while (q != c) {
                z[r - a] = arr[q]
                q++
                r++
            }
            r = a
            while (r != c) {
                arr[r] = z[r - a]
                r++
            }
            a += 2*k
            b += 2*k;
            c = kotlin.math.min(c + 2*k, n)
        }
        k*= 2
    }
    return arr
}