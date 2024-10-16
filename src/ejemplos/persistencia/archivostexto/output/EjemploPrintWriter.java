package ejemplos.persistencia.archivostexto.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EjemploPrintWriter {
    public static void main(String[] args) {
        PrintWriter pw = new PrintWriter(System.out);
        pw.println("Hello World");
        pw.printf("Hola %s", "Miguel");
        pw.close();//si no se cierra el pw, no hay escritura en la salida.

        //probando ahora en un archivo
        try {
            //RUTA ABSOLUTA=>C:\Users\ThinkBook14G2\IdeaProjects\EjemplosPOO2024-UBB\src\ejemplos\persistencia\archivostexto\output\out.txt
            //Working Directory=>C:\Users\ThinkBook14G2\IdeaProjects\EjemplosPOO2024-UBB\
            //RUTA RELATIVA=>src\ejemplos\persistencia\archivostexto\output\out.txt
            File nuevoArchivo=new File("src/ejemplos/persistencia/archivostexto/output/out.txt");
            PrintWriter pw2 = new PrintWriter(nuevoArchivo);
            pw2.println("Hello World");
            pw2.printf("Hola %s", "Miguel");
            pw2.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
