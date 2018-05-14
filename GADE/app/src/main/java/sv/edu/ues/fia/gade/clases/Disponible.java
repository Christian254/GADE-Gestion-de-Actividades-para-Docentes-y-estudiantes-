package sv.edu.ues.fia.gade.clases;

/**
 * Created by Christian on 12/5/2018.
 */

public class Disponible {
    private int idHorario, idLocal, idReserva, idCiclo, disponible; // disponible 1: Si 2:No

    public Disponible() {
    }

    public Disponible(int idHorario, int idLocal, int idReserva, int idCiclo, int disponible) {
        this.idHorario = idHorario;
        this.idLocal = idLocal;
        this.idReserva = idReserva;
        this.idCiclo = idCiclo;
        this.disponible = disponible;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }
}
