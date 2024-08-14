package ejemplos.basicos.estanque;

public class Estanque {
    private float altura;
    private float alturaLlenado;
    private float radio;

    public Estanque(float altura, float radio) {
        this.altura = altura;
        this.radio = radio;
        alturaLlenado = 0;
    }

    public boolean agrega(float volumen){
        if(getCapacidadDisponible()>=volumen){
            float nuevoVolumen=getCapacidadOcupada()+volumen;
            alturaLlenado=calcularAltura(nuevoVolumen);
            return true;
        }
        return false;
    }

    private float calcularAltura(float volumen){
        //volumen=PI * radio * radio * altura
        //altura=volumen/(PI * radio * radio)
        return (float)(volumen/(Math.PI*radio*radio));
    }

    private float calcularVolumen(float altura){
        return (float)(Math.PI * radio * radio * altura);
    }

    public boolean extrae(float volumen){
        if(getCapacidadOcupada()>=volumen){
            float nuevoVolumen=getCapacidadOcupada()-volumen;
            alturaLlenado=calcularAltura(nuevoVolumen);
            return true;
        }
        return false;
    }
    public float getAlturaLlenado() {
        return alturaLlenado;
    }

    public float getCapacidadOcupada() {
        return calcularVolumen(alturaLlenado);
    }

    public float getCapacidadDisponible() {
        return calcularVolumen(altura-alturaLlenado);
    }

    public boolean equals(Estanque otro) {
        return this.altura==otro.altura && this.radio==otro.radio;
    }
}
