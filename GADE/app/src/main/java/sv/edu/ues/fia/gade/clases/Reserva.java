package sv.edu.ues.fia.gade.clases;

/**
 * Created by Christian on 7/5/2018.
 */

public class Reserva {
    private int idReserva;
    private int estado;

    public Reserva() {
    }

    public Reserva(int idReserva, int estado) {
        this.idReserva = idReserva;
        this.estado = estado;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
