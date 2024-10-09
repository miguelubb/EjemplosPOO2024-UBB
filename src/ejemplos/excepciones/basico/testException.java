package ejemplos.excepciones.basico;

import java.util.*;

public class testException {
    private static final Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        //test();
        /*
        try {
            test2();
        } catch (MiException e) {
            System.err.println("Error: "+e.getMessage());
        }*/

        Optional<Integer> n=leerNumero();
        while(n.isEmpty()) {
            System.out.println("Ingrese un número válido");
            n=leerNumero();
        }
        System.out.println("ha ingresado: "+ n.get());
    }
    public static void test() {
        System.out.println("test: se lanza una excepcion de tipo RunTimeException");
        throw new MiRunTimeException("Ejemplo 1");
    }

    public static void test2() throws MiException {
        System.out.println("test: se lanza una excepcion de tipo Exception");
        throw new MiException("Ejemplo 2");
    }

    private static Optional<Integer> leerNumero(){
        try {
            Integer num = sc.nextInt();
            return Optional.of(num);
        }catch (InputMismatchException e){
            String next = sc.next();//importante limpiar el buffer del error!!!
            return Optional.empty();
        }
    }

}
