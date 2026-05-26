/**
 * Punto de entrada principal para la ejecución de pruebas empíricas de rendimiento
 * sobre los algoritmos de ordenamiento Bubble Sort e Insertion Sort.
 *
 * La función recibe desde la línea de comandos la información para generar secuencias de datos aleatorios, 
 * mide los tiempos de ejecución en nanosegundos tras múltiples intentos, calcula mínimo, máximo y promedio 
 * de tiempo de ejecución y finalmente exporta una gráfica comparativa de los resultados.
 *
 * Precondiciones:
 * - El arreglo [args] debe contener un número de elementos mayor o igual a tres (args.size >= 3).
 * - args[0] debe ser un string que represente un número entero válido y positivo (args[0]> 0).
 * - args[1] debe ser un string no vacío que represente un nombre de archivo válido para la gráfica.
 * - Todos los elementos desde args[2] hasta args[args.size - 1] deben ser strings convertibles a enteros positivos.
 *
 * Postcondiciones:
 * - Se habrán ejecutado `tIntentos` (cantidad de intentos) por cada combinación de algoritmo y tamaño de muestra .
 * - Se imprime en la consola estándar el desglose de tiempos (Avg, Min, Max) de cada prueba.
 * - Se genera y guarda un archivo de imagen con el nombre especificado en `graphName`.
 * - Si alguna comprobación de ordenamiento falla o el gráfico no se puede renderizar, el programa notificará el error en la consola.
 *
 * @param args Arreglo de cadenas de texto (Array<String>):
 * - args[0]: Cantidad de intentos por tamaño de muestra.
 * - args[1]: Nombre del archivo de salida para la gráfica.
 * - args[2...]: Uno o más tamaños de arreglos (N) a evaluar.
 * * @return Esta función no devuelve ningún valor (`Unit`).
 * @throws NumberFormatException Si los argumentos que corresponden a números no pueden ser parseados a `Int`.
 * @throws SystemExitException (A través de `System.exit(1)`) Si la cantidad de argumentos es menor a 3 o si un algoritmo falla en ordenar los datos de prueba.
 * @see bubbleSort
 * @see insertionSort
 * @see estaOrdenAscendente
 * @see plotRuntime
 */
fun main(args: Array<String>) {
    // Verificar que tengamos los argumentos mínimos (intentos, output y al menos un tamaño)
    if (args.size < 3) {
        println("Error: El programa debe recibir intentos, nombre de salida y al menos un tamaño.")
        System.exit(1)
    }

    // 1. Captura de argumentos desde el script de Bash
    val tIntentos = args[0].toInt()
    val graphName = args[1]

    // Todos los demás argumentos a partir del índice 2 son los tamaños 'n'
    val tamaños = Array<Int>(args.size - 2) { i -> args[i + 2].toInt() }

    val algoritmos = arrayOf("Bubble sort", "Insertion sort")
    val totalMuestras = algoritmos.size * tamaños.size

    // Estructuras de datos planas requeridas por plotRuntime
    val algorithmsLabels = Array<String>(totalMuestras) { "" }
    val numElements = Array<Int>(totalMuestras) { 0 }
    val averageTimes = Array<Double>(totalMuestras) { 0.0 }
    val minTimes = Array<Double>(totalMuestras) { 0.0 }
    val maxTimes = Array<Double>(totalMuestras) { 0.0 }

    println("Iniciando pruebas empíricas desde pord.sh ($tIntentos intentos)...")

    var indiceMuestra = 0

    for (algo in algoritmos) {
        for (n in tamaños) {
            algorithmsLabels[indiceMuestra] = algo
            numElements[indiceMuestra] = n

            val tiemposDeIntentos = DoubleArray(tIntentos)

            for (intento in 0 until tIntentos) {
                val sec = Array(n) { (0..n).random() }
                
                val startTime = System.nanoTime()
                if (algo == "Bubble sort") {
                    bubbleSort(sec)
                } else {
                    insertionSort(sec)
                }
                val endTime = System.nanoTime()

                if (!estaOrdenAscendente(sec)) {
                    println("Error: ¡$algo falló para N = $n!")
                    System.exit(1)
                }

                tiemposDeIntentos[intento] = (endTime - startTime).toDouble() / 1_000_000_000.0
            }

            val min = tiemposDeIntentos.minOrNull() ?: 0.0
            val max = tiemposDeIntentos.maxOrNull() ?: 0.0
            val avg = tiemposDeIntentos.average()

            minTimes[indiceMuestra] = min
            maxTimes[indiceMuestra] = max
            averageTimes[indiceMuestra] = avg

            println("[$algo] N = $n -> Avg: ${String.format("%.5f", avg)}s (Min: ${String.format("%.5f", min)}s, Max: ${String.format("%.5f", max)}s)")
            indiceMuestra++
        }
    }

    // Generar la gráfica usando el nombre dinámico elegido por el usuario
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
