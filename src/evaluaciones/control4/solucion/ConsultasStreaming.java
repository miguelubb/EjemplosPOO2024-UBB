package evaluaciones.control4.solucion;

import evaluaciones.control4.modelo.*;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class ConsultasStreaming {


    public static String[][] listarAutoresPorPais(List<Autor> autores, String pais) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return autores.stream()
                .filter(a-> a.getPais().equals(pais))
                .map(a-> new String[]{String.format("%,d", a.getId()),
                        a.getNombre(), a.getPais(), a.getFechaNacimiento().format(formatter),
                        String.format("%,d",a.getCantPremios()), String.format("%,d", a.getCantPinturas())}
                ).toArray(String[][]::new);
    }

    public static String[][] listarAutoresPorEstilo(List<Pintura> pinturas, String estilo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return pinturas.stream()
                .filter(p-> p.getEstilo().name().equalsIgnoreCase(estilo))
                .map(p-> p.getAutor())
                .sorted(Comparator.comparing(Autor::getId))
                .distinct()
                .map(a-> new String[]{String.format("%,d", a.getId()),
                        a.getNombre(), a.getPais(), a.getFechaNacimiento().format(formatter),
                        String.format("%,d",a.getCantPremios()), String.format("%,d", a.getCantPinturas())}
                ).toArray(String[][]::new);
    }

    public static String[][] listarPinturasAutor(List<Pintura> pinturas, int id) {
        return pinturas.stream()
                .filter(pintura -> pintura.getAutor().getId()==id)
                //"ID", "TÍTULO", "ESTILO", "TECNICA", "AUTOR", "AÑO", "DIMENSIONES", "LOCALIZACION"};
                .map(p ->new String[]{p.getTitulo(), p.getEstilo().name(), p.getTecnica().name(),
                        p.getAutor().getNombre(), String.format("%,d",p.getAnioCreacion())} )
                .toArray(String[][]::new);

    }

    public static String[][] listarPinturasEnRango(List<Pintura> pinturas, int ini, int fin) {
        return pinturas.stream()
                .filter(pintura -> pintura.getAnioCreacion()>=ini && pintura.getAnioCreacion()<=fin )
                //"ID", "TÍTULO", "ESTILO", "TECNICA", "AUTOR", "AÑO", "DIMENSIONES", "LOCALIZACION"};
                .map(p ->new String[]{p.getTitulo(), p.getEstilo().name(), p.getTecnica().name(),
                        p.getAutor().getNombre(), String.format("%,d",p.getAnioCreacion())} )
                .toArray(String[][]::new);
    }
}
