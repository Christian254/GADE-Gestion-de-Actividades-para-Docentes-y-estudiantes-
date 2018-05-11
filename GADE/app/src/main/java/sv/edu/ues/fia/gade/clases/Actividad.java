package sv.edu.ues.fia.gade.clases;

/**
 * Created by Christian on 10/5/2018.
 */

public class Actividad {
    private int idActividad;
    private int tipoActividad;
    private int idDocente;
    private String nomActividad;

    public Actividad() {
    }

    public Actividad(int idActividad, int tipoActividad, int idDocente, String nomActividad) {
        this.idActividad = idActividad;
        this.tipoActividad = tipoActividad;
        this.idDocente = idDocente;
        this.nomActividad = nomActividad;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(int tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getNomActividad() {
        return nomActividad;
    }

    public void setNomActividad(String nomActividad) {
        this.nomActividad = nomActividad;
    }
}
