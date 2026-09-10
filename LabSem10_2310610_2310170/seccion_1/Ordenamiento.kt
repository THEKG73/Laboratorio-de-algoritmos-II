/**
 * ARCHIVO: Ordenamiento.kt
 * Contiene implementaciones de algoritmos de ordenamiento necesarios para el laboratorio.
 */

/**
 * Ordena un arreglo mediante el algoritmo de ordenamiento Bubble Sort.
 *
 * Recorre el arreglo de derecha a izquierda comparando elementos adyacentes y flotando
 * el valor menor hacia las primeras posiciones. En cada iteración completa, el elemento más
 * pequeño del subarreglo evaluado queda fijado en su posición definitiva.
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
 * Ordena un arreglo mediante el algoritmo de ordenamiento por selección (Selection Sort).
 *
 * Busca de forma lineal el elemento mínimo del subarreglo desordenado y lo intercambia con 
 * el elemento que se encuentra al principio de dicha sección. Repite el proceso hasta llegar al ultimo elemento.
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
 * Ordena por comparación directa un arreglo que contiene dos elementos.
 *
 * Evalúa si el primer elemento es mayor que el segundo, en caso de ser así, se 
 * intercambian las posiciones de los elementos.
 *
 * Precondiciones:
 * - El arreglo [arr] debe tener un tamaño de 2 elementos (`arr.size == 2`).
 * - El tipo [T] debe ser comparable consigo mismo para permitir su ordenamiento.
 *
 * Postcondiciones:
 * - El arreglo original queda ordenado de manera que `arr[0] <= arr[1]`.
 *
 * @param T Tipo genérico comparable de los elementos.
 * @param arr Arreglo de tamaño fijo igual a dos.
 * @return El mismo arreglo [arr] ordenado de forma ascendente.
 * @throws SystemExitException Detiene la ejecución si el tamaño del arreglo es distinto a 2.
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
 * Ordena por comparación directa un arreglo que contiene tres elementos.
 *
 * La funcion verifica si el primer elemento del arreglo es mayor al segundo elemento, en caso de que sea cierto
 * se intercambian las posiciones de los elementos; después se verifica si el segundo elemento es mayor al tercero, 
 * en caso de que sea cierto se intercambian; luego se verifica si el primer elemento del arreglo es mayor al segundo 
 * elemento, en caso de que sea cierto se intercambian los elementos; seguidamente se verifica si el segundo elemento 
 * es mayor al tercero, en caso de que sea cierto se intercambian; por último se verifica si el primer elemento del 
 * arreglo es mayor al segundo elemento, en caso de que sea cierto se intercambian los elementos.

 * Precondiciones:
 * - El arreglo [arr] debe tener un tamaño de 3 elementos (`arr.size == 3`).
 * - El tipo [T] debe ser comparable consigo mismo para permitir su ordenamiento.
 *
 * Postcondiciones:
 * - El arreglo original queda ordenado de manera que `arr[0] <= arr[1] <= arr[2]`.
 *
 * @param T Tipo genérico comparable de los elementos.
 * @param arr Arreglo de tamaño fijo igual a tres.
 * @return El mismo arreglo [arr] ordenado de forma ascendente.
 * @throws SystemExitException Detiene la ejecución si el tamaño del arreglo es distinto a 3.
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
 * Ordena por comparación directa un arreglo que contiene cuatro elementos.
 *
 * La funcion verifica si el primer elemento del arreglo es mayor al segundo elemento, en caso de que sea cierto
 * se intercambian los elementos; después se verifica si el segundo elemento es mayor al tercero, en caso de que
 * sea cierto se intercambian; posteriormente se verifica si el tercer elemento del arreglo es mayor al cuarto 
 * elemento, en caso de que sea cierto se intercambian los elementos; luego se verifica si el primer elemento del 
 * arreglo es mayor al segundo elemento, en caso de que sea cierto se intercambian los elementos; seguidamente se 
 * verifica si el segundo elemento es mayor al tercero, en caso de que sea cierto se intercambian; por último 
 * se verifica si el primer elemento del arreglo es mayor al segundo elemento, en caso de que sea cierto se 
 * intercambian los elementos.

 * Precondiciones:
 * - El arreglo [arr] debe tener un tamaño de 4 elementos (`arr.size == 4`).
 * - El tipo [T] debe ser comparable consigo mismo para permitir su ordenamiento.
 *
 * Postcondiciones:
 * - El arreglo original queda ordenado de manera que `arr[0] <= arr[1] <= arr[2] <= arr[3]`.
 *
 * @param T Tipo genérico comparable de los elementos.
 * @param arr Arreglo de tamaño fijo igual a cuatro.
 * @return El mismo arreglo [arr] ordenado de forma ascendente.
 * @throws SystemExitException Detiene la ejecución si el tamaño del arreglo es distinto a 4.
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
 * Algoritmo híbrido de ordenamiento por mezcla (Mergesort e inserción).
 *
 * Emplea la técnica "divide y vencerás". Optimiza el rendimiento interceptando casos base 
 * pequeños (N <= 4) llamando a funciones de comparaciones fijas, y tamaños moderados (5 <= N <= 100) 
 * resolviéndolos vía insertionSort.
 *
 * Precondiciones:
 * - El objeto [arr] no debe ser nulo.
 * - El tipo [T] debe ser comparable consigo mismo para permitir su ordenamiento.
 *
 * Postcondiciones:
 * - Los elementos del arreglo original quedan completamente ordenados de manera ascendente.
 *
 * @param T Tipo genérico comparable de los elementos.
 * @param arr Arreglo dinámico que se desea ordenar.
 * @return El mismo arreglo [arr] en orden ascendente.
 * @see ordenaDos
 * @see ordenaTres
 * @see ordenaCuatro
 * @see insertionSort
 * @see merge
 */
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
 * Ordena un arreglo utilizando la estrategia Merge Sort de manera puramente iterativa.
 *
 * Elimina la pila de llamadas recursivas procesando el arreglo en etapas sucesivas mediante bloques 
 * de tamaño variable `k`, duplicando su tamaño en cada iteración completa (k = 1, 2, 4, 8,...)
 * y mezclándolos de forma local.
 *
 * Precondiciones:
 * - El objeto [arr] no debe ser nulo de forma estructural.
 * - El tipo [T] debe ser comparable consigo mismo para permitir su ordenamiento.
 *
 * Postcondiciones:
 * - El arreglo de entrada ordenado de forma ascendente.
 *
 * @param T Tipo genérico de datos comparables.
 * @param arr Arreglo inicial que se va a ordenar.
 * @return El mismo arreglo [arr] ordenado ascendentemente.
 */
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
            val z = arr.copyOfRange(a, c)
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

