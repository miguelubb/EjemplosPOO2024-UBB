package asociacion.AutorLibro;

import java.util.*;

public class Libro {
    private String titulo;
    private int yearPublicacion;

    //variable de instancia que implementa la asociación con Autor
    private List<Autor> autores;


    public Libro(String titulo, int yearPublicacion) {
        this.titulo = titulo;
        this.yearPublicacion = yearPublicacion;
        //crea una lista vacía y la guarda en autores
        this.autores = new ArrayList<>();

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getYearPublicacion() {
        return yearPublicacion;
    }

    public void setYearPublicacion(int yearPublicacion) {
        this.yearPublicacion = yearPublicacion;
    }

    @Override
    public String toString() {
        return  titulo;
    }

    public boolean agregaAutor(Autor autor) {
        if(autores.contains(autor)) {
            return false;
        }
        return autores.add(autor);
    }

    public boolean eliminaAutor(Autor autor) {
        return autores.remove(autor);
    }

    public String[] obtieneNombreAutores(){
        String[] out=new String[autores.size()];
        for(int i=0; i< out.length; i++){
            Autor a=autores.get(i);
            out[i]=a.getNombre();
        }
        return out;
    }
}
