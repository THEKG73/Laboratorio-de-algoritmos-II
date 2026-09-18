#!/bin/bash

if [ "$#" -ne 1 ]; then
    echo "Uso: ./runTestHashTables.sh <n>"
    exit 1
fi

# Ejecuta el JAR pasándole la variable n
java -jar PruebaHash.jar "$1"