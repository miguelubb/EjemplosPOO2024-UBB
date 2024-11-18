package ejemplos.gui.tabla;

public class ListaClientes {

    private static ListaClientes instance=new ListaClientes();
    private ListaClientes() {

    }
    public static ListaClientes getInstance() {
        return instance;
    }

    String[][] listadoClientes(){
        String[][] out={
                {"11.111.111-1","Berta Gajardo", "30"},
                {"22.222.222-2", "Carlos Carrasco","31"},
                {"33.333.333-3", "Alicia Aedo", "32"}
        };
        return out;
    }

    public double obtenerDeuda(String rut){
        return 120000;
    }
}
