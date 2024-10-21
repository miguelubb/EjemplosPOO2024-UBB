package ejemplos.mvc.kanban.modelo;

import java.time.LocalDateTime;

public class Tarea {
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
}
