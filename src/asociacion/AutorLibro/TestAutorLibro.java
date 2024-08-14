package asociacion.AutorLibro;

import java.util.*;

public class TestAutorLibro {
    public static void main(String[] args) {
        Autor marcelaPaz=new Autor("Marcela Paz", "marcela.paz@gmail.com");
        Libro papelucho=new Libro("Papelucho y mi hermana Ji", 1976);
        Libro papelucho2=new Libro("Papelucho Historiador", 1976);
        System.out.println("Libro: "+papelucho);
        System.out.println("Autora: "+marcelaPaz);
        marcelaPaz.agregaLibro(papelucho);
        papelucho.agregaAutor(marcelaPaz);
        marcelaPaz.agregaLibro(papelucho2);
        papelucho2.agregaAutor(marcelaPaz);

        System.out.println("Libros marcela Paz");
        System.out.println(Arrays.toString(marcelaPaz.obtieneTituloLibros()));
        System.out.println("autores del libro: "+papelucho2);
        System.out.println(Arrays.toString(papelucho2.obtieneNombreAutores()));

    }
}
