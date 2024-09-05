package ejemplos.composicion.rol_privilegio;

import java.util.*;

public class Privilegio {
    private int id;
    private String nombre;

    //asociación para la implementación de la composición bidireccional con Rol
    private List<Rol> roles=new ArrayList<>();
    public Privilegio(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Privilegio that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return nombre;
    }

    public List<Rol> getRoles() {
        return roles;
    }
}
