# Laboratorio 11: Sección 2 - Código Morse
### Kevin Gomes, carnet #23-10170
### David Garrido, carnet #23-10610
\
Este proyecto implementa la decodificación de mensajes en Código Morse mediante un árbol binario de decisión. El programa recibe una secuencia de puntos y rayas, la interpreta según el esquema del alfabeto Morse y devuelve el mensaje original en caracteres latinos.

## Descripción del Problema

El Código Morse representa cada letra con una secuencia de puntos (.) y rayas (-). Para decodificar un mensaje, se puede modelar el alfabeto como un árbol binario:

- el hijo izquierdo representa un punto (.)
- el hijo derecho representa una raya (-)
- cada nodo hoja contiene la letra asociada

La tarea consiste en construir este árbol, navegarlo según la secuencia recibida y recuperar el texto original.

---

## Procedimiento Implementado

La solución principal está contenida en los archivos `CodigoMorseAD.kt` y `PruebaCodigoMorseAD.kt` y se compone de las siguientes partes:

### 1. `NodoMorse`
* Estructura auxiliar para modelar cada nodo del árbol.
* Cada nodo almacena un carácter opcional (`valor`) y dos referencias:
  * `izq` para el camino de punto.
  * `der` para el camino de raya.

### 2. `CodigoMorseAD`
* Implementa el TAD del Código Morse como un árbol binario de decisión.
* En el constructor se inicializa el alfabeto con la codificación Morse estándar de las letras del abecedario.
* La función `insertar(letra, codigo)` crea los caminos del árbol a partir de la secuencia de puntos y rayas.
* La función `decodificarLetra(secuencia)` recorre el árbol para interpretar una letra individual.
* La función `decodificarMensaje(frase)` divide la entrada por palabras (`/`) y por letras (` `), decodifica cada símbolo y reconstruye el texto final.

### 3. `main`
* Punto de entrada del programa.
* Verifica que se haya recibido un argumento con la secuencia Morse.
* Crea una instancia de `CodigoMorseAD`.
* Llama a `decodificarMensaje` para traducir el texto.
* Imprime el resultado en la salida estándar o un mensaje de error si la entrada no es válida.

---

## Formato de Entrada y Salida

### Formato de Entrada
La entrada debe ser una cadena con el código Morse a decodificar, donde:

- cada letra se representa con su secuencia en Morse,
- las letras dentro de una palabra se separan con un espacio,
- las palabras se separan con `/`.

Ejemplo:

```text
"... --- ... / ... --- ..."
```

Esto representa:

```text
S O S / S O S
```

Otro ejemplo:

```text
".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
```

que corresponde a:

```text
H E L L O / W O R L D
```

### Formato de Salida
El programa imprime el texto decodificado en la consola.

Ejemplo de salida:

```text
HOLA MUNDO
```

Si la secuencia es inválida, imprime:

```text
Error, codigo Morse no valido
```

---

## Compilación y Ejecución

El proyecto incluye un `Makefile` automatizado para compilar el programa y un script de ejecución en Bash.

### 1. Compilación
Para compilar el código de Kotlin y generar el archivo JAR ejecutable:

```bash
make
```

Para eliminar los ejecutables creados:

```bash
make clean
```

### 2. Ejecución
Se puede ejecutar el programa utilizando el script incluido:

```bash
./runCodigoMorse.sh ".-.-. --- -. --- -.-. .. -- .. . -. - ---"
```

También se puede ejecutar directamente el JAR:

```bash
java -jar PruebaMorse.jar ".-.-. --- -. --- -.-. .. -- .. . -. - ---"
```

> Nota: la secuencia debe ir entre comillas si incluye espacios y barras diagonales.

---

## Observaciones

La implementación usa un árbol de decisión para resolver el problema de decodificación de manera clara y eficiente. Esta estructura permite interpretar cada símbolo de Morse de forma directa, siguiendo el camino de puntos y rayas hasta llegar a la letra correspondiente.
