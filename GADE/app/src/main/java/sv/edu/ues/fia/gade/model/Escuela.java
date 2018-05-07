package sv.edu.ues.fia.gade.model;

public class Escuela {
    private int identificadorEscuela;
    private String nombreEscuela;

    public Escuela() {
    }

    public Escuela(int identificadorEscuela, String nombreEscuela) {
        this.identificadorEscuela = identificadorEscuela;
        this.nombreEscuela = nombreEscuela;
    }

    public int getIdentificadorEscuela() {
        return identificadorEscuela;
    }

    public void setIdentificadorEscuela(int identificadorEscuela) {
        this.identificadorEscuela = identificadorEscuela;
    }

    public String getNombreEscuela() {
        return nombreEscuela;
    }

    public void setNombreEscuela(String nombreEscuela) {
        this.nombreEscuela = nombreEscuela;
    }
}
