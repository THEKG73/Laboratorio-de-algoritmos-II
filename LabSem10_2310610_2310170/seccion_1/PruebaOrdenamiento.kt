// Importación de librería para obtener numeros reales aleatorios
import kotlin.random.Random

/**
 * Analiza los argumentos pasados por la línea de comandos, genera las estructuras de datos 
 * bajo las configuraciones de secuencia solicitadas y coordina las pruebas de rendimiento empíricas.
 *
 * Precondiciones:
 * - El arreglo [args] debe contener obligatoriamente al menos 5 elementos válidos.
 * - `args[0]` debe ser una cadena con identificadores de algoritmos separados por comas.
 * - `args[1]` debe ser un tipo de secuencia soportado.
 * - `args[2]` debe representar un número entero estrictamente positivo.
 * - `args[4]` debe ser una lista de tamaños separados por comas, sin duplicados y en orden estrictamente ascendente.
 *
 * Postcondiciones:
 * - Se ejecutan `intentos` de ordenamiento para cada algoritmo y tamaño especificado.
 * - Imprime en la consola estándar la media (`Avg`), desviación estándar (`DesvSt`), valores mínimos (`Min`) y máximos (`Max`) en segundos.
 * - Si `graphName` es distinto de `"SinGrafico.png"`, se exporta y persiste un archivo de imagen con la gráfica en el directorio raíz (`.`).
 * - En caso de detectar fallas en las validaciones, formatos incorrectos o errores de ordenamiento, el programa aborta inmediatamente la ejecución.
 *
 * @param args Argumentos de la línea de comandos pasados por consola:
 * - `args[0]`: Algoritmos de ordenamiento a evaluar separados por comas (códigos válidos: `ss`, `is`, `bs`, `mb`, `ms`, `mi`).
 * - `args[1]`: Identificador del tipo de secuencia de datos a generar para las pruebas.
 * - `args[2]`: Cantidad de intentos o repeticiones numéricas a realizar por muestra.
 * - `args[3]`: Nombre del archivo de salida para almacenar la gráfica o `"SinGrafico.png"`.
 * - `args[4]`: Tamaños de entrada ($N$) delimitados por comas (ej. `"100,200,300"`).
 * @return Esta función no devuelve ningún valor (`Unit`).
 * @throws NumberFormatException Si los intentos o los tamaños de muestra no pueden convertirse a un valor de tipo [Int].
 * @throws SystemExitException (A través de `System.exit(1)`) Si no se cumple alguna precondición lógica o un algoritmo falla en ordenar los datos.
 */
