# Definición de variables del compilador
KC=kotlinc
PROG=PruebaOrdenamiento.jar

# Classpath: Apunta a los archivos .jar de soporte que están en libPlotRuntime
CP=libPlotRuntime/lets-plot-jfx-2.0.2.jar:libPlotRuntime/lets-plot-kotlin-api-2.0.1.jar:.

# TODOS los archivos fuentes necesarios 
SRCS=Ordenamiento.kt Main.kt PlotRuntime.kt SwingJfxDemoFrontendContext.kt

# Regla por defecto
all: $(PROG)

# Compilación del JAR ejecutable unificando los fuentes
$(PROG): $(SRCS)
	$(KC) -cp "$(CP)" $(SRCS) -include-runtime -d $(PROG)

.PHONY: clean

# Limpieza de ejecutables y archivos temporales
clean:
	rm -f $(PROG)
	rm -rf META-INF *.png
