package ejemplos.mvcconpresistencia.kanban.modelo;
import ejemplos.mvcconpresistencia.kanban.excepciones.KanbanException;

import java.io.Serializable;
import java.util.*;

public class ListaDeTareas implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    //para implementar la asociación con tarea
    private List<Tarea> tareas;
    public ListaDeTareas(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tareas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public boolean addTarea(Tarea tarea) {
        return tareas.add(tarea);
    }
    public Optional<Tarea> removeTarea(int id) {
        for(int i=0; i<tareas.size(); i++){
            Tarea tarea=tareas.get(i);
            if(tarea.getId()==id){
                tareas.remove(i);
                return Optional.of(tarea);
            }
        }
        return Optional.empty();
    }
    public Tarea getTarea(int pos) throws KanbanException {
        if(pos<0 || pos>=tareas.size()){
            throw new KanbanException("Posición de la tarea fuera de rango");
        }
        return tareas.get(pos);
    }

    public int nroTareas() {
        return tareas.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListaDeTareas that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
