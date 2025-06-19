# Trabajo Práctico Especial - Programación 3 (2025)

Este proyecto aborda la resolución de un problema de optimización dentro del contexto de una fábrica de autopartes, utilizando dos enfoques clásicos de la programación algorítmica: **Backtracking** y **Greedy**. A continuación, se explica cómo utilizar el código implementado.

---

## Cómo utilizar el código

### Estructura del proyecto
El proyecto está organizado con la siguiente estructura:
- `main.java`: Archivo principal que ejecuta ambos algoritmos
- `utils/FileLoader.java`: Clase encargada de cargar los datos desde el archivo `maquinas.txt`
- `utils/SolverUtils.java`: Clase que contiene los métodos de resolución
- `data/maquinas.txt`: Archivo de datos con la información de las máquinas

### Formato del archivo de datos
El archivo `maquinas.txt` debe seguir el siguiente formato:
```
12
M1,1
M2,4
M3,3
M4,7
```

Donde:
- La primera línea contiene las **piezas totales a producir**
- Las siguientes líneas contienen las **máquinas con la cantidad de piezas que produce cada una** en formato: `ID_MAQUINA,PIEZAS`

### Ejecución del programa
1. **Preparar los datos**: Asegúrate de que el archivo `maquinas.txt` esté en la ruta `tpe-programacion-3/src/data/` con el formato correcto.

2. **Ejecutar el programa**: Compila y ejecuta la clase `main.java`:
   ```bash
   javac main.java
   java main
   ```

3. **Resultados**: El programa ejecutará automáticamente ambos algoritmos:
    - **Backtracking**: Encuentra la solución óptima explorando todas las posibilidades
    - **Greedy**: Elige siempre la máquina más productiva en cada iteración

### Personalización
Para probar con diferentes conjuntos de datos:
1. Modifica el archivo `maquinas.txt` siguiendo el formato especificado
2. O cambia la ruta del archivo en el método `cargarDatos()` del archivo `main.java`

### Salida del programa
El programa mostrará para cada algoritmo:
- La **secuencia de máquinas** necesaria para producir las piezas requeridas
- La **cantidad de piezas producidas** y **cantidad de puestas en funcionamiento** requeridas
- Las **métricas de rendimiento**:
    - Backtracking: número de estados generados
    - Greedy: número de candidatos considerados

Estas métricas permiten contrastar la eficiencia y efectividad de cada técnica, brindando una perspectiva práctica sobre sus ventajas y limitaciones.

### Integrantes

- Daniel Ferreiro
- Henry Melchior
