# Laboratorio 4: QuickSort y Dual-Pivot QuickSort
### Kevin Gomes, carnet #23-10170
### David Garrido, carnet #23-10610
\
Este proyecto contiene la ampliación de la librería de ordenamiento `Ordenamiento.kt` junto con un programa cliente de pruebas (`PruebaOrdenamiento.kt`) encargado de evaluar empíricamente el rendimiento de diversos algoritmos de ordenamiento bajo diferentes configuraciones de secuencias de datos.

## Funciones adicionales

Para optimizar el rendimiento con conjuntos de datos reducidos y servir de base para variantes avanzadas de ordenamiento, se incorporaron tres funciones especializadas en arreglos de tamaño fijo. Cada una de ellas emplea un número óptimo y controlado de comparaciones directas.

### 1. `ordenaDos`
* **Descripción**: Procedimiento diseñado para ordenar arreglos de tamaño reducido mediante comparaciones directas de sus elementos.
* **¿Qué hace?**: Recibe un arreglo de exactamente dos elementos. Compara de forma directa el primer elemento con el segundo; si el primero es estrictamente mayor, intercambia sus posiciones para asegurar un orden estrictamente ascendente.
* **Control de Errores**: Si el arreglo recibido posee una longitud distinta de 2, el procedimiento detiene inmediatamente la ejecución del programa emitiendo un mensaje explicativo por la salida estándar.

### 2. `ordenaTres`
* **Descripción**: Procedimiento diseñado para la ordenación de tres elementos empleando un esquema fijo de comparaciones e intercambios condicionales.
* **¿Qué hace?**: Recibe un arreglo de exactamente tres elementos. Aplica una secuencia de tres comparaciones (comparando las posiciones $0$ con $1$, luego $1$ con $2$, y finalmente re-verificando $0$ con $1$) para garantizar que los tres componentes queden ordenados de forma ascendente.
* **Control de Errores**: Si el tamaño del arreglo es diferente de 3, el programa aborta su ejecución de forma inmediata e informa la anomalía por la salida estándar.

### 3. `ordenaCuatro`
* **Descripción**: Procedimiento óptimo para la ordenación elemental de arreglos de cuatro posiciones mediante una red fija de permutaciones condicionales.
* **¿Qué hace?**: Recibe un arreglo de exactamente cuatro elementos. Ejecuta una serie de comparaciones sucesivas e intercambios posicionales sobre sus índices para reestructurar la secuencia completa en orden estrictamente ascendente.
* **Control de Errores**: Si la función es invocada con un arreglo cuya longitud no sea rigurosamente igual a 4, el sistema interrumpe la ejecución arrojando un error en la consola.

> **Nota de Restricción**: De acuerdo con las especificaciones del laboratorio, estos tres procedimientos procesan únicamente arreglos de elementos que extiendan de tipos comparables (en este caso números). Está estrictamente prohibido el uso de listas u otra estructura de datos de la librería de Kotlin que no corresponda a estructuras de arreglos nativos.

---

## Extensiones Adicionales de la Librería

Además de los métodos elementales anteriores, la biblioteca `Ordenamiento.kt` se ha expandido para incluir tres variantes del algoritmo fundamental Mergesort, una implementación de Heapsort, y dos de quicksort (quicksort y Dual-Pivot quicksort):

* **`InsertionSort` (`is`)**: Divide la lista en una sección ordenada y otra desordenada; toma un elemento de la parte desordenada y lo va comparando hacia atrás con los elementos ya ordenados hasta "insertarlo" en su posición correcta, desplazando los demás elementos un lugar hacia adelante.
* **`selectionSort` (`ss`)**: Divide la lista en dos secciones (ordenada y desordenada) y en cada iteración busca el elemento más pequeño de la sección desordenada. Una vez que lo encuentra, lo intercambia directamente con el primer elemento de esa sección desordenada, haciendo crecer la parte ordenada posición por posición.
* **`bubbleSort` (`bs`)**: Compara repetidamente pares de elementos adyacentes a lo largo de la lista y los intercambia si están en el orden incorrecto. De esta forma, en cada pasada completa, el elemento más pequeño "flota" como una burbuja hacia su posición final en el extremo del arreglo.
* **`mergesortInsertion` (`mb`)**: Versión híbrida de Mergesort. Utiliza los métodos de tamaño fijo para arreglos de longitud de 2 a 4. Para sub-arreglos con un tamaño comprendido en el intervalo $[5..100]$, detiene la subdivisión recursiva y delega la ordenación al algoritmo `insertionSort` con el fin de mitigar el sobrecosto de llamadas en la pila.
* **`mergesort` (`ms`)**: Versión recursiva estándar de Mergesort basada en el enfoque clásico de "Dividir y Vencerás" empleando (`merge`).
* **`mergesortIterativo` (`mi`)**: Variante iterativa de Mergesort que prescinde de la recursividad, gestionando las mezclas de sub-arreglos de tamaño k por medio de bucles iterativos.
* **`heapsort` (`hs`)**: Implementa el algoritmo Heap Sort. Construye un max-heap y extrae el mayor elemento repetidamente para ordenar el arreglo. Para arreglos pequeños y moderados delega en las funciones especializadas del laboratorio (`ordenaDos`, `ordenaTres`, `ordenaCuatro`, `insertionSort`) antes de aplicar la versión completa del Heap Sort.
* **`QuickSort` (`qs`)**: Funciona seleccionando un único elemento como pivote y reorganizando el arreglo de modo que todos los elementos menores o iguales al pivote se desplacen a su izquierda, y los mayores a su derecha. Una vez colocado el pivote en su posición correcta, el proceso se aplica de forma recursiva a los sub-arreglos izquierda y derecha hasta que todo el arreglo queda completamente ordenado.
* **`Dual-Pivot Quicksort` (`qp`)**: Este algoritmo utiliza dos pivotes, en lugar de dividir el arreglo en dos partes, se particiona en tres secciones: elementos menores que el primer pivote, elementos que se encuentran entre ambos pivotes, y elementos mayores que el segundo pivote. Después, el algoritmo ejecuta llamadas recursivas sobre cada una de estas tres secciones de manera independiente, logrando reducir el número de comparaciones.
---

