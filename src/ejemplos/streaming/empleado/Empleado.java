package ejemplos.streaming.empleado;

import java.util.Objects;

public class Empleado {
    private String rut;
    private String nombre;
    private long sueldo;
    private String departamento;

    public Empleado(String rut, String nombre, long sueldo, String departamento) {
        this.rut = rut;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.departamento = departamento;
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

    public long getSueldo() {
        return sueldo;
    }

    public void setSueldo(long sueldo) {
        this.sueldo = sueldo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return rut.equals(empleado.rut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rut);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sueldo=" + sueldo +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
