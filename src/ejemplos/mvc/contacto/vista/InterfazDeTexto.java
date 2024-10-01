package ejemplos.mvc.contacto.vista;

import ejemplos.mvc.contacto.controlador.Controlador;


import java.util.Arrays;
import java.util.Scanner;

public class InterfazDeTexto {
    private static final Scanner input=new Scanner(System.in)
            .useDelimiter("\t|\r\n|[\n\r\u2028\u2029\u0085]");
    private static final int SALIR=8;


    public static void main(String[] args) {
	    // write your code here
        int op;
        do {
            desplegarMenu();
            System.out.print("\nIngrese Opción: ");
            op=input.nextInt();
            switch(op){
                case 1:
                    opcion1();
                    break;
                case 2:
                    opcion2();
                    break;
                case 3:
                    opcion3();
                    break;
                case 4:
                    opcion4();
                    break;
                case 5:
                    opcion5();
                    break;
                case 6:
                    opcion6();
                    break;
                case 7:
                    opcion7();
                    break;
                case 8:
                    System.out.println("Hasta otra oportunidad!!!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }while(op!=SALIR);

    }

    private static void desplegarMenu() {
        System.out.println("*** Menú Principal ***");
        System.out.println("1) Crear Contacto");
        System.out.println("2) Listar los teléfonos de un contacto");
        System.out.println("3) Encontrar el nombre asociado a un teléfono");
        System.out.println("4) Actualiza teléfono");
        System.out.println("5) Actualiza nombre contacto");
        System.out.println("6) Eliminar teléfono de un contacto");
        System.out.println("7) Eliminar Contacto y todos sus teléfonos");
        System.out.println("8) Salir");

    }

    private static void opcion1() {
        System.out.println("**** Crear Contacto");
        System.out.println("Ingrese los datos del contacto");
        System.out.print("Nombre: ");
        String nombre=input.next();
        System.out.println("Telefono: ");
        String telefono=input.next();
        if(Controlador.getInstance().CrearContacto(nombre, telefono)){
            System.out.println("Contacto creado satisfactoriamente.");
        }else{
            System.out.println("No fue posible crear el contacto.");
        }
    }

    private static void opcion2() {
        System.out.println("*** Listar los teléfonos de un contacto");
        System.out.println("Ingrese el nombre del contacto a buscar");
        System.out.println("(puede ser una parte del nombre)");
        String nombre=input.next();
        String[][] contactos=Controlador.getInstance().buscarPorNombre(nombre);
        char[] linea=new char[75];
        Arrays.fill(linea, '_');
        System.out.println(linea);
        System.out.println("Telefonos de: "+ nombre);
        System.out.println(linea);
        System.out.printf("%2s %-50s %-20s %n", "N°", "Nombre", "Teléfono");
        for (int i = 0; i < contactos.length; i++) {
            System.out.printf("%2d %-50s %-20s %n", i, contactos[i][0],
                    contactos[i][1]);
        }
    }

    private static void opcion3() {
        System.out.println("*** Encontrar el nombre asociado a un teléfono");
        System.out.print("Ingrese el teléfono: ");
        String telefono=input.next();
        String nombre=Controlador.getInstance().buscarPorTelefono(telefono);
        System.out.println("Nombre: "+ nombre);
    }

    private static void opcion4() {
        System.out.println("*** Actualiza teléfono");
        System.out.println("Ingrese el nombre del contacto a actualizar");
        String nombre=input.next();
        String[][] contactos=Controlador.getInstance().buscarPorNombre(nombre);
        System.out.println("Telefonos de: "+ nombre);
        char[] linea=new char[75];
        Arrays.fill(linea, '_');
        System.out.println(linea);
        System.out.printf("%2s %-50s %-20s %n", "N°", "Nombre", "Teléfono");
        System.out.println(linea);
        for (int i = 0; i < contactos.length; i++) {
            System.out.printf("%2d %-50s %-20s %n", i, contactos[i][1], contactos[i][1]);
        }
        System.out.print("Ingrese el N° de la lista de telefono: ");
        int num=input.nextInt();
        System.out.print("Ingrese el nuevo teléfono: ");
        String nuevoTelefono=input.next();
        if(Controlador.getInstance().actualizarTelefono(nombre, contactos[num][1], nuevoTelefono)){
            System.out.println("Telefono actualizado");
        }else{
            System.out.println("Error al actualizar el teléfono");
        }
    }
    private static void opcion5() {
        System.out.println("*** Actualiza nombre contacto");
        System.out.print("Nombre actual: ");
        String nombre=input.next();
        System.out.println("Nombre nuevo: ");
        String nuevo=input.next();
        if(Controlador.getInstance().actualizarNombre(nombre, nuevo)){
            System.out.println("Nombre del contacto cambiado exitosamente");
        }else{
            System.out.println("Falló el cambio de nombre");
        }

    }

    private static void opcion6() {
        System.out.println("*** Eliminar teléfono de un contacto");
    }
    private static void opcion7() {
        System.out.println(" ***Eliminar Contacto y todos sus teléfonos");

    }


}