/**
 * Ordena un arreglo utilizando el algoritmo Heap Sort.
 *
 * Construye primero un montón máximo (max-heap) sobre el arreglo y luego extrae
 * repetidamente el elemento mayor hacia el final del arreglo, reduciendo el tamaño
 * del montón en cada paso.
 *
 * Para arreglos pequeños (0 a 4 elementos) y de tamaño moderado (5 a 100 elementos),
 * utiliza los procedimientos solicitados en el laboratorio.
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
 * @return El mismo arreglo [arr] ordenado ascendentemente.
 */
fun <T: Comparable<T>> heapsort(arr: Array<T>): Array<T>{
    //Comportamiento solicitado en el laboratorio
    when (arr.size) {
        0, 1 -> return arr
        2 -> return ordenaDos(arr)
        3 -> return ordenaTres(arr)
        4 -> return ordenaCuatro(arr)
        in 5..100 -> return insertionSort(arr)
    }
    /*Para no crear una clase que contenga la propiedad heapSize la declaramos
      como variable y la solicitamos en maxHeapify*/
    var heapSize = arr.size
    //BuildMaxHeap del libro INTRODUCTION TO ALGORITHMS
    for (i in (arr.size/2 - 1) downTo 0){
        maxHeapify(arr,i,heapSize)
    }

    //Inicio del heapsort del libro
    for (i in (arr.size -1) downTo 1){
        val temp = arr[0]
        arr[0]=arr[i]
        arr[i]=temp
        heapSize -= 1
        maxHeapify(arr,0, heapSize)
    }

    return arr
}

