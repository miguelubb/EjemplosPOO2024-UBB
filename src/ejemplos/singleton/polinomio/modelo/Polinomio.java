package ejemplos.singleton.polinomio.modelo;

import java.util.*;

public class Polinomio {
    private double[] coeficiente;
    public Polinomio(int grado) {
        coeficiente = new double[grado+1];
    }

    public Polinomio(double[] coeficiente) {
        this.coeficiente = coeficiente;
    }

    public int grado(){
        return coeficiente.length-1;
    }

    public double getCoeficiente(int exponente){
        return exponente<=grado()?coeficiente[exponente]:0;
    }

    public Polinomio suma(Polinomio p) {
        int grado=Math.max(p.grado(), this.grado());
        Polinomio out=new Polinomio(grado);
        for (int i = 0; i < out.coeficiente.length; i++) {
            out.coeficiente[i]=this.getCoeficiente(i)+p.getCoeficiente(i);
        }
        return out;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = grado(); i >=0; i--) {
            sb.append(String.format("%+.2fX^%d ", this.getCoeficiente(i), i));
        }
        return sb.toString();
    }
}
