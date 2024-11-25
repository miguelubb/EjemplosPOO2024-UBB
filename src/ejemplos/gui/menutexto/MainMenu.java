package ejemplos.gui.menutexto;

import java.util.*;

public class MainMenu {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do{
            System.out.println("\nMenu de Opciones");
            System.out.println("1. Crear o Abrir Archivo");
            System.out.println("2. Salir");
            System.out.print("Ingrese la opción: ");
            opcion=sc.nextInt();
            switch(opcion){
                case 1 -> abrirArchivo();
                case 2 -> System.out.println("Adios...");
                default -> System.out.println("Opcion no valida");
            }
        }while(opcion!=2);
    }


    private static void abrirArchivo() {
        System.out.print("Ingrese el nombre del archivo (si no existe se creará): ");
        String archivo=sc.next();
        EditorArchivo.display(archivo);
    }
}
