package ejemplos.persistencia.archivostexto.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EjemploLectura {
    public static void main(String[] args) {
        File file=new File("src/ejemplos/persistencia/archivostexto/input/data.txt");
        try {
            Scanner sc=new Scanner(file);
            sc.useDelimiter("\n|;");
            //sc.useLocale(Locale.FRENCH);//separador decimal es la ,
            while(sc.hasNext()) {
                int id=Integer.parseInt(sc.next().trim());
                String nombre=sc.next().trim();
                String[] lista=sc.next().trim().split(",");
                System.out.printf("%s (%d telefonos)%n",nombre, lista.length);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
