package sv.edu.ues.fia.gade.clases;

/**
 * Created by Christian on 10/5/2018.
 */

public class Alumno {
    private String carnet;
    private int idEscuela;
    private String nombre;

    public Alumno() {
    }

    public Alumno(String carnet, int idEscuela, String nombre) {
        this.carnet = carnet;
        this.idEscuela = idEscuela;
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
