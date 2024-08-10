package ejemplos.basicos.punto;

public class TestPunto {
    public static void main(String[] args) {
        Punto a=new Punto();
        Punto b=new Punto(3,4);
        System.out.println("a="+a);
        System.out.println("b="+b);
        System.out.println("Distancia al origen");
        System.out.println("a="+a.distanciaAlOrigen());
        System.out.println("b="+b.distanciaAlOrigen());
        a.mover(3,4);
        a.x=3;
        a.y=4;
        System.out.println("a="+b.distanciaAlOrigen());
        System.out.println("distancia entre "+a+ " y "+b);
        //modo estructurado=sumar(a,b) Modo Objeto= a.sumar(b)

        //p.sumar(q) //la llamada

        //parámetros reales son a y b.
        //parámetros formales (define el método)
        //   copia==>(this = p), (b = q)
        // public ejemplos.basicos.punto.Punto suma(ejemplos.basicos.punto.Punto b){
        //   return new ejemplos.basicos.punto.Punto(this.x+b.x,this.y+b.y);
        // }
        //

        System.out.println(a.distanciaA(b));
    }
}
