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