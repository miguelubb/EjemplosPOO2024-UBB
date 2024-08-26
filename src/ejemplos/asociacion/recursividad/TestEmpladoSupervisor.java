package ejemplos.asociacion.recursividad;

public class TestEmpladoSupervisor {
    public static void main(String[] args) {
        Empleado rector=new Empleado("11.111.111-1", "Benito Humaña", "Rector");
        Empleado vrip=new Empleado("22.222.222-2", "María Angelica Caro", "Vicerectora de Investigación");
        Empleado dirInv=new Empleado("33.333.333-3", "Claudia Muñoz", "Directora de Investigación");
        Empleado subDirInv=new Empleado("33.333.333-3", "Hector Torrez", "Subdirectora de Investigación");
        Empleado dirPost=new Empleado("33.333.333-3", "Carolina Luengo", "Directora de Postgrado");
        Empleado subDirPost=new Empleado("33.333.333-3", "Guillermo Latorre", "Subdirectora de Postgrado");
        Empleado dirInnova=new Empleado("44.444.444-4", "Mario Nuñez", "Directora de Innovación");
        Empleado subDirInnova=new Empleado("55.555.555-5", "Guillermo Petzol", "Subdirector de Innovación");
        rector.addSupervisado(vrip);
        vrip.addSupervisado(dirInv);
        vrip.addSupervisado(dirPost);
        vrip.addSupervisado(dirInnova);
        subDirInv.setSupervisor(dirInv);
        subDirPost.setSupervisor(dirPost);
        subDirInnova.setSupervisor(dirInnova);
        System.out.println(rector.dependientes());
        System.out.println(rector.dependientesConNiveles());
    }
}
