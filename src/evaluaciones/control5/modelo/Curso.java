package evaluaciones.control5.modelo;

import java.util.ArrayList;

public class Curso extends Formacion {
    private int costo;

    private final ArrayList<Diplomado> diplomados;

    public Curso(int codigo, String nombre, Modalidad modalidad, int costo) {
        super(codigo, nombre, modalidad);
        this.costo = costo;
        diplomados = new ArrayList<>();
    }

    @Override
    public int getCosto() {
        return costo;
    }

    public void addDiplomado(Diplomado diplomado) {
        if (!diplomados.contains(diplomado)) {
            diplomados.add(diplomado);
        }
    }
}
