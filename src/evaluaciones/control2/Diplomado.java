package evaluaciones.control2;

import java.util.*;

public class Diplomado extends Formacion{
    List<Curso> cursos;
    public Diplomado(int codigo, String nombre) {
        super(codigo, nombre);
        cursos = new ArrayList<Curso>();
    }

    @Override
    public int getCosto() {
        int sum=0;
        for (Curso curso : cursos) {
            sum+=curso.getCosto();
        }
        return (int) (sum*0.9);
    }

    @Override
    public String getTipo() {
        return "Diplomado";
    }

    public boolean addCurso(Curso curso) {
        if(cursos.contains(curso)){
            return false;
        }
        curso.addDiplomado(this);
        return cursos.add(curso);

    }
}
