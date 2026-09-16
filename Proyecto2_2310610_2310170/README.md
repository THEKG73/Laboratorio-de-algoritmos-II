# Proyecto 2: Ayudante Ortográfico
### Kevin Gomes, carnet #23-10170
### David Garrido, carnet #23-10610

Este proyecto contiene la implementación en Kotlin del **TAD Ayudante Ortográfico**. La aplicación permite cargar diccionarios de palabras, gestionarlos e inspeccionar textos de entrada para detectar errores ortográficos, sugiriendo hasta 4 correcciones basadas en la métrica de distancia de alineación óptima (Damerau-Levenshtein OSA).

## Descripción del Proyecto

El Ayudante Ortográfico es una herramienta capaz de procesar textos y detectar palabras que no pertenezcan a un diccionario preestablecido. Para cada palabra errónea o desconocida, el sistema busca las palabras dentro del diccionario con menor distancia de edición y las presenta como recomendaciones ordenadas por cercanía.

### Especificación de Palabras Válidas
Una cadena de texto es considerada una palabra válida si está compuesta exclusivamente por caracteres alfabéticos minúsculos del alfabeto (`'a'` a `'z'`) e incluyendo la letra `'ñ'`:
$$\text{esPalabraValida}(s) = (\forall i : 0 \le i < \text{len}(s) : \text{'a'} \le s[i] \le \text{'z'} \lor s[i] = \text{'ñ'})$$

### Métrica de Distancia entre Palabras
La similitud entre palabras se calcula utilizando la distancia Damerau-Levenshtein en su variación de *Optimal String Alignment Distance* (OSA). Esta métrica cuantifica el número mínimo de operaciones requeridas para transformar una cadena en otra, permitiendo cuatro tipos de ediciones:
1. **Inserción** de un carácter.
2. **Eliminación** de un carácter.
3. **Sustitución** de un carácter.
4. **Transposición** de dos caracteres adyacentes.

---

## Estructura de Clases y Funciones

El proyecto se compone de los siguientes módulos principales implementados en Kotlin:

### 1. `ConjuntoPalabras` (`ConjuntoDePalabras.kt`)
Implementa el TAD Conjunto de cadenas utilizando una **Tabla de Hash** con resolución de colisiones por encadenamiento (*chaining*).
* **Representación**: Arreglo dinámico de listas mutables de cadenas (`Array<MutableList<String>>`).
* **Hash & Rehashing**: Utiliza el método `hashCode()` de `String` en Kotlin. Cuando el factor de carga ($\lambda = N/M$) alcanza o supera `0.7`, ejecuta un procedimiento de *rehashing* duplicando la capacidad de la tabla ($2M$).
* **Operaciones**: `agregar`, `eliminar`, `contiene`, `obtenerFactorDeCarga`, `obtenerTodas` y `tamano`.

### 2. `TAD_PMLI` (`TAD_PMLI.kt`)
Tipo Abstraído de Datos **PMLI** (*Palabras con la Misma Letra Inicial*). Encapsula el conjunto de palabras que inician con un carácter alfabético minúsculo específico.
* **Representación**: Posee la propiedad `letra: Char` y una instancia de `ConjuntoPalabras`.
* **Invariante**: $p \in \text{palabras} \implies p[0] = \text{letra} \land \text{esPalabraValida}(p)$.
* **Operaciones**: `agregarPalabra`, `eliminarPalabra`, `buscarPalabra` y `mostrarPalabras` (imprime en orden lexicográfico).

### 3. `TAD_AyudanteOrtografico` (`TAD_AyudanteOrtografico.kt`)
Estructura principal del sistema que administra el diccionario general.
* **Representación**: Arreglo de tamaño fijo $\text{MAX} = 27$ de instancias del `TAD_PMLI` (posiciones 0 a 25 para `'a'..'z'` y la posición 26 para `'ñ'`).
* **Operaciones Principales**:
  * `cargarDiccionario(fname: String)`: Lee un archivo con una palabra válida por línea y puebla las casillas correspondientes.
  * `borrarPalabra(p: String)`: Remueve la palabra del `TAD_PMLI` asociado a su letra inicial.
  * `corregirTexto(finput: String, foutput: String)`: Parsea el archivo de entrada extrayendo secuencias continuas de caracteres válidos, identifica términos ausentes en el diccionario y calcula para cada uno las 4 sugerencias con menor distancia Damerau-Levenshtein.
  * `imprimirDiccionario()`: Despliega por consola el contenido del diccionario agrupado por letra.

### 4. `PruebaOrtografia.kt` y `PruebaDelAyudanteOrtografico.kt`
* **`PruebaOrtografia.kt`**: Programa cliente interactivo que proporciona una interfaz de consola con un menú iterativo de 6 opciones:
  1. Crear un nuevo ayudante ortográfico.
  2. Cargar un diccionario.
  3. Eliminar palabra.
  4. Corregir texto.
  5. Mostrar diccionario.
  6. Salir de la aplicación.
* **`PruebaDelAyudanteOrtografico.kt`**: Módulo con las utilidades algorítmicas auxiliares, incluyendo la función de validación de palabras `esPalabraValida` y el cálculo dinámico de la distancia `damerauLevenshteinOSAD`.

---

## Formato de Entrada y Salida

### Archivo de Diccionario (`diccionario.txt`)
Debe ser un archivo de texto con una palabra válida por línea en minúsculas.
```text
arbol
casa
perro
gato
```

### Archivo de Entrada a Corregir (`entrada.txt`)
Puede contener cualquier texto con espacios, puntuación o mayúsculas. El sistema extraerá únicamente las secuencias válidas.
```text
El perro persiguio al gatto cerca de la kasas.
```

### Archivo de Salida Generado (`resultado.txt`)
Cada línea contiene una palabra no encontrada seguida de las 4 mejores sugerencias del diccionario separadas por comas.
```text
gatto, gato, casa, perro, arbol
kasas, casa, gato, perro, arbol
```

---

## Compilación y Ejecución

El proyecto incluye un `Makefile` para la compilación modular y un script en Bash denominado `runPruebaDelAyudanteOrtografico.sh` para la automatización de la ejecución.

### 1. Compilación
Para compilar los fuentes de Kotlin y empaquetar la aplicación en un ejecutable JAR:

```bash
make
```

Para limpiar las clases compiladas y archivos generados:
```bash
make clean
```

### 2. Ejecución
Para iniciar la interfaz interactiva en consola del cliente:

```bash
./runPruebaDelAyudanteOrtografico.sh
```

---

## Documentación KDoc
Todos los métodos y clases del proyecto cuentan con documentación bajo el formato KDoc, especificando:
* Descripción general y propósito.
* Descripción detallada de parámetros (`@param`) y valores de retorno (`@return`).
* **Precondiciones** requeridas para la ejecución segura.
* **Postcondiciones** garantizadas tras la finalización.
* Excepciones lanzadas (`@throws`).