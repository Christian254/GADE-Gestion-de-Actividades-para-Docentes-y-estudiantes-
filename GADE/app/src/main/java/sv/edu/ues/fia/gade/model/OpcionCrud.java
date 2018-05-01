package sv.edu.ues.fia.gade.model;

/**
 * Created by HP on 28/4/2018.
 */

public class OpcionCrud {
    private String nombreCRUD;
    private int idOpcion;
    private int numCRUD;

    public OpcionCrud(String nombreCRUD, int idOpcion, int numCRUD) {
        this.nombreCRUD = nombreCRUD;
        this.idOpcion = idOpcion;
        this.numCRUD = numCRUD;
    }

    public String getNombreCRUD() {
        return nombreCRUD;
    }

    public void setNombreCRUD(String nombreCRUD) {
        this.nombreCRUD = nombreCRUD;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public int getNumCRUD() {
        return numCRUD;
    }

    public void setNumCRUD(int numCRUD) {
        this.numCRUD = numCRUD;
    }
}
