package ejemplos.recursividad;

public class IntroduccionRecursividad {
    public static void main(String[] args) {
        System.out.println("factorial de 5= "+fact(5));
        System.out.print("primeros 10 números de fibonacci: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(fib(i)+" ");
        }
        System.out.println();
        for (int i = 10; i < 40; i+=10) {
            System.out.println(i+") "+fib(i)+" ");

        }
        System.out.println();
        System.out.println("2 elevado a 10: (lento)"+potencia(2,10));
        System.out.println("2 elevado a 10 (rápido)"+potenciaRapida(2,10));

        long startTime = System.nanoTime();
        double a=potencia(2, 1000);
        long elapsedNanos = System.nanoTime() - startTime;
        System.out.println("Tiempo transcurrido en calcuar 2^1000= "+ a +" Lento="+elapsedNanos/1000+" mu.");

        long startTime3 = System.nanoTime();
        double c=Math.pow(2, 1000);
        long elapsedNanos3 = System.nanoTime() - startTime3;
        System.out.println("Tiempo transcurrido en calcuar 2^1000= "+ c +" Math.pow()="+elapsedNanos3/1000+" mu.");

        long startTime2 = System.nanoTime();
        double b=potenciaRapida(2, 1000);
        long elapsedNanos2 = System.nanoTime() - startTime2;
        System.out.println("Tiempo transcurrido en calcuar 2^1000= "+ b +" Rapido="+elapsedNanos2/1000+" mu.");


    }
    //Estrategia achica y vencerás
    public static int fact(int n) {
        if (n == 0){
            return 1;
        }else{
            return n * fact(n-1);
        }
    }

    //pre: n>=0
    public static long fib(int n) {
        if (n <= 1){
            return n;
        }else{
            return fib(n-1) + fib(n-2);
        }
    }

    //pre: divisor es distinto de 0
    public static int division(int dividendo, int divisor){
        if(dividendo < divisor){
            return 0;
        }else{
            return 1+division(dividendo-divisor,divisor);
        }
    }

    public static double potencia(int base, int exp){
        if(exp==0){
            return 1;
        }else if(exp==1){
            return base;
        }else{
            return base*potencia(base,exp-1);
        }
    }

    //estrategia dividir para reinar
    public static double potenciaRapida(int base, int exp){
        if(exp==0){
            return 1;
        }else if(exp==1){
            return base;
        }else if(exp%2==0){
            //si es par
            double semiPotencia=potenciaRapida(base,exp/2);
            return semiPotencia*semiPotencia;
        }else{
            //si exp es impar
            double semiPotencia=potenciaRapida(base,(exp-1)/2);
            return base*semiPotencia*semiPotencia;
        }
    }
}
