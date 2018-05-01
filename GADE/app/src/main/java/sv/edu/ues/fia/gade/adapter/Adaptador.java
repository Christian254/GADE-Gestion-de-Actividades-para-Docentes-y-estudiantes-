package sv.edu.ues.fia.gade.adapter;

import sv.edu.ues.fia.gade.model.AccesoUsuario;
import sv.edu.ues.fia.gade.model.OpcionCrud;
import sv.edu.ues.fia.gade.model.Usuario;

/**
 * Created by HP on 29/4/2018.
 */

public class Adaptador {
    private Usuario usuario;
    private AccesoUsuario acces;
    private OpcionCrud op;

    public Adaptador(Usuario usuario, AccesoUsuario acces, OpcionCrud op) {
        this.usuario = usuario;
        this.acces = acces;
        this.op=op;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public AccesoUsuario getAcces() {
        return acces;
    }

    public void setAcces(AccesoUsuario acces) {
        this.acces = acces;
    }

    public OpcionCrud getOp() {
        return op;
    }

    public void setOp(OpcionCrud op) {
        this.op = op;
    }
}
