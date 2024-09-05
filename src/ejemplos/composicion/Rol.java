package ejemplos.composicion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rol {
    private int id;
    private String nombre;

    private List<Privilegio> privilegios = new ArrayList<>();

    public Rol(int id, String nombre) {
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
        if (!(o instanceof Rol rol)) return false;
        return id == rol.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        StringBuffer sf=new StringBuffer();
        sf.append(nombre);
        sf.append(" [");
        for (Privilegio p : privilegios) {
            sf.append(String.format("\"%s\"",p));
            sf.append(", ");
        }
        sf.delete(sf.length()-2, sf.length());
        sf.append("]");
        return sf.toString();
    }

    //la clae Rol es la responsable de mantener la consistencia de los datos de la relación
    //esta clase hace la asociación bidireccional
    public boolean agregaPrivilegio(Privilegio p) {
        if (privilegios.contains(p)) {
            return false;
        }
        p.getRoles().add(this);
        return privilegios.add(p);
    }

    public boolean eliminaPrivilegio(Privilegio p) {
        p.getRoles().remove(this);
       return privilegios.remove(p);
    }
    public boolean tienePrivilegio(Privilegio p) {
        return privilegios.contains(p);
    }
    public List<Privilegio> getPrivilegios() {
        return privilegios;
    }
}
