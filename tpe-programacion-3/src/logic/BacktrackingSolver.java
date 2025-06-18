package logic;

import core.Maquina;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/*

Estrategia Backtracking:
Este algoritmo explora sistemáticamente todas las combinaciones posibles de máquinas para encontrar la solución óptima. Comienza con las máquinas ordenadas de mayor a menor producción, lo que permite encontrar soluciones eficientes más rápido mediante podas inteligentes.
Cómo funciona:
Exploración: Prueba todas las combinaciones válidas de máquinas recursivamente
Poda: Elimina ramas del árbol de búsqueda cuando:
La solución parcial ya tiene más máquinas que la mejor solución encontrada
La mejor solución encontrada es teóricamente óptima
Selección: Mantiene siempre la mejor solución encontrada (la que usa menos máquinas)
Ventajas:
Garantiza encontrar la solución óptima cuando existe
Las podas reducen significativamente el espacio de búsqueda
Permite reutilización de máquinas
Limitaciones:
Complejidad exponencial en el peor caso
Complejidad:
Temporal: O(n!) en peor caso (sin podas)
Espacial: O(n) (profundidad de recursión)
Eficiencia mejora notablemente con las podas implementadas
Métrica:
Registramos la cantidad de estados explorados para evaluar el rendimiento
*/

public class BacktrackingSolver {
    private List<Maquina> mejorSolucion;
    private int cantEstados;

    public List<Maquina> resolver(ArrayList<Maquina> maquinas, int target) {
        mejorSolucion = new ArrayList<>();
        cantEstados = 0;

        // Orden descendente para encontrar soluciones óptimas más rápido
        Collections.sort(maquinas);

        backtrack(maquinas, target, new ArrayList<>(), 0, 0);
        return mejorSolucion;
    }

    private void backtrack(List<Maquina> maquinas, int target,
                           List<Maquina> seleccionadas,
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

        for (int i = startIndex; i < maquinas.size(); i++) {
            Maquina m = maquinas.get(i);
            int nuevaSuma = acumulado + m.getProduccion();

            if (nuevaSuma > target) continue;

            seleccionadas.add(m);
            backtrack(maquinas, target, seleccionadas, nuevaSuma, i); // Permite reusar máquinas
            seleccionadas.remove(seleccionadas.size() - 1);

            // Poda adicional: si la mejor solución ya es de tamaño mínimo posible
            if (!mejorSolucion.isEmpty() && mejorSolucion.size() == (target / maquinas.get(0).getProduccion())) {
                return;
            }
        }
    }

    public int getCantEstados() {
        return cantEstados;
    }
}