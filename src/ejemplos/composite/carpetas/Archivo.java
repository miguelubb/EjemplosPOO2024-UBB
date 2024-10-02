package ejemplos.composite.carpetas;

public class Archivo extends Elemento {

    private long tam;

    public Archivo(String nombre, long tam) {
        super(nombre);
        this.tam = tam;
    }

    public void setTam(long tam) {
        this.tam = tam;
    }

    @Override
    public long getTam() {
        return tam;
    }

    @Override
    public String getTipo() {
        return "Archivo";
    }

}