## Tipos de Arreglos Generados

El programa cliente tiene la capacidad de generar y evaluar el rendimiento de los algoritmos sobre las siguientes clases de arreglos según el identificador provisto a través de la bandera `-s`:

| Identificador | Tipo | Descripción |
| :--- | :--- | :--- |
| **`random`** | Entero | Arreglo de $N$ elementos de tipo entero, generados aleatoriamente en el intervalo $[0..N]$. |
| **`randomd`** | Double | Arreglo de $N$ elementos de tipo double, generados aleatoriamente en el intervalo $[0..1]$. |
| **`sorted`** | Entero | Arreglo de $N$ elementos de tipo entero, en donde los elementos ya están ordenados de forma ascendente de la forma $[1, 2, ..., N]$. |
| **`sortedd`** | Double | Arreglo de $N$ elementos de tipo double, en donde los elementos en el intervalo $[0..1]$ ya se encuentran ordenados. |
| **`inv`** | Entero | Arreglo de $N$ elementos de tipo entero, ordenados de forma inversa, tal que el arreglo tiene la forma $[N, ..., 2, 1]$. |
| **`zu`** | Binario | Arreglo de $N$ elementos de ceros y unos ($0$ y $1$), generados de forma completamente aleatoria. |
| **`media`** | Entero | Arreglo de $N$ elementos de tipo entero, cuyos elementos tienen una distribución simétrica en forma de cuña o pirámide de la manera $[1, 2, ..., \lfloor N/2 \rfloor, \lfloor N/2 \rfloor, ..., 2, 1]$. |

---

## Compilación y Ejecución

El proyecto incluye un archivo `Makefile` automatizado para la correcta gestión de la compilación unificando todos los fuentes necesarios del laboratorio.

```bash
# Compilar todo el proyecto y generar el archivo JAR ejecutable
make

# Limpiar los ejecutables y archivos temporales generados
make clean
```

Para realizar las pruebas empíricas de rendimiento se utiliza el script de Bash pord.sh, el cual procesa las flags de la línea de comandos e invoca de manera transparente el programa cliente configurando el entorno headless. Su sintaxis de uso es la siguiente:

```Bash
./pord.sh [-t #num] [-a <alg>] [-s <tipo arr>] [-n <tamaños>] [-o <figura>]
```

Semántica de los Parámetros
* -t: Especifica el número de intentos o repeticiones en las que se aplicará la clase de arreglo seleccionada para promediar los tiempos.  
* -a: Define los algoritmos de ordenamiento a ejecutar separados por comas sin espacios intermedios (ej. ms,bs,is).  
* -s: Identificador de la clase de arreglo a generar (random, inv, media, etc.). Si se indica un identificador inválido, el programa aborta con error.  
* -n: Serie de números que indican los tamaños de los arreglos a generar. Deben ingresarse en orden estrictamente ascendente, ser diferentes y estar separados por comas. En caso contrario, la ejecución se aborta.  
* -o: (Opcional) Indica que se debe exportar un gráfico comparativo de rendimiento. El argumento define el nombre del archivo de salida .png (el sistema añade automáticamente la extensión si hace falta). Solo es válido si se configuran dos o más tamaños de arreglo con la opción -n.  


Ejemplo de ejecución generando gráfica:
```Bash
./pord.sh -s inv -n 20000,40000,50000 -t 3 -a ms,mi -o salidaInv
```

Al finalizar las iteraciones correspondientes, se presentarán por la salida estándar las métricas de rendimiento y se almacenará el diagrama visual del comportamiento de los algoritmos en el archivo salidaInv.png.

