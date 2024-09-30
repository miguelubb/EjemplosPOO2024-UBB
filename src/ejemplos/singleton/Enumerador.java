package ejemplos.singleton;

import java.util.*;

public class Enumerador {
    //implementación del patron singleton
    private static Enumerador instance=null;
    public static Enumerador getInstance() {
        if (instance==null){
            instance=new Enumerador();
        }
        return instance;
    }

    //implementación propia de la clase
    List<Integer> series;
    private Enumerador() { //¡notar que el constructor es privado!
        series = new ArrayList<Integer>();
    }
    //siguiente número en la serie pasado por parámetro
    public int nextValue(int serie){
        int actual=series.remove(serie);
        int next=actual+1;
        series.add(serie,next);
        return next;
    }
    //agrega una nueva serie, entrega el código de la serie como respuesta
    public int addSerie(){
        series.add(0);
        return series.size()-1;
    }
}
