package ejemplos.basicos.Enumeracion;

import java.util.Objects;

public class Estudiante {
    private String rut;
    private String nombre;
    private String email="";
    private EstadoEstudiante estado;

    public Estudiante(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
        email="";
        estado=EstadoEstudiante.NO_VIGENTE;
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

    public EstadoEstudiante getEstado() {
        return estado;
    }

    public void setEstado(EstadoEstudiante estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", estado=" + estado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(rut, that.rut);
    }

    public boolean toEquals(Estudiante otro) {
        return this.rut.equals(otro.rut);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(rut);
    }
}
