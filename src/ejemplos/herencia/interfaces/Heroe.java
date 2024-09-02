package ejemplos.herencia.interfaces;

public class Heroe implements Personaje, Comparable<Heroe> {
    private String nombre;
    private int vida;

    public Heroe(String nombre, int vida) {
        this.nombre = nombre;
        this.vida = vida;
    }

    @Override
    public int disparar() {
        return 0;
    }

    @Override
    public void mover() {

    }

    @Override
    public void recibirDisparo(int disparo) {

    }

    @Override
    public int compareTo(Heroe o) {
        return this.vida - o.vida;
    }
}
