# Definición de variables del compilador
KC=kotlinc
PROG=PruebaHash.jar

# Todos los archivos fuente necesarios
SRCS=HashTableEntry.kt CircularList.kt HashTableChaining.kt CuckooHashTableEntry.kt CuckooHashTable.kt Main.kt

# Regla por defecto
all: $(PROG)

# Compilación del JAR ejecutable unificando los fuentes
$(PROG): $(SRCS)
	$(KC) $(SRCS) -include-runtime -d $(PROG)

.PHONY: clean

clean:
	rm -f $(PROG)