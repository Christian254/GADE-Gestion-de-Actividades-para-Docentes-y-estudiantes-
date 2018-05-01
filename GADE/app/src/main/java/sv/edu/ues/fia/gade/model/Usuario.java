package sv.edu.ues.fia.gade.model;

/**
 * Created by HP on 28/4/2018.
 */

public class Usuario {
    private String username;
    private String clave;
    private int tipo;

    public Usuario(String username, String clave, int tipo) {
        if (tipo==1 || tipo==2){
            this.username = username;
            this.clave = clave;
            this.tipo = tipo;
        }else{
            this.username = username;
            this.clave = clave;
            this.tipo = 1;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        if (tipo==1 || tipo==2){
            this.tipo = tipo;
        }else{
            this.tipo = 1;
        }
    }
}
