# Laboratorio de Algoritmos II: Prueba de Ordenamiento

Este proyecto implementa y evalúa el algoritmo de ordenamiento **Bubble Sort** (Ordenamiento de Burbuja) utilizando el lenguaje **Kotlin**. El sistema permite generar secuencias aleatorias de tamaño variable, procesarlas y validar su integridad de forma automática.

## Descripción de los Archivos

### 1. `Ordenamiento.kt`
Contiene las funciones lógicas del laboratorio:
* **`bubbleSort(A: Array<Int>)`**: Implementación del algoritmo de burbuja con complejidad $O(n^2)$.
* **`estaEnOrdenAscendente(A: Array<Int>)`**: Función de validación que recorre el arreglo para asegurar que cada elemento sea menor o igual al siguiente.

### 2. `Main.kt`
Es el punto de entrada del programa. Sus responsabilidades incluyen:
* Validar los argumentos pasados por línea de comandos.
* Instanciar un arreglo de tamaño `n`.
* Poblar el arreglo con números aleatorios.
* Llamar a las funciones de ordenamiento y validación.

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
./runPruebaOrdenamiento.sh <n>
```
Ejemplo: ./runPruebaOrdenamiento.sh 100

### Notas Adicionales:
* **Uso de `$*`**: En el script `.sh`, este operador asegura que todos los parámetros que escribas después del comando (como el `100`) se pasen directamente a la máquina virtual de Java.
* **Tabuladores**: Recuerda que si editas el `Makefile` manualmente, la línea de comandos de la regla `all:` debe llevar un **tabulador** real.
* **make clean**: Para limpiar el directorio y eliminar el archivo .jar generado.
* **Medidores de Tiempo**: Se implementaron 3 maneras para capturar el tiempo de corrida de una funcion, 2 en milisegundos y 1 en nanosegundos. Nanosegundos nos puede servir si es un tiempo corto (En nuestro caso pocos elementos) y necesitamos precision.