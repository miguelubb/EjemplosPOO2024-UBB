package evaluaciones.control2;

import java.util.*;

public class Persona {
    private String rut;
    private String nombre;
    List<Formacion> formaciones;

    public Persona(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
        formaciones = new ArrayList();
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean matricula(Formacion formacion) {
        if(formaciones.contains(formacion)) {
            return false;
        }
        formaciones.add(formacion);
        formacion.addPersona(this);
        return true;
    }
    public Formacion[] getFormaciones() {
        return formaciones.toArray(new Formacion[formaciones.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(rut, persona.rut);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rut);
    }
}