fun main(args: Array<String>) {

    // Verificar que tengamos los argumentos mínimos (intentos, output, secuencia, algoritmos y al menos un tamaño)
    if (args.size < 5) {
        println("Error: El programa debe recibir intentos, nombre de salida, secuencia, algoritmos y al menos un tamaño.")
        System.exit(1)
    }

    // Captura de argumentos desde el script de Bash y verificación de su formato
    val algorithms: Array<String> = args[0].split(',').toTypedArray()
    for (algo in algorithms) {
        if (algo.length < 2) {
            println("Error: los algoritmos parámetros fueron ingresados en formato incorrecto. Formato esperado: ms,bs,is")
            System.exit(1)
        }
    }
    var sequence = args[1].trim()
    val intentos = args[2].trim().toInt()
    val graphName = args[3].trim()
    val partes = args[4].split(",")
    val tamaños: Array<Int> = try {
        Array<Int>(partes.size) { i -> partes[i].trim().toInt() }
    } catch (e: Exception) {
        println("Error: Los tamaños deben ser enteros y deben ingresarse en formato correcto, ejemplo: 200,300,400")
        System.exit(1)
        arrayOf()
    }

    if (tamaños.size != tamaños.toSet().size || !estaOrdenAscendente(tamaños)) {
        println("Error: No deben haber tamaños repetidos ni estar en orden descendente")
        System.exit(1)
    }
    
    if (tamaños.size == 1 && graphName != "SinGrafico.png") {
        println("Error: Para un solo tamaño, no se puede generar una gráfica comparativa")
        System.exit(1)
    }

    val totalMuestras = algorithms.size * tamaños.size

    // Estructuras de datos planas requeridas por plotRuntime
    val algorithmsLabels = Array<String>(totalMuestras) { "" }
    val numElements = Array<Int>(totalMuestras) { 0 }
    val averageTimes = Array<Double>(totalMuestras) { 0.0 }
    val minTimes = Array<Double>(totalMuestras) { 0.0 }
    val maxTimes = Array<Double>(totalMuestras) { 0.0 }

    if (sequence == "sorted") {
        sequence = "sorte"
    }
    when (sequence.last()) {
        'd' -> {
            val sec = Array(tamaños.size) { Array(intentos) { Array(1) {0.0} } }
            for (n in tamaños.indices) {
                for (i in 0 until intentos) {
                    sec[n][i] = Array(tamaños[n]) { Random.nextDouble() }
                    if (sequence == "sortedd") {
                        sec[n][i].sort()
                    }
                }
            }

            println("Iniciando pruebas empíricas desde pord.sh ($intentos intentos)...")

            var indiceMuestra = 0

            for (algo in algorithms) {
                for (n in tamaños.indices) {
                    algorithmsLabels[indiceMuestra] = algo
                    numElements[indiceMuestra] = tamaños[n]

                    val tiemposDeIntentos = DoubleArray(intentos)

                    for (intent in 0 until intentos) {
                        tiemposDeIntentos[intent] = llamarOrdenamiento(sec[n][intent], algo)
                    }
                    val min = tiemposDeIntentos.minOrNull() ?: 0.0
                    val max = tiemposDeIntentos.maxOrNull() ?: 0.0
                    val avg = tiemposDeIntentos.average()
                    val desviacionEstandar = calcularDesviacionEstandar(tiemposDeIntentos, avg)

                    minTimes[indiceMuestra] = min
                    maxTimes[indiceMuestra] = max
                    averageTimes[indiceMuestra] = avg

                    println("[$algo] N = ${tamaños[n]} -> Avg: ${String.format("%.5f", avg)}s, DesvSt: ${String.format("%.5f", desviacionEstandar)}s, (Min: ${String.format("%.5f", min)}s, Max: ${String.format("%.5f", max)}s)")
                    indiceMuestra++
                }
            }
        }        
        else -> {
            val sec = Array(tamaños.size) { Array(intentos) { Array(1) {0} } }
            when (sequence) {
                "random" -> {
                    for (n in tamaños.indices) {
                        for (i in 0 until intentos) {
                            sec[n][i] = Array(tamaños[n]) { (0..tamaños[n]).random() }
                        }
                    }
                }
                "sorte" -> {
                    for (n in tamaños.indices) {
                        for (i in 0 until intentos) {
                            sec[n][i] = Array(tamaños[n]) { (0..tamaños[n]).random() }
                            sec[n][i].sort()
                        }
                    }
                }
                "inv" -> {
                    for (n in tamaños.indices) {
                        for (i in 0 until intentos) {
                            sec[n][i] = Array(tamaños[n]) { (0..tamaños[n]).random() }
                            sec[n][i].sortDescending()
                        }
                    }
                }
                "zu" -> {
                    for (n in tamaños.indices) {
                        for (i in 0 until intentos) {
                            sec[n][i] = Array(tamaños[n]) { (0..1).random() }
                        }
                    }
                }
                "media" -> {
                    for (n in tamaños.indices){
                        val secMedia = Array(tamaños[n]) {0}
                        var h = tamaños[n]
                        if (h%2 != 0) {
                            h--
                            secMedia[secMedia.size-1] = 1
                        }
                        for (i in 1 until h+1) {
                            if (i <= h/2) {
                                secMedia[i - 1] = i
                            } else {
                                secMedia[i - 1] = h - i + 1
                            }
                        }
                        for (i in 0 until intentos) {
                            sec[n][i] = secMedia.copyOf()
                        }
                    }
                }
                else -> {
                    println("Error: Secuencia inválida")
                    System.exit(1)
                }
            }
            println("Iniciando pruebas empíricas desde pord.sh ($intentos intentos)...")

            var indiceMuestra = 0
            
            for (algo in algorithms) {
                for (n in tamaños.indices) {
                    algorithmsLabels[indiceMuestra] = algo
                    numElements[indiceMuestra] = tamaños[n]

                    val tiemposDeIntentos = DoubleArray(intentos)

                    for (intent in 0 until intentos) {
                        tiemposDeIntentos[intent] = llamarOrdenamiento(sec[n][intent], algo)
                    }
                    val min = tiemposDeIntentos.minOrNull() ?: 0.0
                    val max = tiemposDeIntentos.maxOrNull() ?: 0.0
                    val avg = tiemposDeIntentos.average()
                    val desviacionEstandar = calcularDesviacionEstandar(tiemposDeIntentos, avg)

                    minTimes[indiceMuestra] = min
                    maxTimes[indiceMuestra] = max
                    averageTimes[indiceMuestra] = avg

                    println("[$algo] N = ${tamaños[n]} -> Avg: ${String.format("%.5f", avg)}s, DesvSt: ${String.format("%.5f", desviacionEstandar)}s, (Min: ${String.format("%.5f", min)}s, Max: ${String.format("%.5f", max)}s)")
                    indiceMuestra++
                }
            }
        }
    }

    // Generar la gráfica usando el nombre dinámico elegido por el usuario, solo si se han ingresado más de un tamaño para comparar
    if (graphName != "SinGrafico.png") {
        try {
            println("\nGenerando gráfica comparativa...")
            plotRuntime(
                "Laboratorio 2: Gráficas de Rendimiento",
                ".",
                graphName,
                "Resultados de la secuencia de números aleatorios",
                "Número de elementos (N)",
                "Tiempo de ejecución (segundos)",
                algorithmsLabels,
                numElements,
                minTimes,
                averageTimes,
                maxTimes
            )
            println("¡Gráfica guardada exitosamente como '$graphName'!")
        } catch (e: Exception) {
            println("Error al renderizar el gráfico: ${e.message}")
        }
    }
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
 * Calcula la desviación estándar de un conjunto de tiempos registrados.
 *
 * Calcula la desviación estándar de los tiempos de ejecución para medir qué tan dispersos están respecto al promedio.
 *
 * Precondiciones:
 * - El arreglo [tiempos] no debe ser nulo.
 * - El valor del [promedio] debe corresponder con la media aritmética real calculada previamente sobre el arreglo.
 *
 * Postcondiciones:
 * - El arreglo original no se modifica.
 *
 * @param tiempos Estructura de tipo [DoubleArray] contentiva de los tiempos individuales medidos en segundos.
 * @param promedio Valor numérico real que representa la media de los datos del arreglo.
 * @return Un valor de tipo [Double] con la desviación estándar calculada. Retorna `0.0` si el arreglo contiene $1$ o menos muestras.
 */
fun calcularDesviacionEstandar(tiempos: DoubleArray, promedio: Double): Double {
    if (tiempos.size <= 1) return 0.0
    var sumaCuadrados = 0.0
    for (t in tiempos) {
        sumaCuadrados += (t - promedio) * (t - promedio)
    }
    return kotlin.math.sqrt(sumaCuadrados / tiempos.size)
}

/**
 * Llama al algoritmo de ordenamiento especificado y mide su tiempo de ejecución.
 * Pre-condiciones: arr no es nulo, algoritmo es uno de los soportados.
 * Post-condiciones: Si el algoritmo no falla, se imprime en consola toda la información de
 * ejecución del algoritmo, incluyendo el tiempo de ejecución y la secuencia ordenada.
 * @param arr El arreglo a ordenar.
 * @param algoritmo El nombre del algoritmo de ordenamiento ("selectionSort", "insertionSort", "bubbleSort",...).
 * @param T Tipo genérico que debe ser Comparable.
 * @return Double indicando el tiempo de ejecución del algoritmo.
 */
@Suppress("UNCHECKED_CAST")
fun <T: Comparable<T>> llamarOrdenamiento(arr:Array<T>, algoritmo: String):Double {

    var tempArr = arr.copyOf()
    
    //Tiempo de inicio de la función de ordenamiento
    val startTime = System.nanoTime()

    when (algoritmo) {
        "ss" -> tempArr = selectionSort(tempArr)
        "is" -> tempArr = insertionSort(tempArr)
        "bs" -> tempArr = bubbleSort(tempArr)
        "mb" -> tempArr = mergesortInsertion(tempArr)
        "ms" -> tempArr = mergesort(tempArr)
        "mi" -> tempArr = mergesortIterativo(tempArr)
        "hs" -> tempArr = heapsort(tempArr)
        "qs" -> tempArr = quicksort(tempArr, 0, tempArr.size - 1)
        "qp" -> tempArr = dualPivotQuicksort(tempArr, 0, tempArr.size - 1)
        "cs" -> {
            val intArr = tempArr as? Array<Int> 
                ?: throw IllegalArgumentException("Counting Sort solo acepta arreglos de enteros.")
            tempArr = countingSort(intArr) as Array<T>
        }
        "rs" -> {
            val intArr = tempArr as? Array<Int> 
                ?: throw IllegalArgumentException("Radix Sort solo acepta arreglos de enteros.")
            tempArr = radixSort(intArr) as Array<T>
        }
        else -> {
            println("Error: Algoritmo de ordenamiento inválido")
            System.exit(1)
        }
    }

    // Tiempo de fin de la función de ordenamiento
    val endTime = System.nanoTime()

    //Tiempo de ejecución del ordenamiento (nanosegundos)
    val time = endTime - startTime

    if (!estaOrdenAscendente(tempArr)) {
        println("Error: ¡$algoritmo falló para N = ${tempArr.size}!")
        System.exit(1)
    }
    return time.toDouble()/1_000_000_000.0
}
