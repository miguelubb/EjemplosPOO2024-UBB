package ejemplos.basicos.Enumeracion;

import java.util.Scanner;

public class DemoEstudiante {

    public static void main(String[] args) {
        /*
        La clase DemoEstudiante debe:
        Crear un objeto estudiante con rut “11.111.111-1”,
                nombre “Laura Diaz”, email “ldiaz@jmail.com”.
        Desplegar los datos del estudiante, incluyendo su estado.
        Preguntar al usuario un estado a asignarle al estudiante y proceder a asociarle dicho estado al objeto.
        Desplegar los datos del estudiante, incluyendo su estado.
         */
        Scanner sc=new Scanner(System.in);
        Estudiante laura=new Estudiante("11.111.111-1",
                "Laura Diaz");
        laura.setEmail("ldiaz@jmail.com");
        System.out.println(laura);
        System.out.println("Ingrese un estado para el estudiante:");
        String estado=sc.next();
        laura.setEstado(EstadoEstudiante.valueOf(estado));
        System.out.println(laura);

    }

}
