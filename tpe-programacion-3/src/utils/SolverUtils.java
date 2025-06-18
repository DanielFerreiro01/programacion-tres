package utils;

import core.Maquina;
import logic.BacktrackingSolver;
import logic.GreedySolver;

import java.util.ArrayList;
import java.util.List;

public class SolverUtils {

    public static void resolverConBacktracking(FileLoader empresa) {
        BacktrackingSolver solver = new BacktrackingSolver();
        List<Maquina> solucion = solver.resolver(
                new ArrayList<>(empresa.getMaquinas()),
                empresa.getTotalPiezas()
        );

        mostrarSolucion(solucion, "backtracking", solver.getCantEstados());
    }

    public static void resolverConGreedy(FileLoader empresa) {
        GreedySolver solver = new GreedySolver();
        List<Maquina> solucion = solver.resolver(
                new ArrayList<>(empresa.getMaquinas()),
                empresa.getTotalPiezas()
        );

        mostrarSolucion(solucion, "greedy", solver.getEstados());
    }

    public static void mostrarSolucion(List<Maquina> solucion, String nombreAlgoritmo, int estados) {
        if (solucion == null || solucion.isEmpty()) {
            System.out.println("No se encontró una solución con " + nombreAlgoritmo + ".");
            return;
        }

        System.out.println("Solución encontrada en " + nombreAlgoritmo + " (" + solucion.size() + " máquinas):");
        for (Maquina m : solucion) {
            if (m != null) {
                System.out.println(m.getId());
            }
        }
        System.out.println("El total de estados/candidatos generados es " + estados);
    }
}
