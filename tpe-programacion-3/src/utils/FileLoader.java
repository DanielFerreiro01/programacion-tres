package utils;

import core.Maquina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    private int totalPiezas;
    private List<Maquina> maquinas;

    public FileLoader() {
        this.maquinas = new ArrayList<>();
    }

    public void cargarDatos(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    this.totalPiezas = Integer.parseInt(linea.trim());
                    primeraLinea = false;
                } else {
                    String[] partes = linea.split(",");  // Cambiado a coma (,) en lugar de punto (.)
                    if (partes.length == 2) {
                        String nombre = partes[0].trim();
                        int cantidad = Integer.parseInt(partes[1].trim());
                        maquinas.add(new Maquina(nombre, cantidad));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en formato numérico: " + e.getMessage());
        }
    }

    // Getters
    public int getTotalPiezas() {
        return totalPiezas;
    }

    public List<Maquina> getMaquinas() {
        return new ArrayList<>(maquinas); // Copia defensiva
    }
}
