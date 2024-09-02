package ejemplos.herencia.animal;

public class Pato extends Animal{
    public Pato(String nombre) {
        super(nombre);
    }

    @Override
    public String saludar() {
        return getNombre()+": Cuac, cuac!!!";
    }

}
