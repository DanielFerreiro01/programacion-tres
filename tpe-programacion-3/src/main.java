import utils.FileLoader;
import utils.SolverUtils;

public class main {
    public static void main(String[] args) {
        FileLoader empresa = new FileLoader();
        empresa.cargarDatos("tpe-programacion-3/src/data/maquinas.txt");

        SolverUtils.resolverConBacktracking(empresa);
        SolverUtils.resolverConGreedy(empresa);
    }
}
