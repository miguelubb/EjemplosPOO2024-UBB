package ejemplos.basicos.persona;

import java.time.LocalDate;

public class TestPersona {
    public static void main(String[] args) {
        System.out.println("Ejemplo de clase persona");
        Persona[] personas = new Persona[10];

        //itar:itera arreglo
        for (int i = 0; i < personas.length; i++) {
            personas[i]=new Persona(i+1);
            personas[i].setNombre("Nombre"+i);
            LocalDate hoy=LocalDate.now();
            LocalDate nacim=hoy.minusYears(18+i);
            personas[i].setFechaNacimiento(nacim);
        }

        System.out.printf("%4s %-10s %10s %4s %n","ID","Nombre","Nacimiento", "Edad");
        //iter: for-each
        for (Persona p : personas) {
            System.out.printf("%4d %-10s %10s %4s %n",p.getId(),p.getNombre(),p.getFechaNacimiento(),p.edad());

        }




    }
}
