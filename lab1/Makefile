KC=kotlinc
KFLAGS=-include-runtime
PROG=PruebaOrdenamiento.jar
SRC=Ordenamiento.kt Main.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY: clean

clean:
	rm -f $(PROG)