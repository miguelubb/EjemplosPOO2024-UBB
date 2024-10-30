package ejemplos.mvcconpresistencia.kanban.controlador;

import ejemplos.mvcconpresistencia.kanban.excepciones.KanbanException;
import ejemplos.mvcconpresistencia.kanban.modelo.ListaDeTareas;
import ejemplos.mvcconpresistencia.kanban.modelo.Tarea;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

//singleton
public class Controlador {
    //Singleton en 3 pasos!!!
    //1) crear una variable estática para guardar la única instancia
    private static final Controlador instance = new Controlador();

    //2) crear método estático para obtener la instancia
    public static Controlador getInstance() {
        return instance;
    }

    //3) constructor private
    private Controlador() {

    }

    //atributos del controlador.
    List<ListaDeTareas> listasDeListas = new ArrayList<ListaDeTareas>();

    public boolean agregarLista(int id, String nombre, String descripcion) {
        ListaDeTareas lista = new ListaDeTareas(id, nombre, descripcion);
        if (!listasDeListas.contains(lista)) {
            return listasDeListas.add(lista);
        }
        return false;
    }

    public boolean crearTarea(int idLista, int id, String nombre, String descripcion, String responsable, LocalDateTime plazo) {
        Tarea tarea = new Tarea(id, nombre, descripcion, responsable, plazo);
        Optional<ListaDeTareas> lista = buscarLista(idLista);
        if (lista.isPresent()) {
            return lista.get().addTarea(tarea);
        }
        return false;
    }

    private Optional<ListaDeTareas> buscarLista(int idLista) {
        for (ListaDeTareas lista : listasDeListas) {
            if (lista.getId() == idLista) {
                return Optional.of(lista);
            }
        }
        return Optional.empty();
    }

    public void moverTarea(int idTarea, int idListaOrigen, int idListaDestino) throws KanbanException {
        Optional<ListaDeTareas> origen = buscarLista(idListaOrigen);
        Optional<ListaDeTareas> destino = buscarLista(idListaDestino);
        if (origen.isPresent() && destino.isPresent()) {
            Optional<Tarea> opTarea = origen.get().removeTarea(idTarea);
            if (opTarea.isPresent()) {
                Tarea t = opTarea.get();
                destino.get().addTarea(t);
                return;
            }
            throw new KanbanException("La tarea no se encuentra en la lista de origen");
        }
        throw new KanbanException("La lista de origen o la de destino no existen");
    }

    public String[] getListas() {
        List<String> out = new ArrayList<>();
        for (ListaDeTareas lista : listasDeListas) {
            out.add("[" + lista.getId() + "]" + lista.getNombre());
        }
        return out.toArray(new String[out.size()]);
    }

    public String[] getTareas(int idLista) {
        List<String> out = new ArrayList<>();
        Optional<ListaDeTareas> opLista = buscarLista(idLista);
        if (opLista.isPresent()) {
            ListaDeTareas lista = opLista.get();
            for (int i = 0; i < lista.nroTareas(); i++) {
                try {
                    out.add("[" + lista.getTarea(i).getId() + "]" + lista.getTarea(i).getNombre());
                } catch (KanbanException e) {
                    //no se debería dar la excepción a menos que exista un error de programación
                    throw new RuntimeException(e);
                }
            }
        }
        return out.toArray(new String[out.size()]);
    }

    public boolean leer() {
        try {
            File file = new File("src/ejemplos/mvcconpresistencia/kanban/data/data.kan");
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fos);
            //leer la lista
            listasDeListas = (List<ListaDeTareas>) ois.readObject();
            //cerrar el archivo
            ois.close();
            return true;

        } catch (FileNotFoundException e) {
           return false;
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }

    }

    public void guardar() {
        try {
            File file = new File("src/ejemplos/mvcconpresistencia/kanban/data/data.kan");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //guardar la lista
            oos.writeObject(listasDeListas);
            //cerrar el archivo
            oos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
