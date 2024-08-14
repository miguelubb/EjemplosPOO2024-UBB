package ejemplos.basicos.persona;

import java.time.LocalDate;
import java.time.Period;

public class Persona {
    String nombre;
    String correo;
    String telefono;
    LocalDate fechaNacimiento;
    int id;

    public Persona() {
    }

    public Persona(String nombre, String correo, String telefono, LocalDate fechaNacimiento, int id) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.id = id;
    }

    public Persona(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int edad(){
        Period p=Period.between(fechaNacimiento, LocalDate.now());
        return p.getYears();
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
