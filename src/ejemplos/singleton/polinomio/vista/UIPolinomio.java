package ejemplos.singleton.polinomio.vista;

import ejemplos.singleton.polinomio.controlador.*;

public class UIPolinomio {
    public static void main(String[] args) {
        double[] p1={5.0, 2.0, 3.0};
        double[] p2={3.0, 2.0, 2.0};
        String suma= ControladorPolinomio
                .getInstance()
                .suma(p1,p2);
        System.out.println(suma);
    }
}
