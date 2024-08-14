package ejemplos.basicos.arrayList;
import java.util.*;
import ejemplos.basicos.persona.Persona;

public class TestArrayList {
    public static void main(String[] args) {
        //creación de un arrayList
        ArrayList<Persona> misContactos=new ArrayList<>();
        Persona a=new Persona(1);
        Persona b=new Persona(2);
        Persona c=new Persona(2);
        a.setNombre("Juan");
        b.setNombre("Ana");
        c.setNombre("Gabriel");
        misContactos.add(a);
        misContactos.add(0,b);
        misContactos.add(c);
        System.out.println(misContactos);
        misContactos.sort(Comparator.comparing(Persona::getNombre));
        System.out.println(misContactos);
        misContactos.sort(Comparator.comparing(Persona::getNombre).reversed());
        System.out.println(misContactos);
        Persona p=misContactos.remove(1);
        System.out.println(p.getNombre());
        for (Persona contacto : misContactos) {
            System.out.println(contacto.getNombre());
        }

        for (int i = 0; i < misContactos.size(); i++) {
            System.out.println((i+1)+" "+misContactos.get(i).getNombre());
        }



    }
}
