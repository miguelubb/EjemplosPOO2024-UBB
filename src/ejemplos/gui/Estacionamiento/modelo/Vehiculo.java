package ejemplos.gui.Estacionamiento.modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Vehiculo {
    public Vehiculo(String patente, String marca, String modelo, String color) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }

    public Vehiculo() {
        patente="";
        marca="";
        modelo="";
        color="";
    }

    private String patente, marca, modelo, color;
    private ArrayList<EntradaSalida> entradasYSalidas=new ArrayList<>();

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(patente, vehiculo.patente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patente);
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "patente='" + patente + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    //pre: no debre estar repetido
    public boolean addEntradaYSalida(EntradaSalida es){
        return entradasYSalidas.add(es);
    }
}
