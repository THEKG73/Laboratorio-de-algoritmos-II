import java.io.File

/**
 * Programa principal que procesa un archivo de entrada para ordenar panquecas en colas.
 *
 * Este programa lee un archivo de entrada que contiene información sobre varias colas de panquecas,
 * y genera una salida que indica los movimientos necesarios para ordenar cada cola de panquecas.
 *
 * Precondiciones:
 * - El archivo de entrada debe existir y ser accesible.
 * - El primer valor del archivo debe ser un número entero que indique la cantidad de colas.
 * - Cada cola debe estar representada en el archivo con un formato específico, donde primero se dice el numero de cola, y
 *   cada línea posterior contiene el tamaño de cada panqueca en la cola correspondiente.
 *
 * Postcondiciones:
 * - Se imprime en la salida estándar la secuencia de movimientos necesarios para ordenar cada cola de panquecas.
 *
 * @param args Arreglo de argumentos de línea de comandos. Se espera que contenga al menos un argumento: el nombre del archivo de entrada.
 */
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Error: Debe proporcionar el archivo de entrada")
        System.exit(1)
    }

    // Leemos todas las líneas y las pasamos a un arreglo estático estándar
    val lines: Array<String> = try {
        File(args[0]).readLines().toTypedArray()
    } catch (e: Exception) {
        println("Error al leer el archivo: ${e.message}")
        System.exit(1)
        return
    }

    if (lines.isEmpty()) return

    val numColas = lines[0].trim().toIntOrNull() ?: return

    val temp_string = lines.drop(1).joinToString(",")
    val colas: Array<Array<Int>> = temp_string.split(Regex("Cola\\d+,?"))
        .filter {it.isNotEmpty()}
        .map {bloque -> bloque.split(",")
                .filter {it.isNotEmpty()}
                .map {it.toInt()}
                .reversed()
                .toTypedArray()
        }
        .toTypedArray()
    val salida = Array<String>(numColas*2) {""}
    for (i in 0 until numColas) {
        salida[i*2] = "Cola${i+1}"
        var movimientos = StringBuilder()
        var cola_actual = colas[i]
        val cola_actual_sort = cola_actual.sortedDescending().toTypedArray()
        for ((index, elemento) in cola_actual_sort.withIndex()) {
            val piso = cola_actual.indexOf(elemento)
            if (piso == index) continue
            else if (piso != cola_actual.size - 1) {
                cola_actual = voltear(cola_actual, piso)
                movimientos.append("${piso+1} ")
            }
            cola_actual = voltear(cola_actual, index)
            movimientos.append("${index+1} ")
            if (cola_actual.contentEquals(cola_actual_sort)) break
        }
        movimientos.append("0")
        salida[i*2+1] = movimientos.toString().trim()
    }
    println(salida.joinToString("\n"))
}

/**
 * Invierte el orden de las panquecas a partir de un piso dado.
 *
 * Toma un arreglo [A] y un índice [piso], y voltea los elementos desde [piso] hasta el final del arreglo,
 * revirtiendo su orden.
 *
 * Precondiciones:
 * - El objeto [A] no debe ser nulo.
 * - El índice [piso] debe estar dentro del rango válido del arreglo (0 <= piso < A.size).
 *
 * Postcondiciones:
 * - Los elementos del arreglo [A] desde el índice [piso] hasta el final quedan invertidos.
 *
 * @param A Arreglo de enteros a modificar.
 * @param piso Índice a partir del cual se realizará la inversión de los elementos.
 * @return El mismo arreglo [A] con los elementos invertidos desde el índice especificado.
 */
fun voltear(A: Array<Int>, piso: Int): Array<Int> {
    var count = 1
    for (i in piso until (piso + (A.size - piso)/2)) {
        val t = A[i]
        A[i] = A[A.size - count]
        A[A.size - count] = t
        count++
    }
    return A
}