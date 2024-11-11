package ejemplos.streaming.empleado;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        try (Scanner sc = new Scanner(new File("src/ejemplos/streaming/empleado/empleados.txt"))) {
            sc.useDelimiter(",|\r\n|[\n\r\u2028\u2029\u0085]");
            ArrayList<Empleado> empleados = new ArrayList<>();
            String rut;
            String nombre;
            long sueldo;
            String departamento;
            while (sc.hasNext()) {
                nombre = sc.next();
                sueldo = Long.parseLong(sc.next().trim());
                rut = sc.next();
                departamento = sc.next();
                empleados.add(new Empleado(rut, nombre, sueldo, departamento));
            }

            //reporte completo
            System.out.println("REPORTE COMPLETO (ordenado por departamento, seguido de nombre)");
            System.out.printf("%10S\t%-20S\t%-26S\t%12S %n", "RUT", "NOMBRE", "DEPARTAMENTO", "SUELDO");
            empleados.stream()
                    .sorted(Comparator.comparing(Empleado::getDepartamento).thenComparing(Empleado::getNombre))
                    .forEach(emp -> System.out.printf("%10s\t%-20s\t%-26s\t%,12d %n", emp.getRut(), emp.getNombre(), emp.getDepartamento(), emp.getSueldo()));

            //estadísticas con streaming y lamdas
            System.out.println("Cantidad total de empleados: " + (empleados.stream().count()));

            //empleado de menor sueldo
            Optional<Empleado> menorSueldo = empleados.stream().min(Comparator.comparing(Empleado::getSueldo));
            System.out.println("Menor sueldo: " + (menorSueldo.isPresent() ? menorSueldo.get() : "no hay menor"));

            //empleado de mayor sueldo
            Optional<Empleado> mayorSueldo = empleados.stream().max(Comparator.comparing(Empleado::getSueldo));
            System.out.println("Mayor sueldo: " + (mayorSueldo.isPresent() ? mayorSueldo.get() : "no hay mayor"));

            //Listado de empleados del departamento 'Research and Development'
            System.out.println("\nLista de empleados departamento: Research and Development");
            System.out.printf("%10S\t%-20S\t%-26S\t%12S%n", "RUT", "NOMBRE", "DEPARTAMENTO", "SUELDO");
            List<Empleado> empRD=empleados.stream()
                    .filter(emp -> emp.getDepartamento().equals("Research and Development"))
                    .sorted(Comparator.comparing(Empleado::getNombre))
                    .toList();
            for (Empleado emp : empRD) {
                   System.out.printf("%10s\t%-20s\t%-26s\t%,12d %n", emp.getRut(), emp.getNombre(), emp.getDepartamento(), emp.getSueldo());
            }
            //lo mismo sin guardar la lista:
            //Listado de empleados del departamento 'Research and Development'
            System.out.println("\nLista de empleados departamento: Payroll");
            System.out.printf("%10S\t%-20S\t%-26S\t%12S%n", "RUT", "NOMBRE", "DEPARTAMENTO", "SUELDO");
            empleados.stream()
                    .filter(emp -> emp.getDepartamento().equals("Payroll"))
                    .sorted(Comparator.comparing(Empleado::getNombre))
                    .forEach(emp -> System.out.printf("%10s\t%-20s\t%-26s\t%,12d %n", emp.getRut(), emp.getNombre(), emp.getDepartamento(), emp.getSueldo()));


            //cantidad de empleados por departamento
            Map<String, Long> CantidadEmpleadosPorDepartamento = empleados.stream()
                    .collect(Collectors.groupingBy(
                                Empleado::getDepartamento, //columna por la que se agrupa
                                TreeMap::new,  //estructura de datos que se usa para agrupar
                                Collectors.counting() //operación de agregación
                            )
                    );

            //reporte:
            System.out.println("\nCantidad de empleados por departamento");
            System.out.printf("%-26s\t%10s%n", "Departamento", "empleados");
            CantidadEmpleadosPorDepartamento.forEach((depto, count) -> System.out.printf("%-26s\t%,10d%n", depto, count));

            //Total de remuneraciones por departamento
            Map<String, Long> remuneracionesPorDepartamento = empleados.stream()
                    .collect(Collectors.groupingBy(Empleado::getDepartamento, TreeMap::new, Collectors.summingLong(Empleado::getSueldo)));

            //reporte:
            System.out.println("\nSuma de remuneracioens por departamento");
            System.out.printf("%-26s\t%10s%n", "Departamento", "Suma sueldos");
            remuneracionesPorDepartamento.forEach((depto, suma) -> System.out.printf("%-26s\t%,10d%n", depto, suma));

            //promedio por departamento
            System.out.println("\nSueldo promedio por departamento");
            Map<String, Double> promedioPorDepartamento = empleados.stream()
                    .collect(Collectors.groupingBy(Empleado::getDepartamento, TreeMap::new, Collectors.averagingDouble(Empleado::getSueldo)));
            //reporte:
            System.out.printf("%-26s\t%10s%n", "Departamento", "Sueldo promedio");
            promedioPorDepartamento.forEach((depto, prom) -> System.out.printf("%-26s\t%,10.0f%n", depto, prom));

            //menor sueldo por departamento
            System.out.println("\nMenor Sueldo por departamento");
            Map<String, Optional<Empleado>> menorSueldoPorDepartamento = empleados.stream()
                    .collect(Collectors.groupingBy(Empleado::getDepartamento, TreeMap::new, Collectors.minBy(Comparator.comparing(Empleado::getSueldo))));
            //reporte:
            System.out.printf("%-26s\t%10s%n", "Departamento", "Sueldo menor");
            menorSueldoPorDepartamento.forEach((depto, min) -> System.out.printf("%-26s\t%,10d%n", depto, min.isPresent() ? min.get().getSueldo() : 0));

            //menor sueldo por departamento
            System.out.println("\nMayor Sueldo por departamento");
            Map<String, Optional<Empleado>> mayorSueldoPorDepartamento = empleados.stream()
                    .collect(Collectors.groupingBy(Empleado::getDepartamento, TreeMap::new, Collectors.maxBy(Comparator.comparing(Empleado::getSueldo))));
            //reporte:
            System.out.printf("%-26s\t%10s%n", "Departamento", "Sueldo menor");
            mayorSueldoPorDepartamento.forEach((depto, max) -> System.out.printf("%-26s\t%,10d%n", depto, max.isPresent() ? max.get().getSueldo() : 0));

            //todo en un solo reporte
            System.out.printf("%n%S%n", "Estadisticas de sueldo por departamento");
            System.out.printf("%-26S\t%10S\t%10S\t%10S\t%10S\t%10S%n", "Departamento", "Cantidad", "Suma", "Promedio", "menor", "Mayor");
            CantidadEmpleadosPorDepartamento.forEach((depto, count) -> System.out.printf("%-26s\t%,10d\t%,10d\t%,10.0f\t%,10d\t%,10d%n",
                    depto,
                    count,
                    remuneracionesPorDepartamento.get(depto),
                    promedioPorDepartamento.get(depto),
                    menorSueldoPorDepartamento.get(depto).isPresent() ? menorSueldoPorDepartamento.get(depto).get().getSueldo() : 0,
                    mayorSueldoPorDepartamento.get(depto).isPresent() ? mayorSueldoPorDepartamento.get(depto).get().getSueldo() : 0
            ));

            //Ejercicios:
                //1) listar empleados cuyo nombre comienza con S
            System.out.println("\nLista de empleados cuyo nombre comienza con S");
            empleados
                    .stream()
                    .filter(x->x.getNombre().toUpperCase().charAt(0)=='S')
                    .forEach(x-> System.out.println(x.getNombre()));

                //2) listar empleados con sueldo en un rango de valores dado
            System.out.println("\nLista de empleados con sueldo entre 600 y 1000");
            empleados.stream()
                       .filter(x->x.getSueldo()>=600000)
                       .filter(x->x.getSueldo()<=1000000)
                       .forEach(x-> System.out.printf("%-20s $%,10d %n",x.getNombre(),x.getSueldo()));

                //3) Indicar el nombre del deptartamento al que pertenece: "Jessica Jimenez"
            Optional<Empleado> resp=empleados
                    .stream()
                    .filter(x->x.getNombre().equalsIgnoreCase("Miguel"))
                    .findFirst();
            if(resp.isPresent()) {
                System.out.println("Departamento de Jessica es: "+ resp.get().getDepartamento() );
            }else{
                System.out.println("No trabaja en la empresa");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo: 'empleados.txt'");
        }
    }
}
