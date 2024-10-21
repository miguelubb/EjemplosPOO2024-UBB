package ejemplos.persistencia.archivostexto.ambos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class BuscadorAvaluoFiscal {
    public static void main(String[] args) {
        //## Ejercicio:
        //implemente un menú con dos opciones:
        //menu
        // busqueda por año, marca, modelo
        // busqueda por rango tazación (min, max)
        mostrar5Primeros();
        extrePorMarca("CHEVROLET", "chevrolet.csv");
        marcasChinas();
    }

    private static void marcasChinas() {
        //Código SII;Año;Tipo;Marca;Modelo;Versión;Cilindrada (CC);Potencia (HP);Combustible;Transmisión;Marchas;Tracción;País;Tasación 2024;Permiso 2024
        try {
            Scanner input = new Scanner(new File("src/ejemplos/persistencia/archivostexto/ambos/tasacion.csv"));
            String linea;
            String[] data;
            Set<String> marcas=new HashSet<>();
            while (input.hasNext()) {
                linea = input.nextLine();
                data = linea.split(";");
                if (data[12] != null && data[12].toUpperCase().equals("CHINA")) {
                    marcas.add(data[3]);
                }
            }
            input.close();

            PrintStream output = new PrintStream(new File("src/ejemplos/persistencia/archivostexto/ambos/marcas_chinas.csv"));
            for (String marca : marcas) {
                output.println(marca);
            }
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo con los datos o no se puede crear archivo de salida");
        }
    }

    private static void extrePorMarca(String marca, String filename) {
        //guarda en el archivo filename todos los vehículos según marca
        try {
            Scanner input = new Scanner(new File("src/ejemplos/persistencia/archivostexto/ambos/tasacion.csv"));
            PrintStream output = new PrintStream(new File("src/ejemplos/persistencia/archivostexto/ambos/" + filename));
            String linea;
            String[] data;

            while (input.hasNext()) {
                linea = input.nextLine();
                data = linea.split(";");
                if (data[3].toUpperCase().equals(marca.toUpperCase())) {
                    for (int j = 0; j < data.length - 1; j++) {
                        output.printf("%s;", data[j]);
                    }
                    output.println(data[data.length - 1]);
                }
            }
            input.close();
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo con los datos o no se puede crear archivo de salida");
        }
    }


    public static void mostrar5Primeros() {
        //Código SII;Año;Tipo;Marca;Modelo;Versión;Cilindrada (CC);Potencia (HP);Combustible;Transmisión;Marchas;Tracción;País;Tasación 2024;Permiso 2024
        try {
            Scanner sc = new Scanner(new File("src/ejemplos/persistencia/archivostexto/ambos/tasacion.csv"));
            String linea;
            String[] data;

            for (int i = 0; i < 11 && sc.hasNext(); i++) {
                linea = sc.nextLine();
                data = linea.split(";");
                for (int j = 0; j < data.length; j++) {
                    System.out.printf(" %-12s ", data[j].length() < 12 ? data[j] : data[j].substring(0, 12));
                }
                System.out.println();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo con los datos");
        }
    }
}
