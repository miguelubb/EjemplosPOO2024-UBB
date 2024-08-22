package ejemplos.asociacion.cobranza;

import java.util.*;

public class Cliente {
    private String rut ;
    private String nombre;
    private String email;

    //implementación de asociación con deuda:
    //un clinte puede tener 0 o muchas deudas
    private List<Deuda> deudas=new ArrayList<>();
    public Cliente() {
        rut="";
        nombre="";
        email="";
    }

    public Cliente(String rut, String nombre, String email) {
        this.rut = rut;
        this.nombre = nombre;
        this.email = email;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(rut, cliente.rut);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rut);
    }

    public boolean addDeuda(Deuda deuda) {
        //esta clase no se preocupa de la integridad referencial
        //el responsable es deuda.
        return deudas.add(deuda);
    }

    public void eliminarDeuda(Deuda deuda) {
        //no se preocupa de la integridad referencial
        deudas.remove(deuda);
    }
}
