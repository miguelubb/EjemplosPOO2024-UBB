package evaluaciones.control2;

import java.util.*;

public class Curso extends Formacion{
    private int costo;
    List<Diplomado> diplomados;
    public Curso(int codigo, String nombre, int costo) {
        super(codigo, nombre);
        diplomados = new ArrayList<>();
        this.costo = costo;
    }

    @Override
    public int getCosto() {
        return costo;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    public boolean addDiplomado(Diplomado d) {
        if(diplomados.contains(d)) {
            return false;
        }
        return diplomados.add(d);
    }
}
