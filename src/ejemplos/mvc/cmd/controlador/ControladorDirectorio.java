package ejemplos.mvc.cmd.controlador;

import ejemplos.mvc.cmd.modelo.*;

public class ControladorDirectorio {
    //implementación de singleton
    private static final ControladorDirectorio instancia = new ControladorDirectorio();

    public static ControladorDirectorio getInstancia() {
        return instancia;
    }

    private Carpeta raiz;
    private Carpeta actual;

    private ControladorDirectorio() {
        raiz = new Carpeta("/", 1000);
        actual = raiz;
    }

    public String pwd() {
        return actual.getRuta();
    }

    public boolean cd(String ruta) {
        Carpeta busca = buscarCarpeta(ruta);
        if (busca == null) return false;
        actual = busca;
        return true;
    }

    public String[][] dir(String ruta) {
        Carpeta busca = buscarCarpeta(ruta);
        if (busca == null) return null;
        String[][] out = new String[busca.getNroElementos() + 3][3];
        int i = 0;
        if (busca != raiz) {
            out = new String[busca.getNroElementos() + 5][3];
            out[i][0] = busca.getTipo();
            out[i][1] = "" + busca.getTam();
            out[i++][2] = ".";
            out[i][0] = busca.getPadre().getTipo();
            out[i][1] = "" + busca.getPadre().getTam();
            out[i++][2] = "..";
        }
        int contDir = 0, contFile = 0, sumTam = 0;
        for (int j = 0; j < busca.getNroElementos(); j++, i++) {
            Elemento e = busca.getElemento(j);
            out[i][1] = e.getTipo();
            out[i][0] = "" + e.getTam();
            out[i][2] = e.getNombre();
            if (e instanceof Carpeta) {
                contDir++;
            } else {
                contFile++;
            }
            sumTam += e.getTam();
        }
        out[i][0] = "";
        out[i][1] = "" + contDir;
        out[i++][2] = "Directorios";

        out[i][0] = "";
        out[i][1] = "" + contFile;
        out[i++][2] = "Archivos";

        out[i][0] = "";
        out[i][1] = "" + sumTam;
        out[i++][2] = "bytes ocupados";
        return out;
    }

    private Carpeta buscarCarpeta(String ruta) {
        //busca el nodo donde comienza la búsqueda
        Carpeta busca = switch (ruta) {
            case "/" -> raiz;
            case ".." -> actual.getPadre();
            default -> actual;
        };

        if (ruta.equals("..")||ruta.equals("/")) {
            return busca;
        }

        String[] dirName = ruta.split("/");
        int i = 0;

        while (i < dirName.length && busca != null) {
            busca = busca.getCarpeta(dirName[i++]);
        }
        if (busca == null) {
            return null;
        }

        return busca;
    }


    public boolean md(String ruta) {
        //EL DIRECTORIO NO DEBE EXISTIR
        Carpeta busca = buscarCarpeta(ruta);
        if (busca != null) return false;
        //se separan la ruta del padre y el nombre del nuevo directorio
        String[] dirName = ruta.split("/");
        String nuevo = dirName[dirName.length - 1];
        int pos = ruta.lastIndexOf("/");
        if (pos == -1) {
            busca = actual;
        } else {
            //ruta="mis_documentos/hola/que_tal
            //rutaPadre="mis_documentos/hola"
            //nuevo="que_tal"
            busca = buscarCarpeta(ruta.substring(0, pos));
        }
        //ahora debería econtrar el directorio
        if (busca == null) return false;
        busca.addElemento(new Carpeta(nuevo, 1000));
        return true;
    }

    public boolean ma(String ruta,long tam){
        Carpeta busca = buscarCarpeta(ruta);
        if (busca != null) return false;
        //se separan la ruta del padre y el nombre del nuevo directorio
        String[] dirName = ruta.split("/");
        String nuevo = dirName[dirName.length - 1];
        int pos = ruta.lastIndexOf("/");
        if (pos == -1) {
            busca = actual;
        } else {
            //ruta="mis_documentos/hola/que_tal
            //rutaPadre="mis_documentos/hola"
            //nuevo="que_tal"
            busca = buscarCarpeta(ruta.substring(0, pos));
        }
        //ahora debería econtrar el directorio
        if (busca == null) return false;
        busca.addElemento(new Archivo(nuevo, tam));
        return true;
    }
}
