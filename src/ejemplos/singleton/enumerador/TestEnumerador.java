package ejemplos.singleton.enumerador;

import java.util.*;

public class TestEnumerador {
    public static final int SERIE_FACTURA=Enumerador.getInstance().addSerie();
    public static final int SERIE_BOLETA=Enumerador.getInstance().addSerie();
    public static final int SERIE_CLIENTE=Enumerador.getInstance().addSerie();
    public static final int SERIE_PROVEEDOR=Enumerador.getInstance().addSerie();

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int op=0;
        do{
            System.out.println("1) nueva factura");
            System.out.println("2) nueva Boleta");
            System.out.println("3) nuevo cliente");
            System.out.println("4) nuevo proveedor");
            System.out.println("5) nuevo producto");
            System.out.println("6) salir");
            op=sc.nextInt();
            switch(op){
                case 1-> nuevaFactura();
                case 2-> nuevaBoleta();
                case 3-> nuevoCliente();
                case 4-> nuevoProveedor();
                case 5-> nuevoProducto();
                default -> System.out.println("adios...");
            }
        }while(op!=6);

    }

    private static void nuevoProducto() {
        Producto p=new Producto("Arroz UBB", "Arroz premium", 1500.0);
        System.out.println("Nuevo producto ID: "+p.getId());
    }

    private static void nuevoProveedor() {
        System.out.println("Nuevo proveedor ID: "+Enumerador.getInstance().nextValue(SERIE_PROVEEDOR));
    }

    private static void nuevoCliente() {
        System.out.println("Nuevo cliente ID: "+Enumerador.getInstance().nextValue(SERIE_CLIENTE));
    }

    private static void nuevaBoleta() {
        System.out.println("Nuevo boleta ID: "+Enumerador.getInstance().nextValue(SERIE_BOLETA));
    }

    private static void nuevaFactura() {
        System.out.println("Nuevo factura ID: "+Enumerador.getInstance().nextValue(SERIE_FACTURA));
    }

}
