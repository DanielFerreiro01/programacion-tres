package core;

/**
 * Representa una máquina productora de autopartes
 */
public class Maquina implements Comparable<Maquina> {
    private final String id;       // Ej: "M1", "M2"
    private final int produccion;  // Piezas que produce por ciclo (ej: 7, 3)

    public Maquina(String id, int produccion) {
        if (produccion <= 0) {
            throw new IllegalArgumentException("La producción debe ser positiva");
        }
        this.id = id;
        this.produccion = produccion;
    }

    // Getters (sin setters porque es inmutable)
    public String getId() {
        return id;
    }

    public int getProduccion() {
        return produccion;
    }

    @Override
    public String toString() {
        return id + " (Producción: " + produccion + " piezas)";
    }

    // Útil para comparaciones en algoritmos
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Maquina)) return false;
        Maquina other = (Maquina) obj;
        return id.equals(other.id) && produccion == other.produccion;
    }
    public int compareTo(Maquina o) {
        return Integer.compare(o.getProduccion(), this.getProduccion());
    }

    @Override
    public int hashCode() {
        return id.hashCode() + 31 * produccion;
    }
}