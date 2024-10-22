package ejemplos.mvc.kanban.vista;

import ejemplos.mvc.kanban.controlador.Controlador;
import ejemplos.mvc.kanban.excepciones.KanbanException;

import javax.naming.ldap.Control;
import java.time.LocalDateTime;
import java.util.*;
import java.time.*;
public class Menu {
    Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        new Menu().dislay();
    }
    public Menu(){
        init();
    }
    public void init(){
        Controlador.getInstance().agregarLista(1, "Todo", "Tareas por hacer");
        Controlador.getInstance().agregarLista(2, "Doing", "Tareas en ejecución");
        Controlador.getInstance().agregarLista(3, "Done", "Tareas terminadas");
        Controlador.getInstance().agregarLista(4, "BackLog", "Tareas no priorizadas para esta semana o sprint");
        LocalDateTime plazo= LocalDateTime.now().plusDays(5);
        Controlador.getInstance().crearTarea(1, 1, "Crear clase persona", "Crear clase persona", "Juan Segura", plazo);
        Controlador.getInstance().crearTarea(1, 2, "Crear clase producto", "Crear clase producto", "Juan Segura", plazo);
        Controlador.getInstance().crearTarea(1, 3, "Crear clase venta", "Crear clase venta", "Juan Segura", plazo);
    }
    public void dislay() {
        int op=1;
        do{
            System.out.println("\nMenu");
            System.out.println("1) Agregar Lista");
            System.out.println("2) Agregar tarea a lista");
            System.out.println("3) Mover tarea");
            System.out.println("4) Mostrar listas");
            System.out.println("5) Mostrar tareas");
            System.out.println("6) Salir");
            System.out.println("\nIngrese opción: ");
            op=sc.nextInt();
            switch(op){
                case 1 -> agregarLista();
                case 2 -> agregarTarea();
                case 3 -> moverTarea();
                case 4 -> mostrarListas();
                case 5 -> mostrarTareas();
                case 6 -> System.out.println("Adios...");
            }
        }while(op!=6);
    }

    private void mostrarTareas() {
        System.out.println("...::::: Mostrar TAREAS ::::....");
        System.out.println("Listas de tareas: ");
        String[] listas=Controlador.getInstance().getListas();
        for (String lista : listas) {
            System.out.print(lista+" ");
        }
        System.out.println();

        System.out.println("Indique el código de la lista a mostrar");
        int codigoLista=sc.nextInt();
        String[] tareas= Controlador.getInstance().getTareas(codigoLista);
        for (String tarea : tareas) {
            System.out.println(tarea);
        }
    }

    private void mostrarListas() {
        System.out.println("...::::: Mostrar Listas de tareas ::::....");
        System.out.println("Listas de tareas");
        String[] listas=Controlador.getInstance().getListas();
        for (String lista : listas) {
            System.out.println(lista);
        }
    }

    private void moverTarea() {
        System.out.println("...::::: Mover TAREA ::::....");

        System.out.println("Listas de tareas: ");
        String[] listas=Controlador.getInstance().getListas();
        for (String lista : listas) {
            System.out.print(lista+" ");
        }
        System.out.println();

        System.out.println("ingrese ID Lista de origen:");
        int idOrigen=sc.nextInt();
        System.out.println("Ingrese ID Lista de destino:");
        int idDestino=sc.nextInt();
        String[] tareas= Controlador.getInstance().getTareas(idOrigen);
        System.out.println("Tareas de la lista de origen:");
        for (String tarea : tareas) {
            System.out.println(tarea);
        }
        System.out.println("Ingrese ID tarea a mover:");
        int idTarea=sc.nextInt();
        try {
            Controlador.getInstance().moverTarea(idTarea,idOrigen,idDestino);
        } catch (KanbanException e) {
            System.out.println("*** ERROR: "+e.getMessage()+ " ***");
        }
    }

    private void agregarTarea() {
        System.out.println("...::::: Agregar TAREAS ::::....");
        System.out.println("Por implementar...");
    }

    private void agregarLista() {
        System.out.println("...::::: Agregar Lista de TAREAS ::::....");
        System.out.println("Por implementar...");
    }


}
