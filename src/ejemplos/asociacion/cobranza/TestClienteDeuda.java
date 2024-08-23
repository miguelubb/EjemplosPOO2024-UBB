package ejemplos.asociacion.cobranza;

import java.time.*;
import java.util.Arrays;

public class TestClienteDeuda {
    public static void main(String[] args) {
        Cliente juan = new Cliente("11.11.111-1", "Juna Pablo", "juan.pablo@fake.email.cl");
        Cliente Ana = new Cliente("22.222.222-2", "Ana María", "ana.maria@fake.email.cl");
        Deuda deuda1 = new Deuda(juan, "Compra según boleta 2234", LocalDate.of(2024, 8, 20), 100000);
        Deuda deuda2 = new Deuda(juan, "Compra según boleta 2235", LocalDate.of(2024, 8, 21), 100000);
        Deuda deuda3 = new Deuda(juan, "Compra según boleta 2236", LocalDate.of(2024, 8, 22), 100000);
        reporteDeDeuda(juan);
        reporteDeDeuda(Ana);
        System.out.println("cambiando el propietadrio de la última deuda a Ana");
        deuda3.setCliente(Ana);
        reporteDeDeuda(Ana);
    }

    public static void reporteDeDeuda(Cliente cli) {
        System.out.println(lineaDoble(70));
        System.out.println(centrar("*** Reporte de Deuda cliente ***", 70));
        System.out.println("\n Datos del cliente");
        System.out.println(lineaDoble(18));
        System.out.printf("%7s: %s%n","Rut",cli.getRut());
        System.out.printf("%7s: %s%n","Nombre",cli.getNombre());
        System.out.printf("%7s: %s%n","email",cli.getEmail());

        System.out.println("\n"+centrar("..:: Lista de deudas ::..",70));
        System.out.println(lineaDoble(70));
        System.out.printf("| %10s | %-40s | %10s |%n", "Fecha","Motivo", "Monto");

        for (String[] deuda : cli.listaDeDeudas()) {
            System.out.printf("|-%10s-+-%-40s-+-%10s-|%n", linea(10), linea(40), linea(10));
            System.out.printf("| %10s | %-40s | %10s |%n", deuda[0],deuda[1],deuda[2]);
        }
        System.out.println(lineaDoble(70));
    }

    private static String centrar(String s, int ancho) {
        if (s.length() > ancho) return s;
        char[] left = new char[(ancho - s.length()) / 2];
        char[] right = new char[ancho - s.length() - left.length];
        Arrays.fill(left, ' ');
        Arrays.fill(right, ' ');
        return new String(left) + s + new String(right);
    }

    private static String linea(int largo){
        char[] linea=new char[largo];
        Arrays.fill(linea,'-');
        return new String(linea);
    }
    private static String lineaDoble(int largo){
        char[] linea=new char[largo];
        Arrays.fill(linea,'=');
        return new String(linea);
    }

}
