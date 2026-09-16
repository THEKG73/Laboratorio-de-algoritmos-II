/**
 * Evalúa si una cadena de texto es una palabra válida.
 *
 * Una palabra es válida si no está vacía y está compuesta exclusivamente por caracteres
 * alfabéticos minúsculos entre la 'a' y la 'z' o la letra 'ñ'.
 *
 * @param s Cadena de caracteres a validar.
 * @return `true` si todos los caracteres de [s] cumplen con el patrón y no está vacía; `false` en caso contrario.
 *
 * **Precondición:** [s] no debe ser nula.
 * **Postcondición:** Retorna la validez de la palabra sin modificar la cadena original.
 */
fun esPalabraValida(s: String): Boolean {
    if (s.isEmpty()) return false
    for (i in 0 until s.length) {
        val c = s[i]
        if ((c < 'a' || c > 'z') && c != 'ñ') {
            return false
        }
    }
    return true
}

/**
 * Calcula la distancia Damerau-Levenshtein (Optimal String Alignment Distance)
 * entre dos cadenas de caracteres.
 *
 * Determina el número mínimo de operaciones (inserción, eliminación, sustitución
 * o transposición de dos caracteres adyacentes) necesarias para transformar [s1] en [s2].
 *
 * @param s1 Primera cadena de caracteres a comparar.
 * @param s2 Segunda cadena de caracteres a comparar.
 * @return Cantidad entera de ediciones requeridas (mayor o igual a 0).
 *
 * Precondición: [s1] y [s2] no deben ser nulas.
 * Postcondición: Retorna la distancia de alineación óptima entre ambas cadenas sin modificar los parámetros de entrada.
 */
fun damerauLevenshteinOSAD(s1: String, s2: String): Int {
    val m = s1.length
    val n = s2.length
    val dp = Array(m + 1) { IntArray(n + 1) }

    // Casos base
    for (i in 0..m) dp[i][0] = i
    for (j in 0..n) dp[0][j] = j

    for (i in 1..m) {
        for (j in 1..n) {
            val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1

            // Operaciones estándar de Levenshtein
            var minCost = minOf(
                dp[i - 1][j] + 1,       // Eliminación
                dp[i][j - 1] + 1,       // Inserción
                dp[i - 1][j - 1] + cost // Sustitución
            )

            // Transposición de caracteres adyacentes
            if (i > 1 && j > 1 && s1[i - 1] == s2[j - 2] && s1[i - 2] == s2[j - 1]) {
                minCost = minOf(minCost, dp[i - 2][j - 2] + 1)
            }

            dp[i][j] = minCost
        }
    }

    return dp[m][n]
}