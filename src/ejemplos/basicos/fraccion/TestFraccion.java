package ejemplos.basicos.fraccion;

public class TestFraccion {
    public static void main(String[] args) {

        Fraccion a = new Fraccion(1, 4);
        Fraccion b = new Fraccion(2, 3);

        System.out.println(a + " + " + b + " = " + a.suma(b));
        System.out.println(a + " - " + b + " = " + a.resta(b));
        System.out.println(a + " * " + b + " = " + a.multiplicacion(b));
        System.out.println(a + " / " + b + " = " + a.division(b));
        System.out.println(a + " *" + "(" + a + " + " + b + ") = "
                + (a.multiplicacion(a.suma(b))));

        System.out.println();

        a = new Fraccion(1, 5);
        b = new Fraccion(2, 15);
        System.out.println(a + " + " + b + " = " + a.suma(b));
        System.out.println(a + " - " + b + " = " + a.resta(b));
        System.out.println(a + " * " + b + " = " + a.multiplicacion(b));
        System.out.println(a + " / " + b + " = " + a.division(b));

        System.out.println();

        a = new Fraccion(1, 55);
        b = new Fraccion(2, 89);
        System.out.println(a + " + " + b + " = " + a.suma(b));
        System.out.println(a + " - " + b + " = " + a.resta(b));
        System.out.println(a + " * " + b + " = " + a.multiplicacion(b));
        System.out.println(a + " / " + b + " = " + a.division(b));

    }
}
