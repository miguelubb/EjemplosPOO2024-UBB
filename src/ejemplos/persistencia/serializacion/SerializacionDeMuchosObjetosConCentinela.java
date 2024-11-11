package ejemplos.persistencia.serializacion;

import java.io.*;
import java.util.*;

public class SerializacionDeMuchosObjetosConCentinela {
    public static void main(String[] args) {
        writeObjects();
        readObject();
    }

    private static void readObject() {
        List<Persona> personas = new ArrayList<>();
        ObjectOutputStream oos = null;
        File out = new File("src/ejemplos/persistencia/serializacion/Personas.obj");
        //leer la lista de personas desde el archivo
        try {
            FileInputStream fis = new FileInputStream(out);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object p;
            while ((p = ois.readObject()) != null) {
                personas.add((Persona) p);
            }
            System.out.println("");
            System.out.println("Lista de personas después de serializacion");
            for (Persona persona : personas) {
                System.out.println(persona);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeObjects() {
        List<Persona> personas = new ArrayList<Persona>();
        personas.add(new Persona("Jose", "930124567"));
        personas.add(new Persona("Juan", "930124567"));
        personas.add(new Persona("Alejandra", "930124567"));
        personas.add(new Persona("Rocío", "930124567"));

        System.out.println("Lista de personas antes de serializacion");
        for (Persona persona : personas) {
            System.out.println(persona);
        }

        ObjectOutputStream oos = null;
        File out = new File("src/ejemplos/persistencia/serializacion/Personas.obj");
        try {
            //FileOutputStream output=new FileOutputStream(out,out.exists());
            FileOutputStream output = new FileOutputStream(out, false);
            oos = new ObjectOutputStream(output);
            for (Persona persona : personas) {

                oos.writeObject(persona);
            }
            //Al terminar se inserta el valor centinela, que puede ser un null o un objeto especial que marque fin de archivo
            oos.writeObject(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
