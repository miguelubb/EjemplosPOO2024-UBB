package ejemplos.mvc.cmd.vista;

import ejemplos.mvc.cmd.controlador.ControladorDirectorio;

import java.util.*;

public class Cmd {
    private ControladorDirectorio cmd;
    private Scanner sc;

    public static void main(String[] args) {
        new Cmd().run();
    }

    public Cmd() {
        sc = new Scanner(System.in);
        cmd=ControladorDirectorio.getInstancia();
    }
    public void run(){
        System.out.println("Simulador de CMD");
        System.out.println("Curso Programación orientada a objetos, Universidad del Bío-Bío");
        System.out.println("[profesor: Miguel Romero Vásquez]");
        String[] prompt;
        do{
            System.out.print(cmd.pwd()+">");
            prompt=sc.nextLine().split(" ");
            String comando=prompt[0];
            switch (comando.toUpperCase()){
                case "PWD" -> cmd.pwd();
                case "CD" -> cd(prompt);
                case "DIR" -> dir(prompt);
                case "MD"  -> md(prompt);
                case "MA"  -> ma(prompt);
                case "EXIT" -> System.out.println("Adios...");
                default -> System.out.println("\""+ comando + "\" no se reconoce como un comando interno o externo,\n" +
                        "programa o archivo por lotes ejecutable.");
            }
        }while(!prompt[0].toUpperCase().equals("EXIT"));

    }

    private void ma(String[] prompt) {
        try {
            if (prompt.length < 3 || !cmd.ma(prompt[1], Long.parseLong(prompt[2]))) {
                System.out.println("No se puede crear el archivo en la ruta especificada o falta el tamaño del archivo.");
            }
        } catch (RuntimeException e) {
            System.out.println("el tamaño del archivo debe ser un número");
        }
    }

    private void md(String[] prompt) {
        if(prompt.length<2 || !cmd.md(prompt[1])){
            System.out.println("No se puede crear el directorio en la ruta especificada.");
        }
    }

    private void cd(String[] prompt) {
        String ruta=prompt.length<2?".":prompt[1];
        if(!cmd.cd(ruta)){
            System.out.println("El sistema no puede encontrar la ruta especificada");
        }
    }

    private void dir(String[] prompt) {
        String ruta=prompt.length<2?".":prompt[1];
        String[][] out=cmd.dir(ruta);
        System.out.println("   Directorio de: "+ ruta);
        System.out.printf("%10s %10s %20s %n","tipo", "tamaño", "nombre");
        if(out==null){
            System.out.println("\n Directorio no existe.");
            return;
        }
        for (int i = 0; i < out.length; i++) {
            System.out.printf("%10s %10s %20s %n",out[i][0], out[i][1], out[i][2]);
        }
    }
}
