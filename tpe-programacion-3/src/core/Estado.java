package core;

import java.util.ArrayList;
import java.util.List;

public class Estado {
    private List<Machine> seleccionadas;  // Máquinas usadas hasta ahora
    private int acumulado;                // Piezas producidas
    private int nextMaquinaIndex;         // Próxima máquina a considerar

    public Estado(List<Machine> seleccionadas, int acumulado, int nextMaquinaIndex) {
        this.seleccionadas = new ArrayList<>(seleccionadas);  // Copia defensiva
        this.acumulado = acumulado;
        this.nextMaquinaIndex = nextMaquinaIndex;
    }

    // Getters
    public List<Machine> getSeleccionadas() { return seleccionadas; }
    public int getAcumulado() { return acumulado; }
    public int getNextMaquinaIndex() { return nextMaquinaIndex; }

    // Método para crear un nuevo estado al agregar una máquina
    public Estado agregarMaquina(Machine m, int index) {
        List<Machine> nuevas = new ArrayList<>(this.seleccionadas);
        nuevas.add(m);
        return new Estado(nuevas, this.acumulado + m.getProduction(), index);
    }
}

