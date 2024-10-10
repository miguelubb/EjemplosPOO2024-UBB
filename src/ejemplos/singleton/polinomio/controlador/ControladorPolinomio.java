package ejemplos.singleton.polinomio.controlador;

import ejemplos.singleton.polinomio.modelo.Polinomio;

public class ControladorPolinomio {
    private static ControladorPolinomio instance=null;
    private ControladorPolinomio() {

    }
    public static ControladorPolinomio getInstance() {
        if (instance == null) {
            instance = new ControladorPolinomio();
        }
        return instance;
    }

    public String suma(double[] p1, double[] p2) {
        Polinomio a=new Polinomio(p1);
        Polinomio b=new Polinomio(p2);
        return a.suma(b).toString();
    }
}
