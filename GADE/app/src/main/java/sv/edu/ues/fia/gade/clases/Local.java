package sv.edu.ues.fia.gade.clases;

/**
 * Created by HP on 13/5/2018.
 */

public class Local {
    private int idLocal;
    private String nombreLocal;
    private int tamanoLocal;

    public Local(int idLocal, String nombreLocal, int tamanoLocal) {
        this.idLocal = idLocal;
        this.nombreLocal = nombreLocal;
        this.tamanoLocal = tamanoLocal;
    }

    public Local() {
    }

    public int getTamanoLocal() {
        return tamanoLocal;
    }

    public void setTamanoLocal(int tamanoLocal) {
        this.tamanoLocal = tamanoLocal;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }
}
