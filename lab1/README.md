# Laboratorio de Algoritmos II: Prueba de Ordenamiento

Este proyecto implementa y evalúa los algoritmos de ordenamiento **Bubble Sort, Insertion Sort y Selection Sort** (Ordenamiento de Burbuja, ordenamiento de Inserción y ordenamiento de Selección) utilizando el lenguaje **Kotlin**. El sistema permite generar secuencias aleatorias de tamaño variable, procesarlas y validar su integridad de forma automática.

## Descripción de los Archivos

### 1. `Ordenamiento.kt`
Contiene las funciones lógicas del laboratorio:
* **`<T : Comparable<T>> bubbleSort(arr: Array<T>)`**: Implementación del algoritmo de burbuja con complejidad $O(n^2)$.
* **`<T : Comparable<T>> insertionSort(arr: Array<T>)`**: Implementación del algoritmo de inserción con complejidad $O(n^2)$ o en casos óptimos $O(n)$.
* **`<T : Comparable<T>> selectionSort(arr: Array<T>)`**: Implementación del algoritmo de selección con complejidad $O(n^2)$.

### 2. `PruebaAlgoritmosSimples.kt`
Es el punto de entrada del programa. Sus responsabilidades incluyen:
* Validar los argumentos pasados por línea de comandos.
* Verificar que secuencia se va a utilizar
* Instanciar un arreglo de tamaño `n`.
* Poblar el arreglo con números aleatorios (dependiendo de la secuencia).
* Llamar a las funciones de ordenamiento y validación.
* **`estaEnOrdenAscendente(A: Array<Int>)`**: Función de validación que recorre el arreglo para asegurar que cada elemento sea menor o igual al siguiente.
* Mostrar en terminal la secuencia original, el algoritmo de ordenamiento utilizado, la secuencia de números ordenada, la validación del orden y el tiempo que duró el proceso de ordenamiento.

### 3. `Makefile`
Automatiza el proceso de compilación para entornos Linux/WSL. Define las reglas para usar `kotlinc` con los flags necesarios para incluir el runtime de Kotlin.

---

## Guía de Compilación y Ejecución

Sigue estos pasos en tu terminal de Debian/WSL para poner en marcha el programa:

### Paso 1: Compilar
Genera el archivo ejecutable `.jar` a partir de los códigos fuente:
```bash
make
```

Crea un "wrapper" de shell para facilitar la ejecución y otorga los permisos necesarios
```bash
echo 'java -jar PruebaOrdenamiento.jar $*' > runPruebaOrdenamiento.sh
chmod +x runPruebaOrdenamiento.sh
```

Corre el programa indicando la cantidad de elementos que deseas ordenar (por ejemplo, 100)
```bash
./runPruebaOrdenamiento.sh -s <'secuencia'> -n <'cantidad de elementos'>
```
Ejemplo: ./runPruebaOrdenamiento.sh -s random -n 100
Ejemplo: ./runPruebaOrdenamiento.sh -s randomd -n 100

### Notas Adicionales:
* Se utilizaron nanosegundos para medir el tiempo, ya que necesitamos precisión a la hora de medir cuanto dura la ejecución.