/**
 * Restablece la propiedad de montón máximo para el subárbol con raíz en el índice [i].
 *
 * Compara el nodo en [i] con sus hijos izquierdo y derecho, y si alguno de ellos es mayor,
 * intercambia el nodo con el mayor de los hijos y se aplica recursivamente sobre el subárbol afectado.
 *
 * Precondiciones:
 * - El objeto [arr] no debe ser nulo.
 * - El índice [i] debe pertenecer a un subárbol dentro de [heapSize].
 * - [heapSize] debe ser un tamaño válido del montón, menor o igual a `arr.size`.
 * - El tipo [T] debe ser comparable consigo mismo para permitir la comparación entre elementos.
 *
 * Postcondiciones:
 * - El subárbol con raíz en [i] cumple la propiedad de max-heap dentro del rango [0, heapSize).
 *
 * @param T Tipo genérico de datos comparables.
 * @param arr Arreglo que contiene el montón.
 * @param i Índice de la raíz del subárbol a ajustar.
 * @param heapSize Tamaño actual del montón válido en [arr].
 */
fun <T: Comparable<T>> maxHeapify(arr: Array<T>, i:Int, heapSize:Int){
    var largest: Int
    //le sumamos 1 a las variables ya que la raiz es 0 y no 1 como en el libro
    val l = 2*i + 1
    val r = 2*i + 2
    if (l < heapSize && arr[l] > arr[i]){
        largest = l
    } else {largest = i}

    if (r < heapSize && arr[r]>arr[largest]){
        largest = r
    }
    if (largest != i){
        val temp = arr[i]
        arr[i]=arr[largest]
        arr[largest]=temp

        maxHeapify(arr,largest,heapSize)
    }
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
fun <T: Comparable<T>> quicksort(arr: Array<T>, p: Int, r: Int): Array<T> {
    if (p < r) {
        val q = partition(arr, p, r)
        quicksort(arr, p, q - 1)
        quicksort(arr, q + 1, r)
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

/**
 * Ordena un arreglo utilizando el algoritmo de ordenamiento Quick Sort con pivote dual.
 *
 * Selecciona dos pivotes (p y q) y particiona el arreglo en tres secciones: elementos menores a p, 
 * elementos entre p y q, y elementos mayores a q. Luego, aplica recursivamente el mismo proceso
 * sobre cada sección.
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
 * @param left Índice de inicio del segmento a ordenar.
 * @param right Índice final del segmento a ordenar.
 * @return El mismo arreglo [arr] ordenado ascendentemente dentro del rango especificado.
 */
fun <T: Comparable<T>> dualPivotQuicksort(arr: Array<T>, left: Int, right: Int): Array<T> {
    if ((right - left) >= 1) {
        var p:T
        var q:T
        if (arr[left] < arr[right]){
            p = arr[left]
            q = arr[right]
        } else {
            p = arr[right]
            q = arr[left]
        }
        var k = left + 1
        var l = k
        var g = right - 1
        while (k<=g) {
            if (arr[k] < p) {
                val temp = arr[k]
                arr[k] = arr[l]
                arr[l] = temp
                l++
            } else if (arr[k] >= q) {
                while (arr[g] > q && k < g) {
                    g--
                }
                val temp = arr[k]
                arr[k] = arr[g]
                arr[g] = temp
                g--
                if (arr[k] < p) {
                    val temp2 = arr[k]
                    arr[k] = arr[l]
                    arr[l] = temp2
                    l++
                }
            }
            k++
        }
        l--
        g++
        arr[left] = arr[l]
        arr[l] = p
        arr[right] = arr[g]
        arr[g] = q
        dualPivotQuicksort(arr, left, l - 1)
        dualPivotQuicksort(arr, l + 1, g - 1)
        dualPivotQuicksort(arr, g + 1, right)
    }
    return arr
}

/**
 * Ordena un arreglo utilizando el algoritmo de ordenamiento Counting Sort.
 *
 * Cuenta la frecuencia de cada elemento en el arreglo y luego reconstruye el arreglo ordenado
 * basándose en estas frecuencias. Es eficiente para rangos pequeños de enteros.
 *
 * Precondiciones:
 * - El objeto [A] no debe ser nulo.
 * - Los elementos del arreglo deben ser números enteros no negativos.
 *
 * Postcondiciones:
 * - El arreglo original [A] queda ordenado en forma ascendente.
 *
 * @param A Arreglo inicial desordenado de números.
 * @return Un nuevo arreglo con los elementos de [A] ordenados ascendentemente.
 */
fun countingSort(A: Array<Int>): Array<Int> {
    val k=A.maxOrNull()?.toInt() ?: 0
    val C = Array(k + 1) {0}
    val B = Array(A.size) {0}
    for (i in 0 until A.size) {
        C[A[i]]++        
    }
    for (i in 1..k) {
        C[i] += C[i - 1]
    }
    for (i in A.size - 1 downTo 0) {
        B[C[A[i]] - 1] = A[i]
        C[A[i]]--
    }
    return B
}

/**
 * Ordena un arreglo de números de punto flotante utilizando el algoritmo Radix Sort.
 *
 * Aplica Counting Sort de manera estable para cada dígito, comenzando desde el dígito menos significativo
 * hasta el más significativo. Convierte los números de punto flotante a enteros eliminando el punto
 * decimal.
 *
 * @param A Arreglo de números de punto flotante a ordenar.
 * @param actual_d Índice del dígito actual a procesar.
 * @param d Número total de dígitos en los números del arreglo.
 * @return El mismo arreglo [A] ordenado ascendentemente.
 */
fun radixCountingSort(A: Array<Double>, actual_d: Int, d: Int): Array<Double> {
    val A_digits = Array(A.size) {0}
    for (i in A.indices) {
        A_digits[i] = A[i].toString().replace(".", "").replace("-", "").padEnd(d, '0')[actual_d].digitToInt()
    }
    val k=A_digits.maxOrNull()?.toInt() ?: 0
    val C = Array(k + 1) {0}
    val B = Array(A.size) {0.0}
    for (i in 0 until A.size) {
        C[A_digits[i]]++        
    }
    for (i in 1..k) {
        C[i] += C[i - 1]
    }
    for (i in A.size - 1 downTo 0) {
        B[C[A_digits[i]] - 1] = A[i]
        C[A_digits[i]]--
    }
    return B
}

/**
 * Ordena un arreglo de números de punto flotante utilizando el algoritmo Radix Sort.
 *
 * Determina la cantidad máxima de dígitos en los números del arreglo y aplica Counting Sort
 * para cada dígito, comenzando desde el dígito menos significativo hasta el más significativo.
 *
 * Precondiciones:
 * - El objeto [A] no debe ser nulo.
 *
 * Postcondiciones:
 * - El arreglo original [A] queda ordenado en forma ascendente.
 *
 * @param A Arreglo inicial desordenado de números de punto flotante.
 * @return El mismo arreglo [A] ordenado ascendentemente según el digito actual sobre el que se está procesando.
 */
fun radixSort(A: Array<Double>): Array<Double> {
    if (A.isEmpty()) return A

    var d = 0
    for (num in A) {
        val len = num.toString().replace(".", "").replace("-", "").length
        if (len > d) {
            d = len
        }
    }
    var B = A.copyOf()
    for (i in d - 1 downTo 0) {
        B = radixCountingSort(B, i, d)
    }
    for (i in A.indices) {
        A[i] = B[i]
    }
    return A
}