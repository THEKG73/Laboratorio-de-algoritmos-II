// Importación de librería para medir el tiempo de ejecución
import kotlin.system.measureTimeMillis
// Importación de librería para obtener numeros reales aleatorios
import kotlin.random.Random

/**
 * Función principal que analiza los argumentos de línea de comandos y ejecuta algoritmos de ordenamiento
 * en una secuencia generada.
 * Pre-condiciones: args debe contener -s y -n con valores válidos.
 * @param args Argumentos de línea de comandos: -s <tipo_secuencia> -n <tamaño>
 * @return Unit (nada).
 */
fun main(args: Array<String>) {

    // Verificar que tengamos los argumentos mínimos (intentos, output y al menos un tamaño)
    if (args.size < 5) {
        println("Error: El programa debe recibir intentos, nombre de salida y al menos un tamaño.")
        System.exit(1)
    }

    // Captura de argumentos desde el script de Bash
    val algorithms: Array<String> = args[0].split(',').toTypedArray()
    val sequence = args[1].trim()
    val intentos = args[2].trim().toInt()
    val graphName = args[3].trim()
    val tamaños: IntArray = args[4].split(',').toTypedArray().map { it.trim().toInt() }.toIntArray()

    val totalMuestras = algorithms.size * tamaños.size

    // Estructuras de datos planas requeridas por plotRuntime
    val algorithmsLabels = Array<String>(totalMuestras) { "" }
    val numElements = Array<Int>(totalMuestras) { 0 }
    val averageTimes = Array<Double>(totalMuestras) { 0.0 }
    val minTimes = Array<Double>(totalMuestras) { 0.0 }
    val maxTimes = Array<Double>(totalMuestras) { 0.0 }

    for (n in tamaños) {
        for (t in intentos) {
            when (sequence.last()) {
                'd' -> {
                    val sec = Array(n) { Random.nextDouble() }
                    if (sequence == "sortedd") {
                        sec.sort()
                    }
                    for (algo in algorithms) {
                        llamarOrdenamiento(sec, algo)
                    }
                }
                else -> {
                    val sec = IntArray(n)
                    when (sequence) {
                        "random" -> {
                            for (i in 0 until n) {
                                sec[i] = Random.nextInt(0, n + 1)
                            }
                        }`
                        "sorted" -> {
                            for (i in 0 until n) {
                                sec[i] = Random.nextInt(0, n + 1)
                                sec.sort()
                            }
                        }
                        "inv" -> {
                            for (i in 0 until n) {
                                sec[i] = Random.nextInt(0, n + 1)
                                sec.sortDescending()
                            }
                        }
                        "zu" -> {
                            for (i in 0 until n) {
                                sec[i] = Random.nextInt(2)
                            }
                        }
                        "media" -> {
                            var h = n
                            if (n%2 != 0) {
                                h = n - 1
                            }
                            for (i in 1 until h+1) {
                                    if (i <= h/2) {
                                        sec[i - 1] = i
                                    } else {
                                        sec[i - 1] = h - i + 1
                                    }
                            }
                            sec[n-1] = 1
                        }
                    }
                    for (algo in algorithms) {
                        llamarOrdenamiento(sec, algo)
                    }
                }
            }
        }
    }
                

    // Obtenemos el tipo y el tamaño de la secuencia
    var secuencia = ""
    var n = -1
    for (i in args.indices step 2) {
        if (args[i].trim() == "-s") {
            secuencia = args[i + 1].trim()
        } else if (args[i].trim() == "-n") {
            n = args[i + 1].trim().toInt()
        }
    }


    // Verificación de argumentos no vacíos
    if (secuencia == "" || n == -1) {
        println("Error: Se esperan argumentos -s <secuencia> -n <tamaño>")
        return
    }


    // Verificación de secuencia válida
    if (secuencia != "random" && secuencia != "randomd"){
        println("Secuencia inválida")
        return
    }

    // Generación de la secuencia de tamaño n sin ordenar y ejecución de los algoritmos de ordenamiento
    when (secuencia) {
        "random" -> {
            val sec = Array(n) {Random.nextInt(0, n+1)}
            ejecutarAlgoritmos(sec)
        }
        "randomd" -> {
            val sec = Array(n) {Random.nextDouble()}
            ejecutarAlgoritmos(sec)
        }
    }
}

/**
 * Ejecuta todos los algoritmos de ordenamiento en el arreglo dado.
 * Pre-condiciones: arr no es nulo.
 * Post-condiciones: check será true si todos los algoritmos de ordenamiento son exitosos, false en caso contrario.
 * @param arr El arreglo a ordenar.
 * @param T Tipo genérico que debe ser Comparable.
 * @return Unit (nada).
 */
fun <T: Comparable<T>> ejecutarAlgoritmos(arr:Array<T>) {
    println("Secuencia original: ${arr.joinToString()}")

    var check = true
    // SELECTION SORT
    check = llamarOrdenamiento(arr, "selectionSort")
    if (!check) {return}

    // INSERTION SORT
    check = llamarOrdenamiento(arr, "insertionSort")
    if (!check) {return}

    // BUBBLE SORT
    check = llamarOrdenamiento(arr, "bubbleSort")
    if (!check) {return}
}
/**
 * Verifica si el arreglo está ordenado en orden ascendente.
 * Pre-condiciones: arr no es nulo.
 * Post-condiciones: La función devuelve true si el arreglo está ordenado ascendentemente, false en caso contrario.
 * @param arr El arreglo a verificar.
 * @param T Tipo genérico que debe ser Comparable.
 * @return Boolean indicando si el arreglo está ordenado en orden ascendente o no.
 */
fun <T: Comparable<T>> estaOrdenAscendente (arr:Array<T>):Boolean{
    for (i in 0 until (arr.size-1)){
        if (arr[i]>arr[i+1]){
            return false
        }
    }
    return true
}

/**
 * Llama al algoritmo de ordenamiento especificado y mide su tiempo de ejecución.
 * Pre-condiciones: arr no es nulo, algoritmo es uno de los soportados.
 * Post-condiciones: Si el algoritmo no falla, se imprime en consola toda la información de
 * ejecución del algoritmo, incluyendo el tiempo de ejecución y la secuencia ordenada.
 * @param arr El arreglo a ordenar.
 * @param algoritmo El nombre del algoritmo de ordenamiento ("selectionSort", "insertionSort", "bubbleSort").
 * @param T Tipo genérico que debe ser Comparable.
 * @return Boolean indicando si el algoritmo fue exitoso o no.
 */
fun <T: Comparable<T>> llamarOrdenamiento(arr:Array<T>, algoritmo: String):Boolean {

    var tempArr = arr.copyOf()
    
    //Tiempo de inicio de la función de ordenamiento
    val startTime = System.nanoTime()

    when (algoritmo) {
        "ss" -> tempArr = selectionSort(tempArr)
        "is" -> tempArr = insertionSort(tempArr)
        "bs" -> tempArr = bubbleSort(tempArr)
    }

    // Tiempo de fin de la función de ordenamiento
    val endTime = System.nanoTime()

    //Tiempo de ejecución del ordenamiento (nanosegundos)
    val time = endTime - startTime

    println("ALGORITMO: $algoritmo")
    println("Secuencia ordenada: ${tempArr.joinToString(", ")}")

    if (estaOrdenAscendente(tempArr)) {
        println("La secuencia está ordenada en orden ascendente")
    } else {
        println("La secuencia no está ordenada en orden ascendente, abortando ejecución")
        return false
    }
    println("Tiempo de ejecución: $time ns")
    return true
}
