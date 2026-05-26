#!/bin/bash

TAMANOS=""
INTENTOS=""
OUTPUT="SinGrafico.png" # Nombre por defecto si no se pasa -o indicando que no se generará un gráfico
ALGORITHMS=""
SEQUENCE=""
SEARCHING="true"
PASS="true"

# Procesar banderas de la línea de comandos
while [[ $# -gt 0 ]]; do
    case $1 in
        -s)
            SEQUENCE="$2"
            shift 2
            ;;
        -a)
            ALGORITHMS=$(echo "$2" | tr -cd 'a-zA-Z,')
            if [[ ! "$ALGORITHMS" == "$2" ]]; then
                echo "Error: Los algoritmos deben estar separados solo por comas, ejemplo: ms,bs,is"
                exit 1
            fi
            shift 2
            ;;
        -n)
            TAMANOS=$(echo "$2" | tr -cd '0-9,')
            if [[ ! "$TAMANOS" == "$2" ]]; then
                echo "Error: Los tamaños deben estar separados solo por comas, ejemplo: 200,300,400"
                exit 1
            fi
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
            echo "Parámetro desconocido: $1 o error de formato, no ingrese espacios entre los valores, ejemplo: -n 200,300,400 -a ms,bs,is"
            echo "Uso: ./pord.sh -n 200,300,400 -t 3 -a ms,bs,is -s random -o nombre_grafica.png"
            exit 1
            ;;
    esac
done

# Validación de parámetros obligatorios
if [ -z "$TAMANOS" ] || [ -z "$INTENTOS" ] || [ -z "$ALGORITHMS" ] || [ -z "$SEQUENCE" ]; then
    echo "Error: Faltan parámetros obligatorios."
    echo "Uso: ./pord.sh -n 200,300,400 -t 3 -a ms,bs,is -s random -o nombre_grafica.png"
    exit 1
fi

# Pasamos el número de intentos, el nombre del archivo de salida y luego los tamaños
java -Djava.awt.headless=true -cp "PruebaOrdenamiento.jar:libPlotRuntime/*" PruebaOrdenamientoKt $ALGORITHMS $SEQUENCE $INTENTOS $OUTPUT $TAMANOS