package evaluaciones.control4.solucion;

import evaluaciones.control4.modelo.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class IOSistemaAutores {
    //singleton
    private static IOSistemaAutores instance=new IOSistemaAutores();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private IOSistemaAutores(){}
    public static IOSistemaAutores getInstance(){
        return instance;
    }
    //El resultado de la importación se guarda en autores
    public List<Autor> importarAutores(String path) throws FileNotFoundException {
        List<Autor> autores=new ArrayList<>();
        Scanner sc=new Scanner(new  File(path));
        sc.useDelimiter("[;\\n\\r]+");
        while(sc.hasNext()){
            //"ID";"NOMBRE";"PAIS";"NACIMIENTO";"FALLECIMIENTO";"BIOGRAFÍA";["ninguno"|"PREMIOS"[, "PREMIOS"]*];
            Autor a=new Autor();
            a.setId(sc.nextInt());
            a.setNombre(sc.next());
            a.setPais(sc.next());
            a.setFechaNacimiento(sc.next());
            String premios=sc.next();
            if(premios.equalsIgnoreCase("ninguno")){
                a.setPremios(List.of());
            }else{
                String[] listaPremios=premios.split(",");
                a.setPremios(Arrays.asList(listaPremios));
            }
            autores.add(a);
        }
        sc.close();
        return autores;
    }
    //se requiere que previamente se lan los autores, los cuales son pasados como parámetro
    //esto es necesario para recrear el objeto pintura.
    //el resultado de la importacion se guarda en pinturas
    public List<Pintura> importarPinturas(String path, List<Autor> autores) throws FileNotFoundException {
        List<Pintura> pinturas=new ArrayList<>();
        Scanner sc=new Scanner(new  File(path));
        sc.useDelimiter("[;\\n\\r]+");
        while(sc.hasNext()){
            //"ID";"TÍTULO";"ESTILO";"TECNICA";"IDAUTOR";"AÑO";"DIMENSIONES";"LOCALIZACION"
            Pintura pintura=new Pintura();
            pintura.setTitulo(sc.next());
            pintura.setEstilo(EstiloPintura.valueOf(sc.next()));
            pintura.setTecnica(TecnicaPintura.valueOf(sc.next()));
            int idAutor=sc.nextInt();
            Optional<Autor> a=autores.stream().filter(autor->autor.getId()==idAutor).findFirst();
            if(a.isPresent()){
                pintura.setAutor(a.get());
                pintura.setAnioCreacion(sc.nextInt());
                pinturas.add(pintura);
            }//si no se encuentra el autor con idAutor, se ignora la pintura.
        }
        sc.close();
        return pinturas;
    }
    public void exportarAutores(String path, List<Autor> autores) throws FileNotFoundException {
        PrintWriter pw=new PrintWriter(path);
        for (Autor autor : autores) {
            //"ID";"NOMBRE";"PAIS";"NACIMIENTO";["ninguno"|"PREMIOS"[, "PREMIOS"]*];
            pw.print(autor.getId()+";");
            pw.print(autor.getNombre()+";");
            pw.print(autor.getPais()+";");
            pw.print(autor.getFechaNacimiento().format(formatter)+";");
            String premios=Arrays.toString(autor.getPremios().toArray(new String[0]));
            pw.print(autor.getCantPremios()==0?"ninguno":premios.substring(1,premios.length()-2)+";");
            pw.println();
        }
        pw.close();
    }
    public void exportarPinturas(String path, List<Pintura> pinturas) throws FileNotFoundException {
        PrintWriter pw=new PrintWriter(path);
        for (Pintura pintura : pinturas) {
            //"ID";"TÍTULO";"ESTILO";"TECNICA";"IDAUTOR";"AÑO";"DIMENSIONES";"LOCALIZACION"
            pw.print(pintura.getTitulo()+";");
            pw.print(pintura.getEstilo().name()+";");
            pw.print(pintura.getTecnica().name()+";");
            pw.print(pintura.getAutor().getId()+";");
            pw.print(pintura.getAnioCreacion()+";");
            pw.println();
        }
        pw.close();
    }
}
