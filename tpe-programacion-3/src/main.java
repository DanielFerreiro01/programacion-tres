import core.Machine;
import logic.BacktrackingSolver;
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

        System.out.println("Solución encontrada (" + solucion.size() + " máquinas):");
        for (Machine m : solucion) {
            System.out.println(m.getId());  // Aquí mostramos el nombre de la máquina
        }
        System.out.println("El total de estados generados es "+ solver.getCantEstados());
    }
}
