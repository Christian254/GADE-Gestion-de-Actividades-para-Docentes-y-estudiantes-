package sv.edu.ues.fia.gade.model;

/**
 * Created by HP on 28/4/2018.
 */

public class AccesoUsuario {
    private int idOpcion;
    private String idUser;

    public AccesoUsuario(int idOpcion, String idUser) {
        this.idOpcion = idOpcion;
        this.idUser = idUser;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
