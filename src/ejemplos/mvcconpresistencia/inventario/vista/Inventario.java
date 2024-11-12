package ejemplos.mvcconpresistencia.inventario.vista;

import ejemplos.mvcconpresistencia.inventario.controlador.Controlador;
import ejemplos.mvcconpresistencia.inventario.excepciones.InventarioException;

import java.util.*;

public class Inventario {
    private Scanner sc;
    Controlador ctrl;

    public static void main(String[] args) {
        new Inventario().menu();
    }

    public Inventario() {
        sc = new Scanner(System.in);
        sc.useLocale(Locale.ENGLISH);
        sc.useDelimiter("[\t\r\n]+");
        ctrl = Controlador.getInstance();
    }

    public void menu() {
        int op = 0;
        System.out.println("Cargando datos guardados previamente...");
        try {
            ctrl.cargarDatos();
            System.out.println("Carga exitosa.");
        } catch (InventarioException e) {
            System.out.println("No se han cargado datos: "+e.getMessage());
        }
        do {
            System.out.println("\n Menú Principal");
            System.out.println("1) Crear un producto");
            System.out.println("2) Aumentar stock de productos");
            System.out.println("3) Disminuir stock de productos");
            System.out.println("4) Listar todos los productos");
            System.out.println("5) Listar productos bajo stock");
            System.out.println("6) Importar datos");
            System.out.println("7) Exportar datos");
            System.out.println("8) Salir");
            System.out.print("\n Ingrese opción: ");
            op = sc.nextInt();
            switch (op) {
                case 1 -> creaProducto();
                case 2 -> aumentaStock();
                case 3 -> disminuyeStock();
                case 4 -> listarTodo();
                case 5 -> listarBajoStock();
                case 6 -> importar();
                case 7 -> exportar();
                case 8 -> System.out.println("Guardando datos...");
                default -> System.out.println("Error: opcion no válida");
            }
        } while (op != 8);
        try {
            ctrl.guardarDatos();
            System.out.println("Guardado exitosa.");
        } catch (InventarioException e) {
            System.out.println("No fue posible guardar los datos: "+e.getMessage());
        }
    }

    private void exportar() {
        String filename="src/ejemplos/mvcconpresistencia/inventario/persistencia/output.txt";
        System.out.println("exportar datos");
        System.out.println("output: '"+filename+"'");
        System.out.println("[1] exportar a output [2] usar otro archvio");
        int i=sc.nextInt();
        try {
            if (i == 1) {
                ctrl.exportar(filename);
            } else {
                System.out.print("Nombre de archivo: ");
                filename = sc.next();
                ctrl.exportar(filename);
            }
        }catch (InventarioException e){
            System.out.println(e.getMessage());
        }
    }

    private void importar() {
        String filename="src/ejemplos/mvcconpresistencia/inventario/persistencia/input.txt";
        System.out.println("Importar datos");
        System.out.println("input: '"+filename+"'");
        System.out.println("[1] importar desde input [2] usar otro archvio");
        int i=sc.nextInt();
        try {
            if (i == 1) {
                ctrl.importar(filename);
            } else {
                System.out.print("Nombre de archivo: ");
                filename = sc.next();
                ctrl.importar(filename);
            }
        }catch (InventarioException e){
            System.out.println(e.getMessage());
        }

    }

    private static void printProductos(String[][] productos) {
        System.out.println("\n");
        System.out.printf("%-13s %-30s %10s %10s %10s%n", "SKU", "NOMBRE", "PRECIO", "STOCK", "STOCK MIN.");
        for (int i = 0; i < productos.length; i++) {
            String[] p = productos[i];
            System.out.printf("%-13s %-30s %10s %10s %10s%n", p[0], p[1], p[2], p[3], p[4]);
        }
        System.out.println("\n====================================\n");
    }

    private void listarBajoStock() {
        System.out.println("Listado de productos bajo stock");
        String[][] productos = ctrl.productosBajoStock();
        printProductos(productos);
    }

    private void listarTodo() {
        System.out.println("Listado de productos");
        String[][] productos = ctrl.productos();
        printProductos(productos);
    }

    private void disminuyeStock() {
        System.out.println("\n\n implementar...\n");
    }

    private void aumentaStock() {
        System.out.println("\n\n implementar...\n");

    }

    private void creaProducto() {
        System.out.println("\n\n implementar...\n");

    }
}
