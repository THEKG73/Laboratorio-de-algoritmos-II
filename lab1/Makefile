KC=kotlinc
KFLAGS=-include-runtime
PROG=PruebaAlgorimosSimples.jar
SRC=Ordenamiento.kt PruebaAlgoritmosSimples.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY: clean

clean:
	rm -f $(PROG)
