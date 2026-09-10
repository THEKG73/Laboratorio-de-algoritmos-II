import java.io.File

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