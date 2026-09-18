#!/bin/bash

# Verificar que se reciba la secuencia a decodificar
if [ "$#" -ne 1 ]; then
    echo "Uso: ./runCodigoMorse.sh \"<secuencia>\""
    exit 1
fi

java -jar PruebaMorse.jar "$1"