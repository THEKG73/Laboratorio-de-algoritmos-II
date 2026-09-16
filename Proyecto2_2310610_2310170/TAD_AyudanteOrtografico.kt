import java.io.File
import java.io.FileNotFoundException

/**
 * Implementación del Tipo Abstraído de Datos (TAD) Ayudante Ortográfico.
 *
 * Administra un diccionario de palabras agrupadas en un arreglo de 27 instancias
 * del TAD PMLI (26 letras del alfabeto en minuscula + 'ñ').
 *
 * @property dicc Arreglo de 27 instancias de [TAD_PMLI], donde los índices 0 a 25 corresponden a 'a'..'z' y el índice 26 a 'ñ'.
 *
 * Precondición: Ninguna.
 * Postcondición: Inicializa el arreglo [dicc] con 27 instancias de [TAD_PMLI], cada una asociada a su letra correspondiente ('a'..'z', 'ñ').
 */
class TAD_AyudanteOrtografico {

    // Tamaño fijo del arreglo según la especificación del TAD (MAX = 27)
    private val MAX: Int = 27

    /**
     * Estructura dicc: Arreglo de 27 instancias de TAD_PMLI.
     * Casillas 0 a 25 correspondientes a 'a'..'z', y la casilla 26 a 'ñ'.
     */
    val dicc: Array<TAD_PMLI>

    init {
        // Inicialización de la estructura dicc (equivale a crearAyudante)
        dicc = Array(MAX) { i ->
            if (i < 26) {
                TAD_PMLI(('a'.code + i).toChar())
            } else {
                TAD_PMLI('ñ')
            }
        }
    }

    /**
     * Devuelve el índice correspondiente en el arreglo [dicc] para una letra dada.
     *
     * @param c Carácter alfabético en minúscula ('a'..'z' o 'ñ').
     * @return Entero en el rango 0..26 asociado al carácter [c].
     * @throws IllegalArgumentException Si [c] no pertenece al alfabeto permitido ('a'..'z', 'ñ').
     *
     * **Precondición:** [c] debe ser un carácter alfabético minúsculo válido ('a'..'z' o 'ñ').
     * **Postcondición:** Retorna la posición en el arreglo sin modificar el estado del objeto.
     */
    private fun obtenerIndice(c: Char): Int {
        return when (c) {
            in 'a'..'z' -> c - 'a'
            'ñ' -> 26
            else -> throw IllegalArgumentException("Carácter '$c' fuera del alfabeto válido.")
        }
    }

    /**
     * Carga palabras desde un archivo de texto hacia la estructura del diccionario.
     *
     * @param fname Nombre o ruta del archivo de texto a leer.
     * @throws IllegalArgumentException Si el archivo no existe, no es válido o contiene palabras no válidas.
     *
     * **Precondición:** El archivo [fname] debe existir, ser un archivo accesible y cada línea no vacía debe contener una palabra válida según [esPalabraValida].
     * **Postcondición:** Todas las palabras válidas leídas son agregadas en su correspondiente estructura [TAD_PMLI] dentro del arreglo [dicc].
     */
    fun cargarDiccionario(fname: String) {
        val file = File(fname)
        require(file.exists() && file.isFile) {
            "Precondición violada en cargarDiccionario: El archivo '$fname' no existe o no es un archivo válido."
        }

        file.forEachLine { line ->
            val palabra = line.trim()
            if (palabra.isNotEmpty()) {
                require(esPalabraValida(palabra)) {
                    "Precondición violada en cargarDiccionario: La palabra '$palabra' en el archivo no es una palabra válida."
                }
                val idx = obtenerIndice(palabra[0])
                dicc[idx].agregarPalabra(palabra)
            }
        }
    }

    /**
     * Elimina una palabra específica del diccionario si se encuentra presente.
     *
     * @param p Palabra que se desea remover.
     * @throws IllegalArgumentException Si [p] no es una palabra válida.
     *
     * **Precondición:** [esPalabraValida](p) debe retornar `true`.
     * **Postcondición:** Si [p] pertenecía a [dicc], es removida del [TAD_PMLI] correspondiente a su inicial. Si no pertenecía, la estructura no cambia.
     */
    fun borrarPalabra(p: String) {
        require(esPalabraValida(p)) {
            "Precondición violada en borrarPalabra: La cadena '$p' no es una palabra válida."
        }
        val idx = obtenerIndice(p[0])
        if (dicc[idx].buscarPalabra(p)) {
            dicc[idx].eliminarPalabra(p)
        }
    }

