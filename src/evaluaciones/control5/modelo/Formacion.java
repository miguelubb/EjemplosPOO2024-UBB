package evaluaciones.control5.modelo;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Formacion {
    private final int codigo;
    private String nombre;
    private Modalidad modalidad;

    private final ArrayList<Persona> personasMatriculadas;

    public Formacion(int codigo, String nombre, Modalidad modalidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.modalidad = modalidad;
        personasMatriculadas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public abstract int getCosto();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Formacion formacion)) return false;
        return codigo == formacion.codigo;
    }

    public boolean addPersona(Persona persona) {
        if (!personasMatriculadas.contains(persona)) {
            personasMatriculadas.add(persona);
            return true;
        }
        return false;
    }

    public Persona[] getPersonas() {
        return personasMatriculadas.toArray(new Persona[0]);
    }
}
