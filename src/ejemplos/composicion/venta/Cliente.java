package ejemplos.composicion.venta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {
    private int rut;
    private char dv;
    private String nombre;

    //asociación con venta
    private List<Venta> ventas=new ArrayList<>();

    public Cliente(int rut, char dv, String nombre) {
        this.rut = rut;
        this.dv = dv;
        this.nombre = nombre;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public char getDv() {
        return dv;
    }

    public void setDv(char dv) {
        this.dv = dv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return rut == cliente.rut;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rut);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "rut=" + rut +
                ", dv=" + dv +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
