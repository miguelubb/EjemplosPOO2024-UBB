package evaluaciones.control5.controlador;

import evaluaciones.control5.modelo.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class SistemaMatriculas {
    private static SistemaMatriculas instance=null;
    private final ArrayList<Persona> personas;
    private final ArrayList<Formacion> formaciones;

    private SistemaMatriculas() {
        personas = new ArrayList<>();
        formaciones = new ArrayList<>();
        try {
            generaDatosIniciales();
        } catch (SistemaMatriculasException e) {
            throw new RuntimeException(e);
        }
    }

    public static SistemaMatriculas getInstance(){
        if(instance == null){
            instance=new SistemaMatriculas();
        }
        return instance;
    }

    public void generaDatosIniciales() throws SistemaMatriculasException {
        personas.add(new Persona("12.345.678-0","Juan Rozas"));
        personas.add(new Persona("13.445.388-1","Leonor Montoya"));
        personas.add(new Persona("17.330.173-2","Daniela Lara"));
        personas.add(new Persona("11.908.345-3","Rafael Diaz"));
        personas.add(new Persona("23.124.235-7","Samuel Ramirez"));
        formaciones.add(new Curso(1234,"Inteligencia de Negocios",Modalidad.PRESENCIAL,15000));
        formaciones.add(new Curso(2345,"Introducción a Ciberseguridad",Modalidad.EN_LINEA,180000));
        formaciones.add(new Curso(3456,"Bases de Datos no SQL",Modalidad.EN_LINEA,190000));
        formaciones.add(new Curso(4567,"Desarrollo de Aplicaciones Web",Modalidad.EN_LINEA,200000));
        Diplomado diplomado = new Diplomado(11023,"Transformación Digital Empresas",Modalidad.HIBRIDO);
        formaciones.add(diplomado);
        diplomado.addCurso((Curso)formaciones.get(0));
        diplomado.addCurso((Curso)formaciones.get(1));
        personas.get(0).matricula(formaciones.get(2));
        personas.get(1).matricula(formaciones.get(2));
        personas.get(2).matricula(diplomado);
        personas.get(3).matricula(diplomado);
    }

    public void creaPersona(String rut, String nombre) throws SistemaMatriculasException {
        if (findPersona(rut).isEmpty()) {
            personas.add(new Persona(rut, nombre));
        } else {
            throw new SistemaMatriculasException("Ya existe una persona con el rut dado");
        }
    }

    public void creaDiplomado(int codigo, String nombre, Modalidad modalidad)
            throws SistemaMatriculasException {
        if (findFormacion(codigo).isEmpty()) {
            formaciones.add(new Diplomado(codigo, nombre, modalidad));
        } else {
            throw new SistemaMatriculasException("Ya existe un programa de formación con el código dado");
        }
    }

    public void creaCurso(int codigo, String nombre, Modalidad modalidad, int costo)
            throws SistemaMatriculasException {
        if (findFormacion(codigo).isEmpty()) {
            formaciones.add(new Curso(codigo, nombre,modalidad, costo));
        } else {
            throw new SistemaMatriculasException("Ya existe un programa de formación con el código dado");
        }
    }

    public void matriculaPersona(String rut, int codigo) throws SistemaMatriculasException {
        Optional<Persona> personaOptional = findPersona(rut);
        Optional<Formacion> formacionOptional = findFormacion(codigo);
        if (personaOptional.isPresent()) {
            if (formacionOptional.isPresent()) {
                if (!personaOptional.get().matricula(formacionOptional.get())) {
                    throw new SistemaMatriculasException("La persona con el rut dado " +
                            "ya está matriculada en el programa de formación con el código dado");
                }
            } else {
                throw new SistemaMatriculasException("No existe un programa de formación con el código dado");
            }
        } else {
            throw new SistemaMatriculasException("No existe una persona con el rut dado");
        }
    }

    public void agregaCursoADiplomado(int codigoCurso, int codigoDiplomado)
            throws SistemaMatriculasException {
        Optional<Formacion> formacionCursoOptional = findFormacion(codigoCurso);
        Optional<Formacion> formacionDiplomadoOptional = findFormacion(codigoDiplomado);
        if (formacionCursoOptional.isPresent() && formacionCursoOptional.get() instanceof Curso) {
            if (formacionDiplomadoOptional.isPresent() && formacionDiplomadoOptional.get() instanceof Diplomado) {
                ((Diplomado) formacionDiplomadoOptional.get()).addCurso((Curso) formacionCursoOptional.get());
            } else {
                throw new SistemaMatriculasException("No existe un diplomado con el código dado");
            }
        } else {
            throw new SistemaMatriculasException("No existe un curso con el codigo dado");
        }
    }

    public Optional<String> getNombreFormacion(int codigo) {
        Optional<Formacion> formacionOptional = findFormacion(codigo);
        return formacionOptional.map(Formacion::getNombre);
    }

    public Optional<String> getNombrePersona(String rut) {
        Optional<Persona> personaOptional = findPersona(rut);
        return personaOptional.map(Persona::getNombre);
    }

    public String[][] listaFormaciones() {
        return formaciones.stream()
                .map(formacion -> new String[]{
                        String.valueOf(formacion.getCodigo()),
                        formacion.getNombre(),
                        formacion.getModalidad().toString(),
                        formacion.getClass().getSimpleName(),
                        String.format("%,d", formacion.getCosto())
                })
                .toArray(String[][]::new);
    }

    public String[][] listaPersonas() {
        return personas.stream()
                .map(persona -> new String[] {
                        persona.getRut(),
                        persona.getNombre()
                })
                .toArray(String[][]::new);
    }

    public String[][] listaPersonasDeFormacion(int codigo) {
        Optional<Formacion> formacionOptional = findFormacion(codigo);
        return formacionOptional
                .map(formacion -> Arrays.stream(formacion.getPersonas())
                .map(persona -> new String[]{
                        persona.getRut(),
                        persona.getNombre()
                })
                .toArray(String[][]::new)).orElseGet(() -> new String[0][0]);
    }

    public String[][] listaFormacionesDePersona(String rut) throws SistemaMatriculasException {
        Optional<Persona> personaOptional = findPersona(rut);
        if (personaOptional.isEmpty()) {
            throw new SistemaMatriculasException("No existe una persona con el rut dado");
        }
        return personaOptional
                .map(persona -> Arrays.stream(persona.getFormaciones())
                .map(formacion -> new String[]{
                        String.valueOf(formacion.getCodigo()),
                        formacion.getNombre(),
                        formacion.getModalidad().toString(),
                        formacion.getClass().getSimpleName(),
                        String.format("%,d",formacion.getCosto())
                })
                .toArray(String[][]::new)).orElseGet(() -> new String[0][0]);

    }

    private Optional<Persona> findPersona(String rut) {
        return personas.stream()
                .filter(persona -> persona.getRut().equalsIgnoreCase(rut))
                .findFirst();
    }

    private Optional<Formacion> findFormacion(int codigo) {
        return formaciones.stream()
                .filter(formacion -> formacion.getCodigo() == codigo)
                .findFirst();
    }
}
