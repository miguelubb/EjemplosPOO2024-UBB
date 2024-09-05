package evaluaciones.control1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DemoCajeroAutomatico {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //1) Utilizar un objeto de la clase ArrayList para almacenar objetos CajeroAutomatico.
        ArrayList<CajeroAutomatico> misCajeros = new ArrayList<>();

        //2) Crear 10 cajeros, leyendo los datos necesarios. No olvide almacenar cada cajero en la colección.
        String id;
        int nroMoneda1, nroMoneda2, estado;
        CajeroAutomatico cajero;
        for (int i = 0; i < 10; i++) {
            System.out.println("Creación del cajero " + (i + 1) + "°");
            System.out.print("Ingrese el ID del cajero: ");
            id = sc.next();
            System.out.println("Ingrese la cantidad de billetes de $" + CajeroAutomatico.MONEDA1);
            nroMoneda1 = sc.nextInt();
            System.out.println("Ingrese la cantidad de billetes de $" + CajeroAutomatico.MONEDA2);
            nroMoneda2 = sc.nextInt();
            System.out.println("Ingrese el estado del cajero " + (i + 1) + " ([1] Operativo, [2] Inoperativo) : ");
            estado = sc.nextInt();
            cajero = new CajeroAutomatico(id);
            cajero.setEstado(estado == 1 ? Estado.OPERATIVO : Estado.INOPERATIVO);
            cajero.agregaVilletes(nroMoneda1, CajeroAutomatico.MONEDA1);
            cajero.agregaVilletes(nroMoneda2, CajeroAutomatico.MONEDA2);
            misCajeros.add(cajero);
            System.out.println("**** cajero creado con éxito!!");
        }

        //3) Dejar inoperativo en octavo cajero creado.
        misCajeros.get(7).setEstado(Estado.INOPERATIVO);

        //4) Agregar $50.000 en billetes de $10.000 y $80.000 en billetes de $20.000 a todos los cajeros.
        for (CajeroAutomatico c : misCajeros) {
            c.agregaVilletes(5, CajeroAutomatico.MONEDA1);
            c.agregaVilletes(4, CajeroAutomatico.MONEDA2);
        }

        /*
        5) Girar $10.000 desde el primer cajero creado, $30.000 desde el tercer cajero creado
              y $90.000 desde el último cajero creado. Se debe desplegar cuantos billetes de cada
              moneda entregó cada cajero desde el cual se hizo un giro.
         */

        int[] g1 = misCajeros.get(0).gira(10000);
        int[] g2 = misCajeros.get(2).gira(30000);
        int[] g3 = misCajeros.get(misCajeros.size() - 1).gira(90000);
        System.out.printf("Giro del primero cajero : Villetes de $%,d = %,d, Villetes de $%,d = %,d %n", CajeroAutomatico.MONEDA1, g1[0], CajeroAutomatico.MONEDA2, g1[1]);
        System.out.printf("Giro del tercer cajero  : Villetes de $%,d = %,d, Villetes de $%,d = %,d %n", CajeroAutomatico.MONEDA1, g2[0], CajeroAutomatico.MONEDA2, g2[1]);
        System.out.printf("Giro del último cajero  : Villetes de $%,d = %,d, Villetes de $%,d = %,d %n", CajeroAutomatico.MONEDA1, g3[0], CajeroAutomatico.MONEDA2, g3[1]);

        /* 6)
        	Desplegar un listado con los datos de los cajeros automáticos existentes.
            El formato del listado debe ser el que se muestra a continuación:
            LISTADO DE CAJEROS --- Fecha: dd-mm-aaaa
            ----------------------------------------------------------
            Id, Saldo ($), Estado
            XXXXXX, 9999999, xxxxxxxxx
            XXXXXX, 9999999, xxxxxxxxx

            Note que el título del listado incluye la fecha al ejecutar el programa (fecha del sistema).

         */
        LocalDate hoy= LocalDate.now();
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.printf("LISTADO DE CAJEROS --- Fecha: %s%n",hoy.format(dtf));
        System.out.println("----------------------------------------------------------");
        System.out.println("ID, Saldo ($), Estado");
        for (CajeroAutomatico c : misCajeros) {
            System.out.println(c);
        }

        System.out.println();
        System.out.println("alternativa de reporte para desplegar ordenado por columnas");
        System.out.println("(no pedido en el control )");
        System.out.println();
        System.out.printf("LISTADO DE CAJEROS --- Fecha: %s%n",hoy.format(dtf));
        System.out.println("----------------------------------------------------------");
        System.out.printf("%4s  %10s  %-10s  %n","ID", "Saldo ($)", "Estado");
        for (CajeroAutomatico c : misCajeros) {
            String[] data=c.toString().split(", ");
            System.out.printf("%4s  %,10d  %-10s  %n",data[0], Integer.parseInt(data[1]), data[2]);
        }
    }
}
