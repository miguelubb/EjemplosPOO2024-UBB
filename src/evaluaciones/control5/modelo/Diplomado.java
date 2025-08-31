package evaluaciones.control5.modelo;

import java.util.ArrayList;

public class Diplomado extends Formacion {
    private final ArrayList<Curso> cursos;

    public Diplomado(int codigo, String nombre, Modalidad modalidad) {
        super(codigo, nombre, modalidad);
        cursos = new ArrayList<>();
    }

    @Override
    public int getCosto() {
        int costo=0;
        for (Curso curso : cursos) {
            costo += curso.getCosto();
        }
        return costo;
    }

    public void addCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
            curso.addDiplomado(this);
        }
    }
}
