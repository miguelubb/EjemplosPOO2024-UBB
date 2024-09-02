package ejemplos.herencia.animal;

import java.util.*;

public class PruebaHerenciaAnimal {
    public static void main(String[] args) {
        List<Animal> animales = new ArrayList<Animal>();
        animales.add(new Gato("Tom"));
        animales.add(new Gato("Gardfield"));
        animales.add(new Gato("Mary"));
        animales.add(new Gato("Negro"));
        animales.add(new Perro("Boby"));
        animales.add(new Perro("Chocolo"));
        animales.add(new Pato("Donal"));
        animales.add(new Pato("Lucas"));
        animales.add(new Pato("Banco Estado"));
        animales.add(new Pato("Malo"));

        for (Animal a : animales) {
            System.out.println(a.saludar());
        }



    }
}
