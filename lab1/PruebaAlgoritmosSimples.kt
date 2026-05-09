// Importacion de libreria para medir el tiempo de ejecucion
import kotlin.system.measureTimeMillis
// Importacion de libreria para obtener numeros reales aleatorios
import kotlin.random.Random

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Error: Se esperan argumentos")
    }

    val secuencia = args[0]
    val n = args[1].toInt()

    if (secuencia != "random" || secuencia != "randomd"){
        println("Secuencia inválida")
        return -1
    }

    val sec: Any

    if (secuencia == "random"){
        for (i in 0 until n) {
            sec[i] = (0..n).random()
        }
    } else {
        for (i in 0 until n) {
            sec[i] = Random.nextDouble()
        }
    }
    

    println("Secuencia original: ${sec.joinToString(", ")}")

    // Medir el tiempo de ejecucion del algoritmo de ordenamiento mediante la libreria de Kotlin
    // val time = measureTimeMillis {
    //     bubbleSort(sec)
    // }

    // Medir el tiempo de ejecucion del algoritmo de ordenamiento mediante la clase System
    // val startTime = System.currentTimeMillis() Para capturar en milisegundos
    val startTime = System.nanoTime()

    var secuenciaOrdenada = selectionSort(sec)

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
