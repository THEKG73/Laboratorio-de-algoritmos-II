# Definición de variables del compilador
KC=kotlinc
PROG=PruebaOrtografia.jar

# TODOS los archivos fuentes necesarios 
SRCS= PruebaOrtografia.kt ConjuntoDePalabras.kt PruebaDelAyudanteOrtografico.kt TAD_AyudanteOrtografico.kt TAD_PMLI.kt

# Regla por defecto
all: $(PROG)

# Compilación del JAR ejecutable unificando los fuentes
$(PROG): $(SRCS)
	$(KC) $(SRCS) -include-runtime -d $(PROG)

.PHONY: clean

clean:
	rm -f $(PROG)
