KC = kotlinc
KFLAG = -include-runtime -d
TARGET = Panquecas.jar

all: $(TARGET)

$(TARGET): Panquecas.kt
	$(KC) Panquecas.kt $(KFLAG) $(TARGET)

clean:
	rm -f *.jar