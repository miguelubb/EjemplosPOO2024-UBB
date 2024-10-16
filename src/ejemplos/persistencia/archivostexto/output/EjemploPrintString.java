package ejemplos.persistencia.archivostexto.output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class EjemploPrintString {
    public static void main(String[] args) {
        try {
            PrintStream out = new PrintStream(new FileOutputStream("src/ejemplos/persistencia/archivostexto/output/output.txt"));
            String[] nombres={"Juan", "Ana", "Lorena", "Fernando", "Alonso", "Gabriel", "Diego", "Pamela"};
            for (int i = 0; i < nombres.length; i++) {
                out.printf("%d; %s%n", i, nombres[i]);
            }
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
