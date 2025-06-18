package utils;

import core.Machine;
import logic.BacktrackingSolver;
import logic.GreedySolver;

import java.util.ArrayList;
import java.util.List;

public class SolverUtils {

    public static void resolverConBacktracking(FileLoader empresa) {
        BacktrackingSolver solver = new BacktrackingSolver();
        List<Machine> solucion = solver.resolver(
                new ArrayList<>(empresa.getMaquinas()),
                empresa.getTotalPiezas()
        );

        System.out.println("Solución encontrada en backtracking (" + solucion.size() + " máquinas):");
        for (Machine m : solucion) {
            System.out.println(m.getId());
        }
        System.out.println("El total de estados generados es " + solver.getCantEstados());
    }

    public static void resolverConGreedy(FileLoader empresa) {
        GreedySolver solver = new GreedySolver();
        List<Machine> solucion = solver.resolver(
                new ArrayList<>(empresa.getMaquinas()),
                empresa.getTotalPiezas()
        );

        System.out.println("Solución encontrada en greedy (" + solucion.size() + " máquinas):");
        for (Machine m : solucion) {
            System.out.println(m.getId());
        }
        System.out.println("El total de candidatos generados es " + solver.getEstados());
    }
}
