package logic;

import core.Machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedySolver {
    private int estadosGreedy = 0;

    private List<Machine> greedy(List<Machine> maquinas, int target) {
        List<Machine> seleccion = new ArrayList<>();
        int suma = 0;

        List<Machine> ordenadas = new ArrayList<>(maquinas);
        Collections.sort(ordenadas);

        for (Machine m : ordenadas) {
            estadosGreedy++; // 👈 Contamos el estado evaluado

            if (suma + m.getProduction() <= target) {
                seleccion.add(m);
                suma += m.getProduction();
                if (suma == target)
                    break;
            }
        }
        return (suma == target) ? seleccion : new ArrayList<>();
    }
}
