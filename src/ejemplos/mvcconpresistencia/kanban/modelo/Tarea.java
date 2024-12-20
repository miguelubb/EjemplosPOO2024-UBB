package ejemplos.mvcconpresistencia.kanban.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Tarea implements Serializable {
    int id;
    String nombre;
    String descripcion;
    String responsable;
    LocalDateTime creacion;
    LocalDateTime termino;
    LocalDateTime plazo;

    public Tarea(int id, String nombre, String descripcion, String responsable, LocalDateTime plazo) {
        this.id=id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.plazo = plazo;
        creacion = LocalDateTime.now();
        termino = null;
    }
    public int getId(){
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public LocalDateTime getPlazo() {
        return plazo;
    }

    public void setPlazo(LocalDateTime plazo) {
        this.plazo = plazo;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public LocalDateTime getTermino() {
        return termino;
    }
    public void terminar(){
        termino = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarea tarea)) return false;
        return id == tarea.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
