package ejemplos.composicion.venta;

import java.util.Scanner;

public class VentaTextUI {
    private static final int SALIR = 2;
    private static Scanner sc = new Scanner(System.in);
    private static Fachada sistema = new Fachada();

    public static void main(String[] args) {
        sc.useDelimiter("\r\n|[\n\r\u2028\u2029\u0085,;\t]");
        int op;
        init();
        do {
            op = menu();
            switch (op) {
                case 1 -> venta("Alberto Cruz");
                case 2 -> System.out.println("Adios...");
                default -> System.out.println("Opcion no valida");
            }
        } while (op != SALIR);
    }

    private static void init() {
        sistema.agregaCliente(11111111, '1', "Juan Rojas Alarcón");
        sistema.agregaCliente(22222222, '2', "Juan Rojas Alarcón");
        sistema.agregaCliente(33333333, '3', "Juan Rojas Alarcón");

        sistema.agregaProducto(1, "Arroz 1k", "Arroz doña Josefa", 10, 1000);
        sistema.agregaProducto(2, "Lentejas 1k", "Lentejas doña Josefa", 10, 1000);
        sistema.agregaProducto(3, "Garbanzos 1k", "Garbanzos doña Josefa", 10, 1000);
        sistema.agregaProducto(4, "Porotos 1k", "Porotos doña Josefa", 10, 1000);
        sistema.agregaProducto(5, "Arbejas 1k", "Arbejas doña Josefa", 10, 1000);

    }

    private static String leeString(String msg) {
        System.out.printf("%40s:", msg);
        return sc.next();
    }

    private static int leeInt(String msg) {
        System.out.printf("%40s:", msg);
        return sc.nextInt();
    }

    private static int leeNatural(String msg) {
        int num = leeInt(msg);
        while (num <= 0) {
            System.out.println("*** Error: el número debe ser > 0");
            System.out.println("...::: Intente de nuevo");
            num = leeInt(msg);
        }
        return num;
    }

    private static void venta(String nombreVendedor) {
        String rut = leeString("Ingrese rut del cliente [99.999.999-X]");
        String[] rutParts = rut.trim().split("-");
        int numRut = Integer.parseInt(rutParts[0].trim().replace(".", ""));
        String[] cliData = sistema.buscaCliente(numRut);
        if (cliData == null) {
            System.out.println("Cliente no encontrado");
            return;
        }
        System.out.println("Nobre de cliente: " + cliData[1]);
        int id = leeNatural("Ingrse id venta");
        boolean error = !sistema.creaVenta(id, numRut, nombreVendedor);
        if (error) {
            System.out.println("*** Error al crear venta");
            return;
        }
        int n = leeNatural("Cantidad de productos a comprar");
        for (int i = 0; i < n; i++) {
            //solicita los datos de la venta de cada item
            int idProd = leeNatural("Ingrese id producto");
            String[] prodData = sistema.buscaProducto(idProd);
            while (prodData == null) {
                System.out.println("*** Error: Producto no encontrado");
                System.out.println("...::: Intente de nuevo");
                idProd = leeInt("Ingrese id producto");
                prodData = sistema.buscaProducto(idProd);
            }
            System.out.println("...::: Datos del producto");
            System.out.printf("      Nombre : %s%n", prodData[0]);
            System.out.printf(" Descripción : %s%n", prodData[1]);
            System.out.printf("Stock Actual : %s%n", prodData[2]);
            System.out.printf("      Precio : %s%n", prodData[3]);
            System.out.printf("   descuento : %s%n", prodData[4]);
            double cantidad = leeNatural("Ingrese cantidad: ");
            while (cantidad > Double.parseDouble(prodData[2])) {
                System.out.println("*** Error: stock insuficiente");
                System.out.println("...::: Intente de nuevo");
                cantidad = leeNatural("Ingrese cantidad: ");
            }
            double subTotal = Double.parseDouble(prodData[3]) * (1 - Double.parseDouble(prodData[4])) * cantidad;
            System.out.printf("   SubTotal : %,.2f%n", subTotal);
            sistema.agregProductoAVenta(id,idProd,cantidad);
        }
        //terminado el ingreso de los productos, se despliega los datos de la venta:
        String[] dataVenta = sistema.buscaVenta(id);
        if (dataVenta == null) {
            System.out.println("*** Error: Venta no encontrado");
            return;
        }
        System.out.println("Venta terminada.");
        System.out.println("Imprimiendo el comprobante....");
        System.out.println();
        System.out.println("....:::: Comprobante de Venta ::::........");
        System.out.printf("             id : %d%n", id);
        System.out.printf("          Fecha : %s%n", dataVenta[0]);
        System.out.printf("           Hora : %s%n", dataVenta[1]);
        System.out.printf("       Vendedor : %s%n", dataVenta[2]);
        System.out.printf("        Cliente : %s%n", dataVenta[3]);
        System.out.printf(" num. productos : %s%n", dataVenta[4]);
        System.out.printf("        Total $ : %s%n", dataVenta[5]);
        System.out.println("-------------------------------------------");
        System.out.println();

    }


    public static int menu() {
        System.out.println("Menú principal");
        System.out.println("1. Venta");
        System.out.println("2. Salir");
        System.out.print("Ingrese un opcion: ");
        int op = sc.nextInt();
        while (op > 2 || op < 1) {
            System.out.println("*** Error: opción no válida");
            System.out.println("...::: Intente de nuevo:");
            op = sc.nextInt();
        }
        return op;

    }
}
