package evaluaciones.control3.vista;

import evaluaciones.control3.controlador.ControlSelloDisco;
import evaluaciones.control3.excepciones.SelloDiscoException;

import java.time.LocalDate;
import java.util.*;

public class UISelloDisco {
    private Scanner input = new Scanner(System.in);

    public UISelloDisco() {
        input.useDelimiter("\r\n|[\n\r\u2028\u2029\u0085]|\t");
    }


    public void menu() {
        int op;
        String[] opciones = {"Crear Album", "Crear Canción", "Agregar canción a Álbum",
                "Eliminar Canción de Álbum", "Listar Álbumes", "Lisar Canciones de un Género",
                "Listar canciones de un álbum", "Salir"};
        do {
            for (int i = 0; i < opciones.length; i++) {
                System.out.printf("%d) %s %n", i + 1, opciones[i]);
            }
            System.out.print("Ingrese opción: ");
            op = input.nextInt();
            switch (op) {
                case 1 -> creaAlbum();
                case 2 -> creaCancion();
                case 3 -> agregaCancionEnAlbum();
                case 4 -> eliminaCancionDeAlbum();
                case 5 -> listaAlbumes();
                case 6 -> listaCancionesDeGenero();
                case 7 -> listaCancionesDeAlbum();
                case 8 -> System.out.println("Adios...");
            }
        } while (op != 8);
    }

    private void listaCancionesDeAlbum() {
        System.out.println("...::: Listado de Canción a un Álbum :::.....");
        System.out.print("Ingresa el nombre del álbum: ");
        String album = input.next();
        try {
            String[] cancionesAlbum = ControlSelloDisco.getInstance().listaCancionesDeAlbum(album);
            System.out.println();
            System.out.println("LISTADO DE CANCIONES DEL ALBUM "+album.toUpperCase());
            System.out.printf("%-50s %15s %-15s %-15s %n", "Nombre", "Duración", "Genero", "Interprete");
            for (String cancion : cancionesAlbum) {
                String[] data = cancion.split(";");
                System.out.printf("%-50s %15s %-15s %-15s %n", data[0], data[1], data[2], data[3]);
            }
            System.out.println();
        } catch (SelloDiscoException e) {
            System.out.println("***ERROR: " + e.getMessage() + " ***");
        }
    }

    private void listaCancionesDeGenero() {
        System.out.println("...::: Listado de Canción a un género :::.....");
        System.out.print("Ingresa el nombre del género: ");
        String genero = input.next();
        try {
            String[] cancionesGenero = ControlSelloDisco.getInstance().listaCancionesDeGenero(genero);
            System.out.println();
            System.out.println("LISTADO DE CANCIONES DEL GÉNERO "+genero.toUpperCase());
            System.out.printf("%-50s %15s %-15s %15s %n", "Nombre", "Duración", "Intérprete", "N° de Álbumes");
            for (String cancion : cancionesGenero) {
                String[] data = cancion.split(";");
                System.out.printf("%-50s %15s %-15s %15s %n", data[0], data[1], data[3], data[4]);
            }
            System.out.println();
        } catch (SelloDiscoException e) {
            System.out.println("***ERROR: " + e.getMessage() + " ***");
        }
    }

    private void listaAlbumes() {
        String[] albumes = ControlSelloDisco.getInstance().listaAlbunes();
        System.out.println();
        System.out.println("LISTADO DE ALBUMES");
        System.out.printf("%-50s %-15s %15s %15s %n", "Nombre", "Fecha creacion", "Duracion", "N° de Canciones");
        for (String album : albumes) {
            String[] data = album.split(";");
            System.out.printf("%-50s %-15s %15s %15s %n", data[0], data[1], data[2], data[3]);
        }
        System.out.println();

    }

    private void eliminaCancionDeAlbum() {
        System.out.println("...::: Elimina Canción a un Álbum :::.....");
        System.out.print("Ingresa el titulo de la canción: ");
        String cancion = input.next();

        System.out.print("Ingresa el nombre del álbum: ");
        String album = input.next();
        try {
            ControlSelloDisco.getInstance().eliminaCancionDeAlbum(cancion, album);
            System.out.println("Se elimina la canción al álbum exitosamente");
        } catch (SelloDiscoException e) {
            System.out.println("***ERROR: " + e.getMessage() + " ***");
        }
    }

    private void agregaCancionEnAlbum() {
        System.out.println("...::: Agrega Canción a un Álbum :::.....");
        System.out.print("Ingresa el titulo de la canción: ");
        String cancion = input.next();

        System.out.print("Ingresa el nombre del álbum: ");
        String album = input.next();
        try {
            ControlSelloDisco.getInstance().agregaCancionEnAlbum(cancion, album);
            System.out.println("Se agrega la canción al álbum exitosamente");
        } catch (SelloDiscoException e) {
            System.out.println("***ERROR: " + e.getMessage() + " ***");
        }
    }

    private void creaCancion() {
        System.out.println("...::: Crea Canción :::.....");
        System.out.print("Ingresa el titulo: ");
        String titulo = input.next();
        System.out.print("Ingresa duración: ");
        float duracion = input.nextFloat();
        System.out.print("Ingresa género: ");
        String genero = input.next();
        System.out.print("Ingresa el nombre del intérprete: ");
        String nombreInterprete = input.next();
        try {
            ControlSelloDisco.getInstance().creaCancion(titulo, duracion, genero, nombreInterprete);
            System.out.println("creación de la canción con éxito");
        } catch (SelloDiscoException e) {
            System.out.println("***ERROR: " + e.getMessage() + " ***");
        }
    }

    private void creaAlbum() {
        System.out.println("...::: crea album :::.....");
        System.out.print("Ingresa el titulo del album: ");
        String nombre = input.next();
        System.out.print("fecha de creacion (dd-mm-aaaa): ");
        String[] fecha = input.next().split("-");
        LocalDate fechaCreacion;
        fechaCreacion = LocalDate.of(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0]));
        try {
            ControlSelloDisco.getInstance().creaAlbum(nombre, fechaCreacion);
            System.out.println("creación del álbum con éxito");
        } catch (SelloDiscoException e) {
            System.out.println("***ERROR: " + e.getMessage() + " ***");
        }
    }
}
