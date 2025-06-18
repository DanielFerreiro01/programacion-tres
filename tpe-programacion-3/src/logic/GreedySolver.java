package logic;

import core.Machine;

import java.util.*;


public class GreedySolver {


    int estados;

    public GreedySolver(){
        setEstados(0);
    }

    public List<Machine> resolver(ArrayList<Machine> candidatos, int target) {
        ArrayList<Machine> solucion = new ArrayList<>(); // Conjunto solución (S)
        int acumulado = 0;

        while (!candidatos.isEmpty() && !esSolucion(acumulado, target)) {
            setEstados(getEstados()+1);
            // Paso 1: Seleccionar el mejor candidato (máquina con mayor producción <= faltante)
            Machine mejor = seleccionar(candidatos, target - acumulado);
            candidatos.remove(mejor);

            // Paso 2: Verificar factibilidad y agregar a solución
            if (esFactible(acumulado, mejor.getProduction(), target)) {
                solucion.add(mejor);
                acumulado += mejor.getProduction();
            }
        }

        return esSolucion(acumulado, target) ? new ArrayList<>(solucion) : null;
    }

    // ---- Métodos auxiliares según estructura de la cátedra ----
    private Machine seleccionar(ArrayList<Machine> candidatos, int faltante) {
        Machine mejorMaquina = null;
        int maxProduccionEncontrada = -1; // Inicializar con un valor que cualquier producción real superaría

        // Iteramos sobre cada máquina en la lista de candidatos
        for (Machine m : candidatos) {
            // Primero, verificamos la condición de filtro:
            // La producción de la máquina no debe exceder lo que falta
            if (m.getProduction() <= faltante) {
                // Si la máquina cumple con el filtro, la comparamos con la mejor encontrada hasta ahora
                if (m.getProduction() > maxProduccionEncontrada) {
                    // Si esta máquina produce más que la "mejor" anterior,
                    // la convertimos en la nueva mejor máquina
                    mejorMaquina = m;
                    maxProduccionEncontrada = m.getProduction();
                }
            }
        }

        // Al final del bucle, 'mejorMaquina' contendrá la máquina deseada o null si no se encontró ninguna
        return mejorMaquina;
    }

    private boolean esFactible(int acumulado, int piezasMaquina, int target) {
        return (acumulado + piezasMaquina) <= target;
    }

    private boolean esSolucion(int acumulado, int target) {
        return acumulado == target;
    }
    public int getEstados() {
        return estados;
    }

    public void setEstados(int estados) {
        this.estados = estados;
    }
}

