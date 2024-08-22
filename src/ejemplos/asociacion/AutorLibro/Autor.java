package ejemplos.asociacion.AutorLibro;

import java.util.*;

public class Autor {
    private String nombre;
    private String email;

    //variable de instancia para implementar la asociación con Libro
    private List<Libro> libros;

    public Autor(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        libros=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nombre, autor.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    public boolean agregaLibro(Libro libro) {
        if(libros.contains(libro)){
            return false;
        }
        return libros.add(libro);
    }
    public boolean eliminaLibro(Libro libro) {
        return libros.remove(libro);
    }
    public String[] obtieneTituloLibros(){
        String[] out=new String[libros.size()];
        for (int i = 0; i < out.length; i++) {
            out[i]=libros.get(i).getTitulo();
        }
        return out;
    }


}
