import java.io.File
import java.util.Scanner

/**
 * Función principal que ejecuta la aplicación interactiva de consola para el cliente del TAD Ayudante Ortográfico.
 *
 * Despliega un menú interactivo que le permite al usuario seleccionar entre 6 opciones para gestionar
 * la creación del ayudante, la carga de un diccionario, la eliminación de palabras, la corrección de archivos
 * de texto y la visualización del contenido del diccionario.
 *
 * @param args Arreglo de argumentos de la línea de comandos pasados a la aplicación.
 *
 * Precondición: Ninguna.
 * Postcondición: Se ejecuta el bucle de la aplicación hasta que el usuario elija la opción de salir (opción 6).
 */
fun main (args:Array<String>) {
    val scanner = Scanner(System.`in`)
    var ayudante: TAD_AyudanteOrtografico? = null
    var salir = false

    println("=========================================")
    println("   BIENVENIDO AL AYUDANTE ORTOGRÁFICO    ")
    println("=========================================")

    while (!salir) {
        mostrarMenu()
        print("Seleccione una opción (1-6): ")
        val opcionInput = scanner.nextLine().trim()

        when (opcionInput) {
            "1" -> {
                // Opción 1: Crear un nuevo ayudante ortográfico
                ayudante = TAD_AyudanteOrtografico()
                println("\n[ÉXITO] Se ha creado un nuevo Ayudante Ortográfico.")
            }

            "2" -> {
                // Opción 2: Cargar un diccionario
                if (ayudante == null) {
                    println("\n[ERROR] No existe una instancia activa de Ayudante Ortográfico.")
                    println("Debe ejecutar primero la Opción 1 para crear un nuevo ayudante.")
                } else {
                    print("\nIngrese el nombre/ruta del archivo de diccionario: ")
                    val fname = scanner.nextLine().trim()

                    if (fname.isEmpty()) {
                        println("[ERROR] El nombre del archivo no puede estar vacío.")
                    } else if (!File(fname).exists() || !File(fname).isFile) {
                        println("[ERROR] El archivo '$fname' no existe o no es accesible.")
                        println("La precondición exige un archivo válido y existente para cargar el diccionario.")
                    } else {
                        try {
                            ayudante.cargarDiccionario(fname)
                            println("[ÉXITO] El diccionario '$fname' fue cargado exitosamente.")
                        } catch (e: IllegalArgumentException) {
                            println("\n[ERROR]")
                            println(e.message)
                        } catch (e: Exception) {
                            println("\n[ERROR AL LEER ARCHIVO] Ocurrió un error inesperado: ${e.message}")
                        }
                    }
                }
            }

            "3" -> {
                // Opción 3: Eliminar palabra
                if (ayudante == null) {
                    println("\n[ERROR] No existe una instancia activa de Ayudante Ortográfico.")
                    println("Debe ejecutar primero la Opción 1 para crear un nuevo ayudante.")
                } else {
                    print("\nIngrese la palabra a eliminar: ")
                    val palabra = scanner.nextLine().trim()

                    if (!esPalabraValida(palabra)) {
                        println("[ERROR] La cadena '$palabra' no es una palabra válida.")
                        println("Solo se permiten caracteres alfabéticos en minúscula ('a'..'z') y la letra 'ñ'.")
                    } else {
                        try {
                            ayudante.borrarPalabra(palabra)
                            println("[ÉXITO] Se procesó la solicitud de eliminación para la palabra '$palabra'.")
                        } catch (e: IllegalArgumentException) {
                            println("\n[ERROR] ${e.message}")
                        }
                    }
                }
            }

            "4" -> {
                // Opción 4: Corregir texto
                if (ayudante == null) {
                    println("\n[ERROR] No existe una instancia activa de Ayudante Ortográfico.")
                    println("Debe ejecutar primero la Opción 1 para crear un nuevo ayudante.")
                } else {
                    print("\nIngrese el archivo de entrada a corregir (ej. texto.txt): ")
                    val finput = scanner.nextLine().trim()

                    if (!File(finput).exists() || !File(finput).isFile) {
                        println("[ERROR] El archivo de entrada '$finput' no existe.")
                        println("Para corregir un texto, el archivo de entrada debe ser válido y existente.")
                    } else {
                        print("Ingrese el nombre del archivo de salida (ej. resultado.txt): ")
                        val foutput = scanner.nextLine().trim()

                        if (foutput.isEmpty()) {
                            println("[ERROR] El nombre del archivo de salida no puede estar vacío.")
                        } else {
                            try {
                                ayudante.corregirTexto(finput, foutput)
                                println("[ÉXITO] Texto corregido exitosamente. Los resultados están guardados en '$foutput'.")
                            } catch (e: IllegalArgumentException) {
                                println("\n[ERROR] ${e.message}")
                            } catch (e: Exception) {
                                println("\n[ERROR] Ocurrió un fallo durante el procesamiento del archivo: ${e.message}")
                            }
                        }
                    }
                }
            }

            "5" -> {
                // Opción 5: Mostrar diccionario
                if (ayudante == null) {
                    println("\n[ERROR] No existe una instancia activa de Ayudante Ortográfico.")
                    println("Debe ejecutar primero la Opción 1 para crear un nuevo ayudante.")
                } else {
                    println("\n--- CONTENIDO DEL DICCIONARIO ---")
                    ayudante.imprimirDiccionario()
                    println("---------------------------------")
                }
            }

            "6" -> {
                // Opción 6: Salir de la aplicación
                salir = true
            }

            else -> {
                println("\n[OPCIÓN INVÁLIDA] La opción '$opcionInput' no existe en el menú. Seleccione un número entre 1 y 6.")
            }
        }
        println()
    }
}

/**
 * Despliega en la salida estándar el listado de opciones disponibles en el menú interactivo.
 *
 * Precondición: Ninguna.
 * Postcondición: Imprime las opciones numeradas de la 1 a la 6 en la consola sin modificar ningún estado del sistema.
 */
fun mostrarMenu() {
    println("-----------------------------------------")
    println("MENÚ DE OPCIONES:")
    println("1. Crear un nuevo ayudante ortográfico.")
    println("2. Cargar un diccionario.")
    println("3. Eliminar palabra.")
    println("4. Corregir texto.")
    println("5. Mostrar diccionario.")
    println("6. Salir de la aplicación.")
    println("-----------------------------------------")
}