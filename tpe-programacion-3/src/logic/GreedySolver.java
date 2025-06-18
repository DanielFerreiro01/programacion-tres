package logic;

import core.Maquina;

import java.util.*;

/*
    Estrategia Greedy:
        Este algoritmo resuelve el problema mediante un enfoque paso a paso. En cada iteración, seleccionamos la máquina disponible más grande que no supere la cantidad de piezas que nos faltan. La idea es acercarnos lo máximo posible al objetivo en cada selección, intentando minimizar el número total de máquinas utilizadas.

    Cómo funciona:
        Selección: En cada paso, buscamos la máquina que produzca más piezas sin exceder lo que falta para completar el objetivo.
        Verificación: Si la máquina es útil (no nos hace pasarnos del objetivo), la agregamos a la solución.
        Repetición: Seguimos hasta alcanzar el objetivo exacto o hasta que no queden máquinas útiles.

    Ventajas:
        Es rápido y fácil de entender.
        Suele dar buenos resultados en la práctica, aunque no siempre es la solución perfecta.

    Complejidad Algorítmica:
        En el peor caso, recorre todas las máquinas en cada paso. Si hay n máquinas, y en cada paso se elimina una, la complejidad es O(n²).

    Métrica:
        Contamos cuántas veces probamos máquinas ("estados generados") para comparar su eficiencia con otros métodos.
*/

public class GreedySolver {

    int estados;

    public GreedySolver(){
        setEstados(0);
    }

    public List<Maquina> resolver(ArrayList<Maquina> candidatos, int target) {
        ArrayList<Maquina> solucion = new ArrayList<>();
        int acumulado = 0;

        while (!candidatos.isEmpty() && !esSolucion(acumulado, target)) {
            setEstados(getEstados()+1);

            Maquina mejor = seleccionar(candidatos, target - acumulado);
            candidatos.remove(mejor);

            if (esFactible(acumulado, mejor.getProduccion(), target)) {
                solucion.add(mejor);
                acumulado += mejor.getProduccion();
            }
        }

        return esSolucion(acumulado, target) ? new ArrayList<>(solucion) : null;
    }

    private Maquina seleccionar(ArrayList<Maquina> candidatos, int faltante) {
        Maquina mejorMaquina = null;
        int maxProduccionEncontrada = -1;

        for (Maquina m : candidatos) {

            if (m.getProduccion() <= faltante) {

                if (m.getProduccion() > maxProduccionEncontrada) {
                    mejorMaquina = m;
                    maxProduccionEncontrada = m.getProduccion();
                }
            }
        }

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

