package evaluaciones.control2;

import java.util.*;

public  abstract class Formacion {
    private int codigo;
    private String nombre;
    List<Persona> personas;
    public Formacion(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        personas = new ArrayList<>();
    }
    public int getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public abstract int getCosto();
    public abstract String getTipo();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Formacion formacion)) return false;
        return codigo == formacion.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public boolean addPersona(Persona persona) {
        if(personas.contains(persona)){
            return false;
        }
        return personas.add(persona);
    }

    public Persona[] getPersonas() {
        return personas.toArray(new Persona[personas.size()]);
    }

}
