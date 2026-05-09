KC=kotlinc
KFLAGS=-include-runtime
PROG=PruebaOrdenamiento.jar
SRC=Ordenamiento.kt PruebaAlgorimosSimples.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY: clean

clean:
	rm -f $(PROG)
