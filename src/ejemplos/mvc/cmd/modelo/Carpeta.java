package ejemplos.mvc.cmd.modelo;

import java.util.*;

public class Carpeta extends Elemento {
    private List<Elemento> elementos;
    private long tamBuff;

    public Carpeta(String nombre, long tamBuff) {
        super(nombre);
        this.tamBuff = tamBuff;
        elementos = new ArrayList<>();
    }

    @Override
    public long getTam() {
        long sum = 0;
        for (Elemento elemento : elementos) {
            sum += elemento.getTam();
        }
        return sum;
    }

    @Override
    public String getTipo() {
        return "Carpeta";
    }

    @Override
    public boolean removeElemento(Elemento elem) {
        elem.ubicacion = null;
        return elementos.remove(elem);
    }

    @Override
    public int getNroElementos() {
        return elementos.size();
    }

    @Override
    public void addElemento(Elemento elemento) {
        if (elementos.size() < tamBuff) {
            elementos.add(elemento);
            //mentener la bidireccionalidad
            elemento.ubicacion = this;
        }
    }

    public long getTamBuff() {
        return tamBuff;
    }

    public void setTamBuff(long tamBuff) {
        this.tamBuff = tamBuff;
    }

    //hay que crear más métodos para implementar CMD

    public Carpeta getCarpeta(String nombre) {
        switch (nombre) {
            case "." -> {
                return this;
            }
            case ".." -> {
                return this.getPadre();
            }
        }
        for (Elemento elemento : elementos) {
            if (elemento instanceof Carpeta && elemento.getNombre().equals(nombre)) {
                return (Carpeta) elemento;
            }
        }
        return null;
    }

    @Override
    public Elemento getElemento(int index) {
        return elementos.get(index);
    }
}
