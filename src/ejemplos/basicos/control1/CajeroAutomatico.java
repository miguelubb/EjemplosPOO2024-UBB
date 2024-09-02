package ejemplos.basicos.control1;

public class CajeroAutomatico {
    public final static int MONEDA1 = 10000;
    public final static int MONEDA2 = 20000;

    private String id;
    private int nroBilletesMoneda1;
    private int nroBilletesMoneda2;
    private Estado estado;

    public CajeroAutomatico(String id) {
        this.id = id;
        nroBilletesMoneda1 = 0;
        nroBilletesMoneda2 = 0;
        estado = Estado.OPERATIVO;
    }

    public String getId() {
        return id.toUpperCase();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getSaldo(int moneda) {
        if (moneda == MONEDA1) {
            return nroBilletesMoneda1 * MONEDA1;
        } else {
            return nroBilletesMoneda2 * MONEDA2;
        }
    }

    public void agregaVilletes(int nroMonedas, int moneda) {
        if (estado == Estado.OPERATIVO) {
            if (moneda == MONEDA1) {
                nroBilletesMoneda1 += nroMonedas;
            } else {
                nroBilletesMoneda2 += nroMonedas;
            }
        }
    }

    public int[] gira(int monto) {
        if (estado == Estado.INOPERATIVO) {
            return new int[]{0, 0};
        }
        int billetes2 = monto / MONEDA2;
        int saldo1 = monto - (billetes2 * MONEDA2);
        int billetes1= saldo1 / MONEDA1;
        if (saldo1 % MONEDA1 == 0 && billetes1 <= nroBilletesMoneda1 && billetes2 <= nroBilletesMoneda2) {
            nroBilletesMoneda1 -= billetes1;
            nroBilletesMoneda2 -= billetes2;
            return new int[]{billetes1, billetes2};
        }
        return new int[]{0, 0};
    }

    public boolean equal(CajeroAutomatico otro) {
        return this.id.equals(otro.id);
    }

    /*
        toString retorna el id (con caracteres alfabéticos en mayúsculas),
        el saldo total considerando todos los billetes del cajero y el estado de este en minúsculas.
        Los datos se deben separar mediante una coma y un espacio.
     */

    @Override
    public String toString() {
        int saldoTotal=getSaldo(MONEDA1)+getSaldo(MONEDA2);
        return getId()+", "+saldoTotal+", "+getEstado().toString().toLowerCase();
    }
}
