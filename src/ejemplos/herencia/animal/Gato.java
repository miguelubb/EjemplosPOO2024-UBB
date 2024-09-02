package ejemplos.herencia.animal;

public class Gato extends Animal {
    public Gato(String nombre) {
        super(nombre);
    }

    @Override
    public String saludar() {
        return getNombre()+": Miau, miau!!!";
    }
}
