# Laboratorio 5: Sección 2 - Problema de las Panquecas
### Kevin Gomes, carnet #23-10170
### David Garrido, carnet #23-10610
\
Este proyecto contiene la solución al **Problema de las Panquecas** (Pancake Sorting). El programa procesa múltiples pilas de panquecas desde un archivo de entrada y determina la secuencia de movimientos necesarios empleando exclusivamente la operación `voltear(i)` para dejarlas ordenadas de mayor a menor diámetro desde el plato.

## Descripción del Problema

Dada una pila de $n$ panquecas en un plato donde cada panqueca posee un diámetro distinto, la panqueca sobre el plato ocupa la posición $1$ y la del tope la posición $n$. Se dispone de un operador `voltear(i)` que inserta una espátula bajo la posición $i$ e invierte el orden de los elementos desde la posición $i$ hasta la posición $n$.

El objetivo es transformar la pila inicial en una **pila objetivo** donde la panqueca de menor diámetro quede en el tope ($n$) y la de mayor diámetro sobre el plato ($1$), aplicando secuencialmente la menor cantidad posible de operaciones `voltear(i)`.

---

## Funciones de la Solución

El núcleo de la solución está contenido en el archivo `Panquecas.kt` y consta de las siguientes funciones principales:

### 1. `main`
* Punto de entrada del programa encargado del procesamiento de argumentos, parsing del archivo de texto y ejecución del algoritmo de ordenamiento por panquecas.
* Lee las líneas del archivo provisto en `args[0]`, extrae el número total de colas a procesar indicado en la primera línea, divide los bloques correspondientes a `ColaX` e invierte la representación para mapear internamente la posición $1$ al índice inicial del arreglo, por cada cola, ubica iterativamente el elemento que debe ir en cada posición objetivo y aplica los movimientos `voltear` necesarios, imprime en la salida estándar el identificador de la cola seguido de la secuencia de comandos terminada en `0`.

### 2. `voltear`
* Implementación directa del operador `voltear(i)`.
* Recibe un arreglo `A` y un índice de corte `piso`. Invierte en sitio todos los elementos desde el índice `piso` hasta el final del arreglo (`A.size - 1`).
* **Precondiciones**: El arreglo `A` no debe ser nulo y `0 <= piso < A.size`.
* **Postcondiciones**: La subsecuencia $[piso \dots A.size-1]$ queda invertida de forma simétrica.

---

## Formato de Entrada y Salida

### Formato de Entrada
El archivo de entrada en formato `.txt` debe cumplir con las siguientes especificaciones:
* **Línea 1**: Número entero que indica la cantidad de colas.
* **Bloques de Cola**: La etiqueta `ColaX` seguida por el diámetro de cada panqueca (un número por línea).

**Ejemplo de entrada (`entrada.txt`):**
```text
3
Cola1
1
2
3
4
5
Cola2
5
4
3
2
1
Cola3
5
1
2
3
4
```

### Formato de Salida
Para cada cola procesada se imprime por la salida estándar:
* El identificador de la cola (`Cola1`, `Cola2`, etc.).
* Una secuencia de enteros separados por espacio que indican las posiciones $i$ sobre las que se aplicó `voltear(i)`, finalizando siempre con el dígito `0`.

**Ejemplo de salida correspondiente:**
```text
Cola1
0
Cola2
1 0
Cola3
1 2 0
```

---

## Compilación y Ejecución

El proyecto incluye un `Makefile` automatizado para la compilación y un script de automatización en Bash denominado `runPanquecas.sh`.

### 1. Compilación
Para compilar el código de Kotlin y empaquetarlo en un archivo JAR ejecutable:

```bash
make
```

Para remover los ejecutables creados:
```bash
make clean
```

### 2. Ejecución
Para ejecutar el programa se utiliza el script ejecutable `runPanquecas.sh`, pasándole como argumento el nombre o ruta del archivo de texto de entrada:

```bash
./runPanquecas.sh archivo_entrada
```

> **Nota**: El script verifica de forma automática la extensión del archivo; si no se incluye `.txt` en el argumento, el script se lo concatenará.