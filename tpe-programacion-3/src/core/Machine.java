package core;

/**
 * Representa una máquina productora de autopartes
 */
public class Machine implements Comparable<Machine> {
    private final String id;       // Ej: "M1", "M2"
    private final int production;  // Piezas que produce por ciclo (ej: 7, 3)

    public Machine(String id, int production) {
        if (production <= 0) {
            throw new IllegalArgumentException("La producción debe ser positiva");
        }
        this.id = id;
        this.production = production;
    }

    // Getters (sin setters porque es inmutable)
    public String getId() {
        return id;
    }

    public int getProduction() {
        return production;
    }

    @Override
    public String toString() {
        return id + " (Producción: " + production + " piezas)";
    }

    // Útil para comparaciones en algoritmos
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Machine)) return false;
        Machine other = (Machine) obj;
        return id.equals(other.id) && production == other.production;
    }
    public int compareTo(Machine o) {
        return Integer.compare(o.getProduction(), this.getProduction());
    }

    @Override
    public int hashCode() {
        return id.hashCode() + 31 * production;
    }
}