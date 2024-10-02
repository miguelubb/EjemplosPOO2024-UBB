package ejemplos.composite.carpetas;

import java.util.*;

public abstract class Elemento {
    String nombre;
    Carpeta ubicacion=null;

    public Elemento(String nombre) {
        this.nombre = nombre;
    }

    public abstract long getTam();
    public abstract String getTipo();

    public void addElemento(Elemento elemento) { }
    public int getNroElementos() {
        return 0;
    }
    public boolean removeElemento(Elemento elemento) {
        return false;
    }

    public void moveCarpeta(Carpeta carpeta) {
        if(ubicacion!=null){
            //eliminar el archivo de la carpeta orginal
            ubicacion.removeElemento(this);
        }
        carpeta.addElemento(this);
        ubicacion = carpeta;
    }
    public String getRuta(){
        if(ubicacion==null){
            return nombre+"/";
        }
        return ubicacion.getRuta()+nombre+"/";
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Elemento elemento)) return false;
        return Objects.equals(nombre, elemento.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}
