package ejemplos.basicos.estanque;

public class EstanqueTest {
    public static void main(String[] args) {
        //	Crear un objeto Estanque y verificar que se encuentra vacío.
        Estanque estanque=new Estanque(1,0.5f);
        System.out.println("true == "+(estanque.getCapacidadOcupada()==0));
        //	Agregar un volumen igual a la mitad de la capacidad disponible
        //	y desplegar el nivel de llenado.
        System.out.println("Agrega 50% capacidad disponible");
        estanque.agrega(estanque.getCapacidadDisponible()/2);
        System.out.println("Altura llenado = "+(estanque.getAlturaLlenado()));
        //	Extraer un volumen superior a la capacidad ocupada en el estanque
        //	y desplegar un mensaje apropiado.
        if(!estanque.extrae(estanque.getCapacidadOcupada()+10)){
            System.out.println("No se puede extraer más de la capacidad ocupada");
        }

        //	Extraer todo el material almacenado en el estanque y
        //	 verificar que este ha quedado vacío.
        estanque.extrae(estanque.getCapacidadDisponible());
        System.out.println("¿está vacío?="+ (estanque.getCapacidadDisponible()==0));

        //	Agregar un volumen superior a la capacidad total del estanque
        //	y desplegar un mensaje apropiado.
        float capacidadTotal=estanque.getCapacidadDisponible()
                + estanque.getCapacidadOcupada();
        estanque.agrega(capacidadTotal+10);

        //	Crear 2 objetos Estanque, uno cuyas dimensiones sean el doble
        //	del primer estanque creado y el otro con las mismas dimensiones
        //	del primero.
        Estanque eDoble=new Estanque(2.0f,1.0f);
        Estanque eIgual=new Estanque(1.0f,0.5f);

        //	Comparar los objetos 1 y 2 verificando que no son iguales,
        //	luego compare los objetos 1 y 3, verifique que estos últimos son iguales. Despliegue mensajes apropiados.
        System.out.println("Estanques son iguales?="+(estanque.equals(eIgual)));
        System.out.println("Estanques NO son iguales?="+(estanque.equals(eDoble)));
    }
}
