package logic;

import core.Estado;
import core.Machine;

import javax.crypto.Mac;
import java.util.ArrayList;
import java.util.List;

public class BacktrackingSolver {
    private List<Machine> mejorSolucion;    // Solo guardamos la mejor
    private int minMaquinas;               // Cantidad mínima de máquinas usadas

    // --------------------         SOLUCIÓN 1         --------------------

    public List<Machine> resolver(List<Machine> machines, int target) {
        mejorSolucion = new ArrayList<>();
        minMaquinas = Integer.MAX_VALUE;  // Inicializar con un valor grande

        Estado estadoInicial = new Estado(new ArrayList<>(), 0, 0);
        backtrack(machines, target, estadoInicial);

        return mejorSolucion;
    }

    private void backtrack(List<Machine> machines, int target, Estado estado) {
        // Condición de éxito
        if (estado.getAcumulado() == target) {
            if (estado.getSeleccionadas().size() < minMaquinas) {
                mejorSolucion = new ArrayList<>(estado.getSeleccionadas());
                minMaquinas = mejorSolucion.size();
            }
            return;
        }

        // Poda: Si ya usamos más máquinas que la mejor solución, no seguimos
        if (estado.getSeleccionadas().size() >= minMaquinas) {
            return;
        }

        // Explorar máquinas desde nextMaquinaIndex (permite reusar máquinas)
        for (int i = estado.getNextMaquinaIndex(); i < machines.size(); i++) {
            Machine m = machines.get(i);

            // Poda: Si agregar la máquina actual excede el objetivo, no seguir
            if (estado.getAcumulado() + m.getProduction() <= target) {
                Estado nuevoEstado = estado.agregarMaquina(m, i);  // i (no i+1) permite reusar
                backtrack(machines, target, nuevoEstado);
                // No necesitamos "quitar" porque trabajamos con estados inmutables
            }
        }
    }

    // --------------------         SOLUCIÓN 2         --------------------

    void backtrack(ArrayList<Machine> maquinas, int target, ArrayList<Machine> estadoActual, int sumaActual, ArrayList<Machine> mejorSolucion) {
        if (sumaActual == target) {//verifica si la suma actual es igual a la buscada
            if (mejorSolucion.isEmpty() || estadoActual.size() < mejorSolucion.size()) {//verifica q sea la mejor solucion
                mejorSolucion.clear();
                mejorSolucion.addAll(estadoActual);
            }
            return;
        }
        if (sumaActual > target) return;//en caso de que suma acual supere a la buscada, se realiza una poda

        for (Machine maquina : maquinas) {
            estadoActual.add(maquina);//se agrega la maquina actual
            backtrack(maquinas, target, estadoActual, sumaActual + maquina.getProduction(), mejorSolucion);//suma y llama a recursividad
            estadoActual.remove(estadoActual.size() - 1); // Backtrack, el codigo se "desarma"
        }
    }
}

