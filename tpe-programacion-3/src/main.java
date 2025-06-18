import core.Machine;
import logic.BacktrackingSolver;
import logic.GreedySolver;
import utils.FileLoader;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        FileLoader empresa= new FileLoader();
        empresa.cargarDatos("tpe-programacion-3/src/maquinas.txt");

        BacktrackingSolver solver = new BacktrackingSolver();
        List<Machine> solucion = solver.resolver(
                new ArrayList<>(empresa.getMaquinas()),
                empresa.getTotalPiezas()
        );
        GreedySolver greedySolver= new GreedySolver();
        List<Machine> solucion2 = greedySolver.resolver(
                new ArrayList<>(empresa.getMaquinas()),
                empresa.getTotalPiezas()
        );

        System.out.println("Solución encontrada en bracktracking (" + solucion.size() + " máquinas):");
        for (Machine m : solucion) {
            System.out.println(m.getId());  // Aquí mostramos el nombre de la máquina
        }
        System.out.println("El total de estados generados es "+ solver.getCantEstados());

        System.out.println("Solución encontrada  en greedy (" + solucion2.size() + " máquinas):");
        for (Machine m : solucion2) {
            System.out.println(m.getId());  // Aquí mostramos el nombre de la máquina
        }
        System.out.println("El total de estados generados es "+ greedySolver.getEstados());


    }
}
