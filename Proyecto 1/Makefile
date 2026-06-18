# Definición de variables del compilador
KC=kotlinc
PROG=Proyecto.jar

# TODOS los archivos fuentes necesarios 
SRCS=main.kt Motor.kt Ordenamiento.kt PruebaRender.kt

# Regla por defecto
all: $(PROG)

# Compilación del JAR ejecutable unificando los fuentes
$(PROG): $(SRCS)
	$(KC) $(SRCS) -include-runtime -d $(PROG)

.PHONY: clean

clean:
	rm -f $(PROG)
