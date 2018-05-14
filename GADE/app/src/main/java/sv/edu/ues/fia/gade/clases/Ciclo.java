package sv.edu.ues.fia.gade.clases;

public class Ciclo {
    private int idCiclo;
    private String numCiclo;

    public Ciclo(){
    }
    public Ciclo(int idCiclo, String numCiclo) {
        this.idCiclo = idCiclo;
        this.numCiclo = numCiclo;
    }
    public int getIdCiclo() {
        return idCiclo;
    }
    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }
    public String getNumCiclo() {
        return numCiclo;
    }
    public void setNumCiclo(String horarioDesde) {
        this.numCiclo = numCiclo;
    }

}