    /**
     * Analiza un archivo de entrada para identificar palabras inválidas no presentes en el diccionario
     * y genera un archivo de salida con las sugerencias de corrección correspondientes.
     *
     * @param finput Ruta del archivo de entrada que contiene el texto a evaluar.
     * @param foutput Ruta del archivo de salida donde se escribirán los resultados.
     * @throws IllegalArgumentException Si el archivo de entrada [finput] no existe o no es accesible.
     *
     * **Precondición:** [finput] debe ser un archivo de texto existente y legible.
     * **Postcondición:** Se crea o sobrescribe el archivo [foutput] con cada palabra desconocida encontrada,
     *                   seguida de sus 4 palabras más cercanas del diccionario ordenadas por distancia Damerau-Levenshtein.
     */
    fun corregirTexto(finput: String, foutput: String) {
        val fileIn = File(finput)
        require(fileIn.exists() && fileIn.isFile) {
            "Precondición violada en corregirTexto: El archivo de entrada '$finput' no existe."
        }

        val contenido = fileIn.readText()
        
        // Expresión regular que extrae secuencias continuas de 'a'..'z' y 'ñ'
        val regex = Regex("[a-zñ]+")
        val palabrasValidasEncontradas = regex.findAll(contenido).map { it.value }.toList()

        // Recopilar todas las palabras guardadas actualmente en todo el diccionario
        val todasLasPalabrasDicc = mutableListOf<String>()
        for (pmli in dicc) {
            todasLasPalabrasDicc.addAll(pmli.palabras.obtenerTodas())
        }

        val writer = File(foutput).bufferedWriter()

        try {
            for (palabra in palabrasValidasEncontradas) {
                val idx = obtenerIndice(palabra[0])
                
                // Si la palabra no se encuentra en el diccionario, buscamos recomendaciones
                if (!dicc[idx].buscarPalabra(palabra)) {
                    val sugerencias = obtenerCuatroMasCercanas(palabra, todasLasPalabrasDicc)
                    val lineaSalida = "$palabra, " + sugerencias.joinToString(", ")
                    writer.write(lineaSalida)
                    writer.newLine()
                }
            }
        } finally {
            writer.close()
        }
    }

    /**
     * Despliega en la salida estándar todas las palabras almacenadas en el diccionario,
     * agrupadas por letra inicial en orden lexicográfico.
     *
     * **Precondición:** Ninguna (True).
     * **Postcondición:** Imprime el contenido de [dicc] por la salida estándar sin modificar los elementos almacenados.
     */
    fun imprimirDiccionario() {
        for (pmli in dicc) {
            if (pmli.palabras.tamano() > 0) {
                println("=== Letra '${pmli.letra}' ===")
                pmli.mostrarPalabras()
            }
        }
    }


    /**
     * Selecciona del diccionario las 4 palabras con menor distancia de edición respecto a la palabra dada.
     *
     * En caso de empate en la distancia, desempata lexicográficamente. Si el diccionario
     * cuenta con menos de 4 palabras, se rellenan las posiciones restantes con cadenas vacías `""`.
     *
     * @param palabra Palabra para la cual se buscan alternativas o correcciones.
     * @param diccionarioCompleto Lista con la totalidad de palabras almacenadas en el diccionario.
     * @return Lista de exactamente 4 elementos de tipo [String] con las sugerencias obtenidas.
     *
     * **Precondición:** [diccionarioCompleto] no debe ser nulo.
     * **Postcondición:** Retorna exactamente 4 cadenas de texto ordenadas por relevancia sin alterar la lista original.
     */
    private fun obtenerCuatroMasCercanas(
        palabra: String,
        diccionarioCompleto: List<String>
    ): List<String> {
        if (diccionarioCompleto.isEmpty()) {
            return listOf("", "", "", "")
        }

        // Asocia cada palabra de dicc con su distancia a la palabra errónea
        val listaConDistancias = diccionarioCompleto.map { diccWord ->
            Pair(diccWord, damerauLevenshteinOSAD(palabra, diccWord))
        }

        // Ordena primero por distancia menor y luego lexicográficamente para desempate
        val ordenadas = listaConDistancias.sortedWith(
            compareBy({ it.second }, { it.first })
        )

        // Toma hasta 4 elementos y rellena si hay menos de 4
        val sugerencias = ordenadas.take(4).map { it.first }.toMutableList()
        while (sugerencias.size < 4) {
            sugerencias.add("")
        }

        return sugerencias
    }
}