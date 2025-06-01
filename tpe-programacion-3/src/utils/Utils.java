package utils;
public class Utils {
    void backtrack(ArrayList<Maquina> maquinas, int target, ArrayList<Maquina> estadoActual, int sumaActual, ArrayList<Maquina> mejorSolucion) {
        if (sumaActual == target) {
            if (mejorSolucion.isEmpty() || estadoActual.size() < mejorSolucion.size()) {
                mejorSolucion.clear();
                mejorSolucion.addAll(estadoActual);
            }
            return;
        }
        if (sumaActual > target) return;

        for (Maquina maquina : maquinas) {
            estadoActual.add(maquina);
            backtrack(maquinas, target, estadoActual, sumaActual + maquina.piezas, mejorSolucion);
            estadoActual.remove(estadoActual.size() - 1); // Backtrack
        }
    }
}
