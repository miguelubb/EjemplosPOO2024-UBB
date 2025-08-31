package evaluaciones.control5.modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Persona {
    private String rut;
    private String nombre;

    private final ArrayList<Formacion> formaciones;

    public Persona(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
        formaciones = new ArrayList<>();
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
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

    public boolean matricula(Formacion programa) {
        if (!formaciones.contains(programa)) {
            formaciones.add(programa);
            programa.addPersona(this);
            return true;
        }
        return false;
    }

    public Formacion[] getFormaciones() {
        return formaciones.toArray(new Formacion[0]);
    }
}
