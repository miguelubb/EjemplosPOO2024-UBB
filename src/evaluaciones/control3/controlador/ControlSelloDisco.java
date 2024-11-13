package evaluaciones.control3.controlador;

import evaluaciones.control3.excepciones.SelloDiscoException;
import evaluaciones.control3.modelo.*;

import java.time.LocalDate;
import java.util.*;

public class ControlSelloDisco {
    private List<Album> albunes = new ArrayList<>();
    private List<Cancion> canciones = new ArrayList<>();
    private static final ControlSelloDisco instance = new ControlSelloDisco();

    private ControlSelloDisco() {

    }

    public static ControlSelloDisco getInstance() {
        return instance;
    }

    public void creaAlbum(String nombre, LocalDate fechaCreacion) throws SelloDiscoException {
        Album album = new Album(nombre, fechaCreacion);
        if (!albunes.contains(album)) {
            albunes.add(album);
        } else {
            throw new SelloDiscoException("El album ya existe");
        }
    }

    public void creaCancion(String nombre, float duracion, String gen, String nombreInterprete) throws SelloDiscoException {
        try {
            Genero genero = Genero.valueOf(gen);
            Cancion cancion = new Cancion(nombre, duracion, genero, nombreInterprete);
            if (!canciones.contains(cancion)) {
                canciones.add(cancion);
            } else {
                throw new SelloDiscoException("La canción ya existe");
            }
        } catch (IllegalArgumentException e) {
            throw new SelloDiscoException("El género:'" + gen + "', no existe");
        }

    }

    public void agregaCancionEnAlbum(String nombreCancion, String nombreAlbum) throws SelloDiscoException {
        Optional<Cancion> cancion = buscarCancion(nombreCancion);
        Optional<Album> album = buscarAlbum(nombreAlbum);
        if (cancion.isPresent() && album.isPresent()) {
            album.get().addCancion(cancion.get());
            cancion.get().addAlbum(album.get());
        } else {
            throw new SelloDiscoException("No existe la canción o el album");
        }

    }

    private Optional<Album> buscarAlbum(String nombreAlbum) {
        for (Album album : albunes) {
            if (album.getNombre().equals(nombreAlbum)) {
                return Optional.of(album);
            }
        }
        return Optional.empty();
    }

    private Optional<Cancion> buscarCancion(String nombreCancion) {
        for (Cancion cancion : canciones) {
            if (cancion.getNombre().equals(nombreCancion)) {
                return Optional.of(cancion);
            }
        }
        return Optional.empty();
    }

    public void eliminaCancionDeAlbum(String nombreCancion, String nombreAlbum) throws SelloDiscoException {
        Optional<Cancion> cancion = buscarCancion(nombreCancion);
        Optional<Album> album = buscarAlbum(nombreAlbum);
        if (cancion.isPresent() && album.isPresent()) {
            album.get().removeCancion(cancion.get());
            cancion.get().removeAlbum(album.get());
        } else {
            throw new SelloDiscoException("No existe la cancion o el album");
        }
    }

    public String[] listaAlbunes() {
        String[] out = new String[albunes.size()];
        int i = 0;
        for (Album a : albunes) {
            out[i++] = a.toString() + ";" + a.getDuracion() + ";" + a.getCanciones().length;
        }
        return out;
    }

    public String[] listaCancionesDeGenero(String gen) throws SelloDiscoException {
        try {
            Genero genero = Genero.valueOf(gen);
            List<String> out = new ArrayList<>();
            for (Cancion c : canciones) {
                if (c.getGenero().equals(genero)) {
                    out.add(c.toString() + ";" + c.getAlbums().length);
                }
            }
            return out.toArray(new String[out.size()]);
        } catch (IllegalArgumentException e) {
            throw new SelloDiscoException("El género:'" + gen + "', no existe");
        }
    }

    public String[] listaCancionesDeAlbum(String nombreAlbum) throws SelloDiscoException {
        Cancion[] canciones;
        Optional<Album> album = buscarAlbum(nombreAlbum);
        if (album.isPresent()) {
            canciones = album.get().getCanciones();
            if (canciones.length > 0) {
                String[] out = new String[canciones.length];
                int i = 0;
                for (Cancion c : canciones) {
                    out[i++] = c.toString();
                }
            } else {
                throw new SelloDiscoException("El album no tiene canciones");
            }
        }
        throw new SelloDiscoException("No existe la cancion o el album");
    }

}
