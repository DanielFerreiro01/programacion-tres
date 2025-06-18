package core;

public class Maquina implements Comparable<Maquina> {
    private final String id;
    private final int produccion;

    public Maquina(String id, int produccion) {
        if (produccion <= 0) {
            throw new IllegalArgumentException("La producción debe ser positiva");
        }
        this.id = id;
        this.produccion = produccion;
    }

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