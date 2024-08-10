package ejemplos.basicos.fraccion;

public class Fraccion {
    private int num, den;

    public Fraccion(int num, int den) {
        this.num = num;
        this.den = den;
    }

    public Fraccion() {
        num = 0;
        den = 1;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDen() {
        return den;
    }

    public void setDen(int den) {
        this.den = den;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Fraccion b = (Fraccion) obj;
        return num == b.num && den == b.den;
    }

    @Override
    public String toString() {
        double number = num * 1.0 / den;
        if (number == Double.NaN) {
            return "NaN";
        } else if (number == Double.NEGATIVE_INFINITY) {
            return "-Inf";
        } else if (number == Double.POSITIVE_INFINITY) {
            return "+Inf";
        }
        if (number < 0) {
            return String.format("-(%d/%d)", Math.abs(num), Math.abs(den));
        } else {
            return String.format("%d/%d", Math.abs(num), Math.abs(den));
        }
    }

    public Fraccion amplificar(int factor) {
        return new Fraccion(num * factor, den * factor);
    }

    public Fraccion simplificar(int factor) {
        if (num % factor != 0 || den % factor != 0) {
            return new Fraccion(0, 0);//error: NaN
        }
        return new Fraccion(num / factor, den / factor);
    }

    public Fraccion suma(Fraccion otra) {
        if (this.den == otra.den) {
            return new Fraccion(num + otra.num, den);
        } else {
            int mcd = maximoComunDivisor(this.den, otra.den);
            int denominador = (mcd > 1) ? mcd : this.den * otra.den;
            int a = denominador / this.den;
            int b = denominador / otra.den;
            return new Fraccion(num * a + otra.num * b, denominador);
        }
    }

    //algoritmo de euclides
    //ver: https://es.wikipedia.org/wiki/Algoritmo_de_Euclides
    private int maximoComunDivisor(int a, int b) {
        int r0 = Math.min(a, b), r1 = Math.max(a, b), aux;
        while (r1 != 0) {
            aux = r0 % r1;
            r0 = r1;
            r1 = aux;
        }
        return r0;
    }

    public Fraccion multiplicacion(Fraccion otra) {
        return new Fraccion(num * otra.num, den * otra.den);
    }

    public Fraccion inversoAditivo() {
        return new Fraccion(-num, den);
    }

    public Fraccion inversoMultiplicativo() {
        return new Fraccion(den, num);
    }

    public Fraccion resta(Fraccion otra) {
        return this.suma(otra.inversoAditivo());
    }

    public Fraccion division(Fraccion otra) {
        return this.multiplicacion(otra.inversoMultiplicativo());
    }

}
