package ejemplos.composite.carpetas;

import java.util.*;

public class TestComposite {
    public static void main(String[] args) {
        Carpeta raiz=new Carpeta("",100);
        Carpeta misDoc=new Carpeta("Mis Documentos",100);
        Carpeta tareas=new Carpeta("Tareas",100);
        Carpeta ejemplos=new Carpeta("ejemplos",100);
        Carpeta java=new Carpeta("Java",100);
        Carpeta html=new Carpeta("HTML",100);
        Carpeta xml=new Carpeta("XML",100);
        Archivo file0=new Archivo("Hola.java",1100);
        Archivo file1=new Archivo("Hola.html",1200);
        Archivo file2=new Archivo("Hola.xml",1300);
        Archivo file3=new Archivo("Hola4.java",1400);
        Archivo file4=new Archivo("Hola5.java",1500);

        List<Elemento> unidad=List.of(raiz,misDoc,tareas,ejemplos,java,html,xml,
                file0, file1, file2, file3, file4);
        //mover carpetas
        misDoc.moveCarpeta(raiz);
        tareas.moveCarpeta(misDoc);
        ejemplos.moveCarpeta(misDoc);
        java.moveCarpeta(ejemplos);
        html.moveCarpeta(ejemplos);
        xml.moveCarpeta(ejemplos);
        file0.moveCarpeta(java);
        file1.moveCarpeta(html);
        file2.moveCarpeta(xml);
        file3.moveCarpeta(java);
        file4.moveCarpeta(java);

        for (Elemento e : unidad) {
            System.out.printf("[%s] %s: %.2f KB. %n",e.getTipo(),e.getRuta(),e.getTam()/1000f);
        }


    }
}
