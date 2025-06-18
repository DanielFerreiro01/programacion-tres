package logic;

//import core.Estado;
import core.Machine;

//import javax.crypto.Mac;
import java.util.*;

/*public class BacktrackingSolver {
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

    void backtrack(List<Machine> machines, int target, List<Machine> seleccionadas, int acumulado, int index) {
        if (acumulado == target) {
            if (seleccionadas.size() < minMaquinas) {
                mejorSolucion = new ArrayList<>(seleccionadas);
                minMaquinas = seleccionadas.size();
            }
            return;
        }

        if (seleccionadas.size() >= minMaquinas) return;
        if(acumulado>target) return;


        for (int i = index; i < machines.size(); i++) {
            Machine m = machines.get(i);
            seleccionadas.add(m);
            backtrack(machines, target, seleccionadas, acumulado + m.getProduction(), i); // i para permitir reutilización
            seleccionadas.remove(seleccionadas.size() - 1); // backtrack explícito
        }
    }

}*/

public class BacktrackingSolver  {
    private List<Machine> mejorSolucion;
    private int minMaquinas;
    private HashMap<String, Boolean> memo;

    public int getCantEstados() {
        return cantEstados;
    }

    public void setCantEstados(int cantEstados) {
        this.cantEstados = cantEstados;
    }

    private int cantEstados;

    public List<Machine> resolver(ArrayList<Machine> machines, int target) {
        mejorSolucion = new ArrayList<>();
        minMaquinas = Integer.MAX_VALUE;
        memo = new HashMap<>();
        setCantEstados(0);
       //Ordenar máquinas por producción ascendente
        Collections.sort(machines);
        backtrack(machines, target, new ArrayList<>(), 0, 0);

        return mejorSolucion;
    }

    private boolean backtrack(List<Machine> machines, int target,
                              List<Machine> seleccionadas,
                              int acumulado,
                              int nextIndex) {
        String clave = acumulado + "-" + nextIndex;
        cantEstados++;
        if (memo.containsKey(clave)) {
            return memo.get(clave);
        }

        if (acumulado == target) {
            if (seleccionadas.size() < minMaquinas) {
                mejorSolucion = new ArrayList<>(seleccionadas);
                minMaquinas = seleccionadas.size();
            }
            memo.put(clave, true);
            return true;
        }

        if (seleccionadas.size() >= minMaquinas) {
            memo.put(clave, false);
            return false;
        }

        boolean encontrado = false;
        int machinesSize = machines.size();

        for (int i = nextIndex; i < machinesSize; i++) {
            Machine m = machines.get(i);
            int nuevaSuma = acumulado + m.getProduction();

            if (nuevaSuma > target) {
                break;
            }

            seleccionadas.add(m);
            encontrado = backtrack(machines, target, seleccionadas, nuevaSuma, i) || encontrado;
            seleccionadas.remove(seleccionadas.size() - 1);
        }

        memo.put(clave, encontrado);
        return encontrado;
    }
}