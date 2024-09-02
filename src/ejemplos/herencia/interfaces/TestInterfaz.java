package ejemplos.herencia.interfaces;

import java.util.*;

public class TestInterfaz {
    public static void main(String[] args) {
        List<Personaje> personajes = new ArrayList<Personaje>();
        personajes.add(new Heroe("Super Man",10));
        personajes.add(new Heroe("Batman",10));
        personajes.get(0).disparar();

    }
}
