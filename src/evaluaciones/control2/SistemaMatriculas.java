package evaluaciones.control2;

import java.util.ArrayList;
import java.util.List;

public class SistemaMatriculas {
    List<Persona> personas;
    List<Formacion> formaciones;

    public SistemaMatriculas() {
        personas = new ArrayList<>();
        formaciones = new ArrayList<>();
    }

    public boolean creaPersona(String rut, String nombre) {
        Persona p = new Persona(rut, nombre);
        if (personas.contains(p)) {
            return false;
        }
        return personas.add(p);
    }

    public boolean creaDiplomado(int codigo, String nombre) {
        return creaFormacion(new Diplomado(codigo, nombre));
    }

    public boolean creaCurso(int codigo, String nombre, int costo) {
        return creaFormacion(new Curso(codigo, nombre, costo));
    }

    private boolean creaFormacion(Formacion formacion) {
        if (formaciones.contains(formacion)) {
            return false;
        }
        return formaciones.add(formacion);
    }

    public boolean matriculaPersona(String rut, int codigo) {
        //hay que buscar el curso y la persona, si uno de ellos no está falla el método.
        Persona p = buscarPersona(rut);
        Formacion f = buscarFormacion(codigo);
        if (p == null || f == null) {
            return false;
        }
        return p.matricula(f);
    }

    private Formacion buscarFormacion(int codigo) {
        for (Formacion f : formaciones) {
            if (f.getCodigo() == codigo) {
                return f;
            }
        }
        return null;
    }

    private Persona buscarPersona(String rut) {
        for (Persona p : personas) {
            if (p.getRut().equals(rut)) {
                return p;
            }
        }
        return null;
    }

    public boolean agregaCursoADiplomado(int codigoCurso, int codigoDiplomado) {
        Formacion d = buscarFormacion(codigoDiplomado);
        Formacion c = buscarFormacion(codigoCurso);
        if (d == null || !d.getTipo().equals("Diplomado")) {
            return false;
        }
        if (c == null || !c.getTipo().equals("Curso")) {
            return false;
        }
        Diplomado diplomado = (Diplomado) d;
        Curso curso = (Curso) c;
        return diplomado.addCurso(curso);
    }

    public String[][] listaPersonasDeFormacion(int codigo) {
        Formacion f = buscarFormacion(codigo);
        if(f==null){
            return new String[0][];
        }
        Persona[] listaPersonas = f.getPersonas();
        String[][] out = new String[listaPersonas.length][2];
        for (int i = 0; i < listaPersonas.length; i++) {
            out[i][0] = listaPersonas[i].getRut();
            out[i][1] = listaPersonas[i].getNombre();
        }
        return out;
    }

    public String[][] listaFormacionesDePersona(String rut) {
        Persona p = buscarPersona(rut);
        if(p==null){
            return new String[0][];
        }
        Formacion[] listaFormaciones = p.getFormaciones();
        String[][] out = new String[listaFormaciones.length][4];
        for (int i = 0; i < listaFormaciones.length; i++) {
            out[i][0] = String.valueOf(listaFormaciones[i].getCodigo());
            out[i][1] = listaFormaciones[i].getNombre();
            out[i][2] = listaFormaciones[i].getTipo();
            out[i][3] = String.valueOf(listaFormaciones[i].getCosto());
        }
        return out;
    }

    public String getNombreFormacion(int codigo) {
        Formacion f = buscarFormacion(codigo);
        if (f == null) {
            return null;
        }
        return f.getNombre();
    }

    public String getNombrePersona(String rut) {
        Persona persona = buscarPersona(rut);
        if (persona == null) {
            return null;
        }
        return persona.getNombre();
    }
}

