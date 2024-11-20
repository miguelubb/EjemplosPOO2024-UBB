package evaluaciones.control4.modelo;

public class Pintura {
    private String titulo;
    private EstiloPintura estilo; // Enumeración para los estilos
    private TecnicaPintura tecnica; // Enumeración para las técnicas
    private Autor autor; // Asociado al autor por su ID
    private int anioCreacion;

    // Constructor
    public Pintura(){

    }
    public Pintura(String titulo, EstiloPintura estilo, TecnicaPintura tecnica, Autor autor, int anioCreacion) {
        this.titulo = titulo;
        this.estilo = estilo;
        this.tecnica = tecnica;
        this.autor = autor;
        autor.addPintura(this);
        this.anioCreacion = anioCreacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public EstiloPintura getEstilo() {
        return estilo;
    }

    public void setEstilo(EstiloPintura estilo) {
        this.estilo = estilo;
    }

    public TecnicaPintura getTecnica() {
        return tecnica;
    }

    public void setTecnica(TecnicaPintura tecnica) {
        this.tecnica = tecnica;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

}
