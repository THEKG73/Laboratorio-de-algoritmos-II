# Laboratorio 11: Sección 1 - Tablas de Hash
### Kevin Gomes, carnet #23-10170
### David Garrido, carnet #23-10610
\
Este proyecto implementa y compara dos estrategias de resolución para tablas de hash: la versión con encadenamiento y la versión con Cuckoo Hashing. El programa genera un conjunto de claves aleatorias, realiza inserciones y eliminaciones alternadas para evaluar el comportamiento de ambas estructuras y reporta el tiempo de ejecución de cada una.

## Descripción del Problema

Una tabla de hash permite almacenar pares clave-valor para acceder a la información en tiempo promedio constante. En este laboratorio se estudian dos variantes:

- Tabla de Hash con Encadenamiento: cada posición del arreglo almacena una lista enlazada que resuelve colisiones.
- Cuckoo Hashing: se utilizan dos funciones de dispersión y dos tablas, redistribuyendo elementos cuando ocurre una colisión para mantener un factor de carga controlado.

El objetivo es comparar el rendimiento de ambas soluciones sobre una secuencia de operaciones de inserción y eliminación dinamizada.

---

## Procedimiento Implementado

El núcleo del programa se encuentra en los archivos:

### 1. Main.kt
* Punto de entrada del programa.
* Recibe un entero n como argumento.
* Genera n pares (clave, valor) con claves aleatorias en el intervalo [0, n/3].
* Realiza una secuencia de inserciones y eliminaciones alternadas sobre ambas tablas.
* Mide el tiempo de ejecución de cada estructura con System.nanoTime().
* Imprime en la salida estándar los tiempos obtenidos para cada implementación.

### 2. HashTableChaining.kt
* Implementa la tabla de hash con encadenamiento.
* Usa función hash por división y almacena cada bucket como una lista circular.
* Soporta operaciones de agregar, buscar, eliminar y rehashing cuando el factor de carga supera el umbral indicado.

### 3. CuckooHashTable.kt
* Implementa la versión con Cuckoo Hashing.
* Utiliza dos tablas internas y dos funciones de dispersión:
  * h1: método de la división.
  * h2: método de la multiplicación.
* Maneja desplazamientos de elementos entre ambas tablas hasta encontrar una posición libre.
* Si se detecta un ciclo, realiza rehashing para reconstruir la estructura.

### 4. Estructuras auxiliares
* HashTableEntry.kt y CuckooHashTableEntry.kt: encapsulan la información de cada clave y su valor.
* CircularList.kt: estructura de soporte para las listas enlazadas del encadenamiento.

---

## Formato de Entrada y Salida

### Formato de Entrada
El programa recibe un único argumento entero:

```text
n
```

Donde n representa la cantidad total de elementos que se generarán para la prueba.

### Formato de Salida
La ejecución imprime una línea resumen con el tiempo requerido por cada implementación:

```text
Iniciando pruebas para n = 100000...
---------------------------------------------------
Tabla de Hash (Encadenamiento): 0.12345 segundos
Tabla de Hash (Cuckoo Hashing): 0.09876 segundos
---------------------------------------------------
```

---

## Compilación y Ejecución

El proyecto incluye un Makefile para compilar la aplicación y un script de ejecución.

### 1. Compilación
Para compilar el código de Kotlin y generar el archivo JAR ejecutable:

```bash
make
```

Para eliminar los artefactos generados:

```bash
make clean
```

### 2. Ejecución
Se puede ejecutar el programa de dos formas:

#### Opción 1: usando el script incluido
```bash
./runTestHashTables.sh 100000
```

#### Opción 2: invocando el JAR directamente
```bash
java -jar PruebaHash.jar 100000
```

> Nota: el valor recibido debe ser un entero positivo que indique la cantidad de elementos a probar.

---

## Observaciones

La comparación experimental permite observar cómo el encadenamiento y el Cuckoo Hashing responden ante un patrón de inserciones y eliminaciones repetidas. El programa está orientado a medir la eficiencia práctica de ambas estrategias en situaciones de carga variable.
