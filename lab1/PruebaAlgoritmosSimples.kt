// Importación de librería para medir el tiempo de ejecución
import kotlin.system.measureTimeMillis
// Importación de librería para obtener numeros reales aleatorios
import kotlin.random.Random

fun main(args: Array<String>) {

    // Verificación de argumento no vacío
    if (args.isEmpty()) {
        println("Error: Se esperan argumentos")
    }

    // Obtenemos el tipo y el tamaño de la secuencia
    var secuencia = ""
    var n = 0

    for (i in args.indices step 2) {
        if (args[i] == "-s") {
            secuencia = args[i + 1]
        } else if (args[i] == "-n") {
            n = args[i + 1].trim().toInt()
        }
    }

    // Verificación de argumentos no vacíos
    if (secuencia == "" || n == 0) {
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

fun <T: Comparable<T>> ejecutarAlgoritmos(arr:Array<T>) {
    println("Secuencia original: ${arr.joinToString()}")

    var error = 0
    // SELECTION SORT
    error = llamarOrdenamiento(arr, "selectionSort")
    if (error == -1) {return}

    // INSERTION SORT
    error = llamarOrdenamiento(arr, "insertionSort")
    if (error == -1) {return}

    // BUBBLE SORT
    error = llamarOrdenamiento(arr, "bubbleSort")
    if (error == -1) {return}
}
fun <T: Comparable<T>> estaOrdenAscendente (arr:Array<T>):Boolean{
    for (i in 0 until (arr.size-1)){
        if (arr[i]>arr[i+1]){
            return false
        }
    }
    return true
}

fun <T: Comparable<T>> llamarOrdenamiento(arr:Array<T>, algoritmo: String):Int {

    var tempArr = arr.copyOf()
    
    //Tiempo de inicio de la función de ordenamiento
    val startTime = System.currentTimeMillis()

    when (algoritmo) {
        "selectionSort" -> tempArr = selectionSort(tempArr)
        "insertionSort" -> tempArr = insertionSort(tempArr)
        "bubbleSort" -> tempArr = bubbleSort(tempArr)
    }

    // Tiempo de fin de la función de ordenamiento
    val endTime = System.currentTimeMillis()

    //Tiempo de ejecución del ordenamiento
    val time = endTime - startTime
    println("ALGORITMO: $algoritmo")
    println("Secuencia ordenada: ${tempArr.joinToString(", ")}")

    if (estaOrdenAscendente(tempArr)) {
        println("La secuencia está ordenada en orden ascendente")
    } else {
        println("La secuencia no está ordenada en orden ascendente, abortando ejecución")
        return -1
    }
    println("Tiempo de ejecución: $time ms")
    return 0
}