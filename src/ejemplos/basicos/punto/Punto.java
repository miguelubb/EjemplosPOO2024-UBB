package ejemplos.basicos.punto;

import static java.lang.Math.*;

public class Punto {
    //atributos
    int x;
    int y;

    //métodos
    //constructores
    //inicializar el objeto construido
    public Punto(){
        x=0;
        y=0;
    }
    public Punto(int x, int y){
        this.x=x;
        this.y=y;
    }

   public void mover(int x, int y){
        this.x=x;
        this.y=y;
   }

   public int distanciaAlOrigen(){
        return(int)(sqrt(x*x+y*y));
   }

   public int distanciaA(Punto b){
        return(int)(sqrt(pow(b.x-this.x,2)+pow(b.y-this.y,2)));
    }

   public String toString(){
        return "("+x+", "+y+")";
   }

}
