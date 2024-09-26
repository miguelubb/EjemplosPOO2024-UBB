package evaluaciones.control2;

import java.util.Scanner;

public class Main {
    private final Scanner sc;
    private final SistemaMatriculas sistema;

    public static void main(String[] args) {
        new Main().menu();


    }

    public Main() {
        sc = new Scanner(System.in);
        sc.useDelimiter("\r\n|[\n\r\u2028\u2029\u0085]|\t|,");

        sistema=new SistemaMatriculas();

        sistema.creaCurso(1, "Algebra 1", 100000);
        sistema.creaCurso(2, "Algebra 2", 200000);
        sistema.creaCurso(3, "Algebra 3", 300000);
        sistema.creaDiplomado(4, "Didactica del Algebra");
        sistema.agregaCursoADiplomado(1,4);
        sistema.agregaCursoADiplomado(2,4);
        sistema.agregaCursoADiplomado(3,4);
        sistema.creaPersona("11.111.111-1", "Persona 1");
        sistema.creaPersona("22.222.222-2", "Persona 2");
        sistema.creaPersona("33.333.333-3", "Persona 3");
        sistema.creaPersona("44.444.444-4", "Persona 4");
        sistema.creaPersona("55.555.555-5", "Persona 5");
        sistema.matriculaPersona("11.111.111-1", 4);
        sistema.matriculaPersona("22.222.222-2", 4);
        sistema.matriculaPersona("33.333.333-3", 1);
        sistema.matriculaPersona("44.444.444-4", 2);
        sistema.matriculaPersona("55.555.555-5", 3);
    }

    private void menu() {
        int op;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("1) Crear persona");
            System.out.println("2) Crear formación");
            System.out.println("3) Matricular persona");
            System.out.println("4) Agregar curso a diplomado");
            System.out.println("5) Lista de personas de una formación");
            System.out.println("6) Lista de formaciones de una persona");
            System.out.println("7) Salir");
            System.out.print("\n Ingrese opción: ");
            op = sc.nextInt();
            switch (op) {
                case 1 -> creaPersona();
                case 2 -> creaFormacion();
                case 3 -> matriculaPersona();
                case 4 -> agregaCursoADiplomado();
                case 5-> listaPersonasDeFormacion();
                case 6 -> listaFormacionesDePersona();
                case 7 -> System.out.println("\n Adiós...\n");
                default -> System.out.println("Opcion no válida");
            }
        } while (op != 7);
    }

    private void listaFormacionesDePersona() {
        System.out.println("...::: Listado Programa de Formación de Persona :::....");
        System.out.println("Ingrese el rut:");
        String rut = sc.next();
        String[][] formaciones=sistema.listaFormacionesDePersona(rut);
        if(formaciones.length>0) {
            String nombreEstudiante = sistema.getNombrePersona(rut);
            System.out.println("Listado Programa de Formación de Persona \"" + nombreEstudiante + "\"");
            System.out.printf("%13s %-20s %-12s %12s %n", "Código", "Nombre", "Tipo", "Costo ($)");
            int total = 0;
            for (String[] formacion : formaciones) {
                System.out.printf("%13s %-20s %-12s %12s %n", formacion[0], formacion[1], formacion[2], formacion[3]);
                total += Integer.parseInt(formacion[3]);
            }
            System.out.printf("Monto total ($): %,d %n", total);
        }else{
            System.out.println("*** No hay formaciones que mostrar ***");
        }
    }

    private void listaPersonasDeFormacion() {
        System.out.println("...::: Listado de Personas de una formación :::....");
        System.out.println("Ingrese el Código de Formación:");
        int codigo = sc.nextInt();
        String[][] personas=sistema.listaPersonasDeFormacion(codigo);
        if(personas.length>0){
            String nombreFormacion=sistema.getNombreFormacion(codigo);
            System.out.println("Listado Personas de Programa de Formación \""+ nombreFormacion+"\"");
            System.out.printf("%-13s %s%n", "RUT", "Nombre");
            for (String[] persona : personas) {
                System.out.printf("%-13s %s%n", persona[0], persona[1]);
            }
            System.out.println("Nro. total estudiantes matriculados: "+ personas.length);
        }else{
            System.out.println("*** no hay estudiantes que mostar ***");
        }
    }

    private void agregaCursoADiplomado() {
        System.out.println("...::: Agregar curso a Diplomado :::....");
        System.out.println("Ingrese el Código curso:");
        int codigoCurso = sc.nextInt();
        System.out.println("Ingrese el Código diplomado:");
        int codigoDiplomadoo = sc.nextInt();
        boolean ok=sistema.agregaCursoADiplomado(codigoCurso, codigoDiplomadoo);
        if(!ok){
            System.out.println("***No se pudo agregar el curso al diplomado ***");
        }else{
            System.out.println("*** Se ha agregado el curso al diplomado exitosamente ***");
        }

    }

    private void matriculaPersona() {
        System.out.println("...::: Matricular Persona :::....");
        System.out.println("Ingrese el rut:");
        String rut = sc.next();
        System.out.println("Ingresa el código de la formación:");
        int codigo= sc.nextInt();
        boolean ok=sistema.matriculaPersona(rut, codigo);
        if(!ok){
            System.out.println("***No se pudo matricular a la persona ***");
        }else{
            System.out.println("*** Matricula realizada exitosamente ***");
        }
    }

    private void creaFormacion() {
        System.out.println("...::: Crear Formación :::....");
        System.out.println("[1] Diplomado [2] Curso:");
        int op = sc.nextInt();
        if (op == 1) {
            System.out.println("...::: Creación de un Diplomado :::....");
        }else{
            System.out.println("...::: Creación de un Curso :::....");
        }
        System.out.println("Ingresa el código:");
        int codigo= sc.nextInt();
        System.out.println("Ingresa el nombre:");
        String nombre = sc.next();
        boolean ok;
        if(op==1){
            ok=sistema.creaDiplomado(codigo,nombre);
        }else {
            System.out.println("Ingrese el costo:");
            int costo=sc.nextInt();
            ok= sistema.creaCurso(codigo, nombre, costo);
        }
        if(!ok){
            System.out.println("*** La formación ya existe, no se puede crear de nuevo ***");
        }else{
            System.out.println("*** Formación creada exitosamente ***");
        }
    }

    private void creaPersona() {
        System.out.println("...::: Crear Persona :::....");
        System.out.println("Ingrese el rut:");
        String rut = sc.next();
        System.out.println("Ingresa el nombre:");
        String nombre = sc.next();
        boolean ok=sistema.creaPersona(rut, nombre);
        if(!ok){
            System.out.println("*** El persona ya existe, no se puede crear de nuevo ***");
        }else{
            System.out.println("*** Persona creada exitosamente ***");
        }
    }

}
