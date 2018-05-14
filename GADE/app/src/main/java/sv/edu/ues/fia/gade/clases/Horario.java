package sv.edu.ues.fia.gade.clases;

public class Horario {
    private int idHorario;
    private String dia;
    private String horarioDesde;
    private String horarioHasta;

    public Horario(){
    }
    public Horario(int idHorario, String dia, String horarioDesde, String horarioHasta) {
        this.idHorario = idHorario;
        this.dia=dia;
        this.horarioDesde = horarioDesde;
        this.horarioHasta = horarioHasta;
    }
    public int getIdHorario() {
        return idHorario;
    }
    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }
    public String getDia(){return dia;}
    public void setDia(String dia){this.dia=dia;}
    public String getHorarioDesde() {
        return horarioDesde;
    }
    public void setHorarioDesde(String horarioDesde) {
        this.horarioDesde = horarioDesde;
    }
    public String getHorarioHasta() {
        return horarioHasta;
    }
    public void setHorarioHasta(String horarioHasta) {
        this.horarioHasta = horarioHasta;
    }
}
