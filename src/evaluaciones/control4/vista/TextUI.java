package evaluaciones.control4.vista;

import evaluaciones.control4.controlador.ControladorBellasArtes;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TextUI {
    private static TextUI instance = new TextUI();

    Scanner sc = new Scanner(System.in);

    private TextUI() {
    }

    public static TextUI getInstance() {
        return instance;
    }

    public void menu() {
        int op;
        do {
            System.out.print("""
                    ...:::: Menú Principal
                    1) Generar autores y pintura
                    2) Listar autores
                    3) Listar pinturas
                    4) Exportar datos
                    5) Importar datos
                    6) Lista de autores de un pais en particular
                    7) Lista de autores con obras de un estilo en particular
                    8) Lista de Pinturas de un autor
                    9) Listado de Pinturas creada en un rango de fechas
                    10) SALIR
                    
                    Ingrese su opción: 
                    """);
            op = sc.nextInt();
            switch (op) {
                case 1 -> opcion1();
                case 2 -> opcion2();
                case 3 -> opcion3();
                case 4 -> opcion4();
                case 5 -> opcion5();
                case 6 -> opcion6();
                case 7 -> opcion7();
                case 8 -> opcion8();
                case 9 -> opcion9();
                case 10 -> opcion10();
                default -> System.out.println("*** Error: Opción no válida, intente de nuevo...");
            }
        } while (op != 10);
    }

    private void opcion1() {
        try {
            System.out.println("Generando datos de prueba ....");
            ControladorBellasArtes.getInstancia().GenerarAutoresYPinturas();
            System.out.println("... Datos generados exitosamente");
        } catch (RuntimeException e) {
            System.out.println("*** Error: Se ha producido un error inesperado. No se pudo cargar los datos: " + e.getMessage());
        }
    }

    private void opcion2() {
        System.out.println("...::: Listado de autores");
        String[][] data = ControladorBellasArtes.getInstancia().listarAutores();
        if (data.length == 0) {
            System.out.println("No se ha encontrado las autores");
            return;
        }
        mostrarTablaAutores(data);
    }

    private static void mostrarTablaAutores(String[][] data) {
        String[] column = {"ID", "NOMBRE", "PAIS", "NACIMIENTO",  "PREMIOS", "PINTURAS"};
        int[] len = {5, 20, 13, 13, 7, 8};
        char[] align = {'r', 'l', 'l', 'r', 'r', 'r'};
        Tabla.printTable(data, column, len, align, 0);
        System.out.printf("Total de autores: %,d%n%n", data.length);
    }

    private void opcion3() {
        System.out.println("...::: Listado de pinturas");
        String[][] data = ControladorBellasArtes.getInstancia().listarPinturas();
        if (data.length == 0) {
            System.out.println("No se ha encontrado las pinturas");
            return;
        }
        mostrarTablaPinturas(data);
    }

    private static void mostrarTablaPinturas(String[][] data) {
        String[] column = {"TÍTULO", "ESTILO", "TECNICA", "AUTOR", "AÑO"};
        int[] len = {40, 18, 13, 20, 13};
        char[] align = {'l', 'l', 'l', 'l', 'r'};
        Tabla.printTable(data, column, len, align, 0);
        System.out.printf("Total de pinturas: %,d%n%n", data.length);
    }

    private void opcion4() {
        try {
            ControladorBellasArtes.getInstancia().exportar();
            System.out.println("Se han generado los archivos de exportación:");
            System.out.println(" 1.- autores.txt");
            System.out.println(" 2.- pinturas.txt");
        } catch (FileNotFoundException e) {
            System.out.println("***Error: no fue posible generar los archivos de exportación:");
            System.out.println(" 1.- autores.txt");
            System.out.println(" 2.- pinturas.txt");
        }
    }

    private void opcion5() {
        try {
            ControladorBellasArtes.getInstancia().importar();
            System.out.println("Se han leido correctamente los archivos de importación:");
            System.out.println(" 1.- autores.txt");
            System.out.println(" 2.- pinturas.txt");
        } catch (FileNotFoundException e) {
            System.out.println("***Error: no se encontraron los archivos de importación:");
            System.out.println(" 1.- autores.txt");
            System.out.println(" 2.- pinturas.txt");
        }
    }

    private void opcion6() {
        //6) Lista de autores por nacionalidad
        System.out.println("...:::: Lista de autores de un pais en particular");
        System.out.println("Ingrese el pais:");
        String pais = sc.next();
        String[][] data = ControladorBellasArtes.getInstancia().listarAutoresPorNacionalidad(pais);
        if (data.length == 0) {
            System.out.println("No se ha encontrado autores");
            return;
        }
        mostrarTablaAutores(data);
    }

    private void opcion7() {
        //          7) Lista de autores con obras de un estilo en particular
        System.out.println("...:::: Lista de autores con obras de un estilo en particular");
        System.out.println("Ingrese el Estilo:");
        String estilo = sc.next();
        String[][] data = ControladorBellasArtes.getInstancia().listarAutoresPorEstilo(estilo);
        if (data.length == 0) {
            System.out.println("No se ha encontrado autores");
            return;
        }
        mostrarTablaAutores(data);

    }

    private void opcion8() {
        //8) Lista de Pinturas de un autor
        System.out.println("...::: Listado de pinturas de un autor");
        System.out.println("Ingrese el ID de un Autor:");
        int idAutor = sc.nextInt();
        String[][] data = ControladorBellasArtes.getInstancia().listarPinturasAutor(idAutor);
        if (data.length == 0) {
            System.out.println("No se ha encontrado las pinturas");
            return;
        }
        mostrarTablaPinturas(data);

    }

    private void opcion9() {
        //          9) Listado de Pinturas creada en un rango de fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("...::: Listado de pinturas en un rango de fechas");
        System.out.println("Ingrese año inicial: ");
        int ini = sc.nextInt();
        System.out.println("Ingrese la año final: ");
        int fin = sc.nextInt();
        String[][] data = ControladorBellasArtes.getInstancia().listarPinturasEnRango(ini, fin);
        if (data.length == 0) {
            System.out.println("No se ha encontrado las pinturas");
            return;
        }
        mostrarTablaPinturas(data);
    }

    private void opcion10() {
        System.out.println("Adios...");
    }
}
