package sv.edu.ues.fia.gade.clases;

/**
 * Created by Christian on 7/5/2018.
 */

public class Reserva {
    private int idReserva;
    private int estado;
    private int idActividad;

    public Reserva(int idReserva, int estado, int idActividad) {
        this.idReserva = idReserva;
        this.estado = estado;
        this.idActividad = idActividad;
    }

    public Reserva() {
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

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", estado=" + estado +
                ", idActividad=" + idActividad +
                '}';
    }
}
