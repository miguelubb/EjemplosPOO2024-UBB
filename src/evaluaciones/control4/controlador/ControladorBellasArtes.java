package evaluaciones.control4.controlador;

import evaluaciones.control4.modelo.Autor;
import evaluaciones.control4.modelo.EstiloPintura;
import evaluaciones.control4.modelo.Pintura;
import evaluaciones.control4.modelo.TecnicaPintura;
import evaluaciones.control4.solucion.ConsultasStreaming;
import evaluaciones.control4.solucion.IOSistemaAutores;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControladorBellasArtes {
    private static ControladorBellasArtes controladorSistema;
    List<Autor> autores;
    List<Pintura> pinturas;

    private ControladorBellasArtes() {
        autores = new ArrayList<>();
        pinturas = new ArrayList<>();
    }

    public static ControladorBellasArtes getInstancia() {
        if (controladorSistema == null) {
            controladorSistema = new ControladorBellasArtes();
        }
        return controladorSistema;
    }

    public void exportar() throws FileNotFoundException {
        IOSistemaAutores.getInstance().exportarAutores( "src/evaluaciones/control4/autores.txt", autores);
        IOSistemaAutores.getInstance().exportarPinturas( "src/evaluaciones/control4/pinturas.txt", pinturas);
    }

    public void importar() throws FileNotFoundException {
        autores = IOSistemaAutores.getInstance().importarAutores("src/evaluaciones/control4/autores.txt");
        pinturas = IOSistemaAutores.getInstance().importarPinturas("src/evaluaciones/control4/pinturas.txt", autores);
    }

    public void GenerarAutoresYPinturas() {
        autores.add(new Autor(1, "Claude Monet", "Francia", "1840-11-14",
                List.of("Legión de Honor", "Premio de Arte Francés")));
        autores.add(new Autor(2, "Vincent van Gogh", "Países Bajos", "1853-03-30",
                List.of()));
        autores.add(new Autor(3, "Pablo Picasso", "España", "1881-10-25",
                List.of("Legión de Honor", "Premio Lenin de la Paz")));
        autores.add(new Autor(4, "Salvador Dalí", "España", "1904-05-11",
                List.of("Gran Cruz de Isabel la Católica")));
        autores.add(new Autor(5, "Frida Kahlo", "México", "1907-07-06",
                List.of()));
        autores.add(new Autor(6, "Rembrandt", "Países Bajos", "1606-07-15",
                List.of("Reconocimiento póstumo")));
        autores.add(new Autor(7, "Leonardo da Vinci", "Italia", "1452-04-15",
                List.of("Genio universal")));
        autores.add(new Autor(8, "Diego Velázquez", "España", "1599-06-06",
                List.of("Reconocimiento por la corte española")));
        autores.add(new Autor(9, "Johannes Vermeer", "Países Bajos", "1632-10-31",
                List.of()));
        autores.add(new Autor(10, "Paul Cézanne", "Francia", "1839-01-19",
                List.of("Reconocimiento póstumo")));

        pinturas.add(new Pintura("Impresión: Sol naciente", EstiloPintura.IMPRESIONISMO, TecnicaPintura.OLEO, autores.get(0), 1872));
        pinturas.add(new Pintura("El estanque de los nenúfares", EstiloPintura.IMPRESIONISMO, TecnicaPintura.OLEO, autores.get(0), 1899));

        pinturas.add(new Pintura("La noche estrellada", EstiloPintura.POSTIMPRESIONISMO, TecnicaPintura.OLEO, autores.get(1), 1889));
        pinturas.add(new Pintura("Los girasoles", EstiloPintura.POSTIMPRESIONISMO, TecnicaPintura.OLEO, autores.get(1), 1888));

        pinturas.add(new Pintura("Las señoritas de Avignon", EstiloPintura.CUBISMO, TecnicaPintura.OLEO, autores.get(2), 1907));
        pinturas.add(new Pintura("El Guernica", EstiloPintura.CUBISMO, TecnicaPintura.OLEO, autores.get(2), 1937));

        pinturas.add(new Pintura("La solucion de la memoria", EstiloPintura.SURREALISMO, TecnicaPintura.OLEO, autores.get(3), 1931));
        pinturas.add(new Pintura("Sueño causado por el vuelo de una abeja", EstiloPintura.SURREALISMO, TecnicaPintura.OLEO, autores.get(3), 1944));

        pinturas.add(new Pintura("Las dos Fridas", EstiloPintura.SURREALISMO, TecnicaPintura.ACUARELA, autores.get(4), 1939));
        pinturas.add(new Pintura( "Autorretrato con collar de espinas", EstiloPintura.SURREALISMO, TecnicaPintura.OLEO, autores.get(4), 1940));

        pinturas.add(new Pintura( "La ronda de noche", EstiloPintura.BARROCO, TecnicaPintura.OLEO, autores.get(5), 1642));
        pinturas.add(new Pintura( "Autorretrato con dos círculos", EstiloPintura.BARROCO, TecnicaPintura.OLEO, autores.get(5), 1665));

        pinturas.add(new Pintura( "La última cena", EstiloPintura.RENACIMIENTO, TecnicaPintura.TEMPLE, autores.get(6), 1498));
        pinturas.add(new Pintura( "Mona Lisa", EstiloPintura.RENACIMIENTO, TecnicaPintura.OLEO, autores.get(6), 1503));

        pinturas.add(new Pintura( "Las meninas", EstiloPintura.BARROCO, TecnicaPintura.OLEO, autores.get(7), 1656));
        pinturas.add(new Pintura( "La rendición de Breda", EstiloPintura.BARROCO, TecnicaPintura.OLEO, autores.get(7), 1635));

        pinturas.add(new Pintura( "La joven de la perla", EstiloPintura.BARROCO, TecnicaPintura.OLEO, autores.get(8), 1665));
        pinturas.add(new Pintura( "Vista de Delft", EstiloPintura.BARROCO, TecnicaPintura.OLEO, autores.get(8), 1660));

        pinturas.add(new Pintura( "La montaña Sainte-Victoire", EstiloPintura.POSTIMPRESIONISMO, TecnicaPintura.OLEO, autores.get(9), 1904));
        pinturas.add(new Pintura( "Bodegón con manzanas y naranjas", EstiloPintura.POSTIMPRESIONISMO, TecnicaPintura.OLEO, autores.get(9), 1899));
    }

    public String[][] listarAutores() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String[][] data = new String[autores.size()][6];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = String.format("%,d", autores.get(i).getId());
            data[i][1] = autores.get(i).getNombre();
            data[i][2] = autores.get(i).getPais();
            data[i][3] = autores.get(i).getFechaNacimiento().format(formatter);
            data[i][4] = String.format("%,d", autores.get(i).getCantPremios());
            data[i][5] = String.format("%,d", autores.get(i).getCantPinturas());
        }
        return data;
    }

    public String[][] listarPinturas() {
        String[][] data = new String[pinturas.size()][5];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = pinturas.get(i).getTitulo();
            data[i][1] = pinturas.get(i).getEstilo().name();
            data[i][2] = pinturas.get(i).getTecnica().name();
            data[i][3] = pinturas.get(i).getAutor().getNombre();
            data[i][4] = String.format("%,d", pinturas.get(i).getAnioCreacion());
        }
        return data;
    }

    public String[][] listarAutoresPorNacionalidad(String nacionalidad) {
        return ConsultasStreaming.listarAutoresPorPais(autores, nacionalidad);
    }

    public String[][] listarAutoresPorEstilo(String estilo) {
        return ConsultasStreaming.listarAutoresPorEstilo(pinturas, estilo);
    }


    public String[][] listarPinturasAutor(int id) {
        return ConsultasStreaming.listarPinturasAutor(pinturas, id);

    }

    public String[][] listarPinturasEnRango(int ini, int fin) {
        return ConsultasStreaming.listarPinturasEnRango(pinturas, ini, fin);
    }
}



