package sv.edu.ues.fia.gade.clases;

// db.execSQL("create table PARTICIPACION(IDACTIVIDAD INTEGER not null,CARNET INTEGER not null,VALORACION INTEGER not null,COMENTARIO TEXT not null, primary key (IDACTIVIDAD,CARNET)) ");
public class Participacion {
   private int idActividad;
   private String carnet;
   private int valoracion;
   private String comentario;

    public Participacion() {

    }

    public Participacion(int idActividad, String carnet, int valoracion, String comentario) {
        this.idActividad = idActividad;
        this.carnet = carnet;
        this.valoracion = valoracion;
        this.comentario = comentario;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
