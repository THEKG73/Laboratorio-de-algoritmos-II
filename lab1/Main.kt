// Importacion de libreria para medir el tiempo de ejecucion
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Error: Se esperan argumentos")
    }

    val n = args[0].toInt()
    val sec: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        sec[i] = (0..n).random()

    }
    println("Secuencia original: ${sec.joinToString(", ")}")

    // Medir el tiempo de ejecucion del algoritmo de ordenamiento mediante la libreria de Kotlin
    // val time = measureTimeMillis {
    //     bubbleSort(sec)
    // }

    // Medir el tiempo de ejecucion del algoritmo de ordenamiento mediante la clase System
    // val startTime = System.currentTimeMillis() Para capturar en milisegundos
    val startTime = System.nanoTime()

    bubbleSort(sec)

    // val endTime = System.currentTimeMillis() Para capturar en milisegundos
    val endTime = System.nanoTime()
    val time = endTime - startTime
    
    println("Secuencia ordenada: ${sec.joinToString(", ")}")

    if (estaOrdenAscendente(sec)) {
        println("La secuencia está ordenada en orden ascendente")
    } else {
        println("La secuencia no está ordenada en orden ascendente")
    }
    println("Tiempo de ejecución: $time ns")

}

fun estaOrdenAscendente (arr:Array<Int>):Boolean{
    for (i in 0 until (arr.size-1)){
        if (arr[i]>arr[i+1]){
            return false
        }
    }
    return true
}
