package logic;

import core.Machine;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;

public class BacktrackingSolver {
    private List<Machine> mejorSolucion;
    private int cantEstados;

    public List<Machine> resolver(ArrayList<Machine> machines, int target) {
        mejorSolucion = new ArrayList<>();
        cantEstados = 0;

        // Orden descendente para encontrar soluciones óptimas más rápido
        Collections.sort(machines);

        backtrack(machines, target, new ArrayList<>(), 0, 0);
        return mejorSolucion;
    }

    private void backtrack(List<Machine> machines, int target,
                           List<Machine> seleccionadas,
                           int acumulado,
                           int startIndex) {
        cantEstados++;

        // Poda: si ya tenemos una solución mejor que la potencial actual
        if (!mejorSolucion.isEmpty() && seleccionadas.size() >= mejorSolucion.size()) {
            return;
        }

        if (acumulado == target) {
            if (mejorSolucion.isEmpty() || seleccionadas.size() < mejorSolucion.size()) {
                mejorSolucion = new ArrayList<>(seleccionadas);
            }
            return;
        }

        for (int i = startIndex; i < machines.size(); i++) {
            Machine m = machines.get(i);
            int nuevaSuma = acumulado + m.getProduction();

            if (nuevaSuma > target) continue;

            seleccionadas.add(m);
            backtrack(machines, target, seleccionadas, nuevaSuma, i); // Permite reusar máquinas
            seleccionadas.remove(seleccionadas.size() - 1);

            // Poda adicional: si la mejor solución ya es de tamaño mínimo posible
            if (!mejorSolucion.isEmpty() && mejorSolucion.size() == (target / machines.get(0).getProduction())) {
                return;
            }
        }
    }

    public int getCantEstados() {
        return cantEstados;
    }
}