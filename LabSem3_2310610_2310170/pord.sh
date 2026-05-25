#!/bin/bash

TAMANOS=""
INTENTOS=""
OUTPUT="grafico.png" # Nombre por defecto si no se pasa -o
ALGORITHMS=""
SEQUENCE=""

# Procesar banderas de la línea de comandos
while [[ $# -gt 0 ]]; do
    case $1 in
        -s)
            SEQUENCE="$2"
            shift 2
            ;;
        -a)
            ALGORITHMS=$(echo "$2" | tr -d ' ')
            shift 2
            ;;
        -n)
            TAMANOS=$(echo "$2" | tr -d ' ')
            shift 2
            ;;
        -t)
            INTENTOS="$2"
            shift 2
            ;;
        -o)
            OUTPUT="$2"
            # Nos aseguramos de que termine en .png
            if [[ ! "$OUTPUT" == *.png ]]; then
                OUTPUT="${OUTPUT}.png"
            fi
            shift 2
            ;;
        *)
            echo "Parámetro desconocido: $1"
            echo "Uso: ./pord.sh -n 200,300,400 -i 3 [-o nombre_grafica]"
            exit 1
            ;;
    esac
done

# Validación de parámetros obligatorios
if [ -z "$TAMANOS" ] || [ -z "$INTENTOS" ]; then
    echo "Error: Faltan parámetros obligatorios."
    echo "Uso: ./pord.sh -n 200,300,400 -i 3 [-o nombre_grafica]"
    exit 1
fi

# Pasamos el número de intentos, el nombre del archivo de salida y luego los tamaños
java -Djava.awt.headless=true -cp "PruebaOrdenamiento.jar:libPlotRuntime/*" PruebaOrdenamiento.kt $ALGORITHMS $SEQUENCE $INTENTOS $OUTPUT $TAMANOS