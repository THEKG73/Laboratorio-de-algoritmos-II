import kotlin.random.Random

fun main(args: Array<String>) {
    // Validación del argumento de entrada
    if (args.isEmpty()) {
        println("Error: Debe proporcionar el número de elementos n")
        System.exit(1)
    }

    val n = try {
        args[0].toInt()
    } catch (e: Exception) {
        println("Error: n debe ser un número entero.")
        System.exit(1)
        0
    }

    // Generación de las claves y valores en el intervalo [0, n/3]
    val limite = n / 3
    val datos = Array(n) {
        val clave = Random.nextInt(0, limite + 1)
        Pair(clave, clave.toString())
    }

    println("Iniciando pruebas para n = $n...")

    // Prueba para Tabla de Hash por Encadenamiento
    val tablaChaining = HashTableChaining()
    val startTimeChaining = System.nanoTime()

    for (par in datos) {
        val (clave, valor) = par
        if (tablaChaining.existe(clave)) {
            tablaChaining.eliminar(clave)
        } else {
            tablaChaining.agregar(clave, valor)
        }
    }

    val endTimeChaining = System.nanoTime()
    val timeChaining = (endTimeChaining - startTimeChaining).toDouble()/1_000_000_000.0

    // Prueba para Cuco Hashing
    val tablaCuckoo = CuckooHashTable()
    val startTimeCuckoo = System.nanoTime()

    for (par in datos) {
        val (clave, valor) = par
        if (tablaCuckoo.existe(clave)) {
            tablaCuckoo.eliminar(clave)
        } else {
            tablaCuckoo.agregar(clave, valor)
        }
    }

    val endTimeCuckoo = System.nanoTime()
    val timeCuckoo = (endTimeCuckoo - startTimeCuckoo).toDouble() / 1_000_000_000.0

    // Resultados
    println("---------------------------------------------------")
    println("Tabla de Hash (Encadenamiento): ${String.format("%.5f", timeChaining)} segundos")
    println("Tabla de Hash (Cuckoo Hashing): ${String.format("%.5f", timeCuckoo)} segundos")
    println("---------------------------------------------------")
}