# Proyecto 1
### Kevin Gomes, carnet #23-10170
### David Garrido, carnet #23-10610
\
Este proyecto contiene una librería de ordenamiento (`Ordenamiento.kt`) junto con un motor (`Motor.kt`) y programa cliente (`PruebaRender.kt`) encargado de inicializar el tablero y realizar todas las acciones que solicite el usuario.

## Extensiones de la librería de ordenamiento

La biblioteca `Ordenamiento.kt` se ha reducido para incluir las tres variantes de algoritmos de ordenamiento a utilizar insertionSort, mergesort y quickSort:

* **`insertionSort`**:es un algoritmo que construye la lista ordenada elemento por elemento. Funciona recorriendo el arreglo desde el segundo elemento en adelante y, en cada paso, "inserta" el elemento actual en su posición correcta dentro de la sublista que ya ha sido ordenada a su izquierda, desplazando los elementos mayores hacia la derecha.
* **`mergesort`**: Versión recursiva estándar de Mergesort basada en el enfoque clásico de "Dividir y Vencerás" empleando (`merge`).
* **`quickSort`**: Funciona seleccionando un único elemento como pivote y reorganizando el arreglo de modo que todos los elementos menores o iguales al pivote se desplacen a su izquierda, y los mayores a su derecha. Una vez colocado el pivote en su posición correcta, el proceso se aplica de forma recursiva a los sub-arreglos izquierda y derecha hasta que todo el arreglo queda completamente ordenado.
---

## Motor del proyecto

El archivo `Motor.kt` contiene todos los tipos de datos que se implementaran para el proyecto, estos son:
* **`SpatialElement`**: este es el tipo de dato de cada elemento, y se define la función compareTo
* **`MapRegion`**
* **`SceneManager`**
---

## Archivo cliente

## Compilación y Ejecución

El proyecto incluye un archivo `Makefile` automatizado para la correcta gestión de la compilación unificando todos los fuentes necesarios del laboratorio.

```bash
# Compilar todo el proyecto y generar el archivo JAR ejecutable
make

# Limpiar los ejecutabLibreríales y archivos temporales generados
make clean
```

Para ejecutar el programa y ver el menú interactivo se utiliza el script de Bash `runPruebaRender.sh`, el cual invoca de manera transparente el programa cliente. Su sintaxis de uso es la siguiente:

```Bash
./runPruebaRender.sh #num
```

Semántica del parámetro
* `#num` debe ser un número entero mayor a cero, el cual define cuantos movimientos tienes disponibles al inicio de la ejecución del programa.

Ejemplo de ejecución:
```Bash
./runPruebaRender.sh 3
```
