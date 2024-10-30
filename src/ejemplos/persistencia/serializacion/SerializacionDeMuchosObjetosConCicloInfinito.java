package ejemplos.persistencia.serializacion;

import java.io.*;
import java.util.*;

public class SerializacionDeMuchosObjetosConCicloInfinito {
    public static void main(String[] args) {
        writeObjects();
        readObjects();
    }

    private static void readObjects() {
        List<Persona> personas=new ArrayList<>();
        ObjectOutputStream oos = null;
        File out = new File("src/ejemplos/persistencia/serializacion/Personas.obj");
        //leer la lista de personas desde el archivo
        try {
            FileInputStream fis = new FileInputStream(out);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    // Leer el siguiente objeto del archivo
                    personas.add((Persona) ois.readObject());
                } catch (EOFException e) {
                    // Fin del archivo alcanzado
                    break;
                }
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}