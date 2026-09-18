KC=kotlinc
PROG=PruebaMorse.jar
SRCS=CodigoMorseAD.kt PruebaCodigoMorseAD.kt

all: $(PROG)

$(PROG): $(SRCS)
	$(KC) $(SRCS) -include-runtime -d $(PROG)

.PHONY: clean

clean:
	rm -f $(PROG)