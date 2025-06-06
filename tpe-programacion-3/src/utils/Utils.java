package utils;
public class Utils {
    void backtrack(ArrayList<Maquina> maquinas, int target, ArrayList<Maquina> estadoActual, int sumaActual, ArrayList<Maquina> mejorSolucion) {
        if (sumaActual == target) {//verifica si la suma actual es igual a la buscada
            if (mejorSolucion.isEmpty() || estadoActual.size() < mejorSolucion.size()) {//verifica q sea la mejor solucion
                mejorSolucion.clear();
                mejorSolucion.addAll(estadoActual);
            }
            return;
        }
        if (sumaActual > target) return;//en caso de que suma acual supere a la buscada, se realiza una poda

        for (Maquina maquina : maquinas) {
            estadoActual.add(maquina);//se agrega la maquina actual
            backtrack(maquinas, target, estadoActual, sumaActual + maquina.piezas, mejorSolucion);//suma y llama a recursividad
            estadoActual.remove(estadoActual.size() - 1); // Backtrack, el codigo se "desarma"
        }
    }
}
