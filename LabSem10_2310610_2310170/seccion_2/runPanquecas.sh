#!/bin/bash

# Verificar que se reciba el archivo de entrada
if [ "$#" -ne 1 ]; then
    echo "Uso: ./runPanquecas.sh archivo_entrada"
    exit 1
fi

ARCHIVO="$1"

# Si la variable ARCHIVO no termina en ".txt", se lo concatenamos
if [[ "$ARCHIVO" != *.txt ]]; then
    ARCHIVO="${ARCHIVO}.txt"
fi

# Ejecutar el programa pasándole la variable ya formateada
java -jar Panquecas.jar "$ARCHIVO"