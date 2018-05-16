package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Actividad;
import sv.edu.ues.fia.gade.clases.Alumno;
import sv.edu.ues.fia.gade.clases.Disponible;
import sv.edu.ues.fia.gade.clases.Docente;
import sv.edu.ues.fia.gade.clases.Participacion;
import sv.edu.ues.fia.gade.clases.Reserva;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class SolicitarLocalActivity extends Activity {
    controlDB helper;
    private String idLocal, idCiclo, idHorario;
    EditText editIdReserva, editIdAct,editAlumno, editEscuela, editNombre, editNomDoc, editIdDoc, editTipoAct, editNombreAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_local);

        idLocal = getIntent().getExtras().getString("idLocal");
        idCiclo = getIntent().getExtras().getString("idCiclo");
        idHorario = getIntent().getExtras().getString("idHorario");
        //Borrar esta parte junto al textView que involucra
        TextView tx = (TextView)findViewById(R.id.borrarEsteTextView);
        tx.setText("IdLocal= "+idLocal+ " idCiclo = "+idCiclo+ " idHorario = "+idHorario);
        //Has aqui borrar

        helper = new controlDB(this);
        editIdReserva = (EditText) findViewById(R.id.editIdReserva);
        editIdAct = (EditText) findViewById(R.id.editIdActividad);
        editAlumno = (EditText) findViewById(R.id.editCarnet);
        editEscuela = (EditText) findViewById(R.id.editIdEscuela);
        editNombre = (EditText) findViewById(R.id.editNomEst);
        editIdDoc = (EditText) findViewById(R.id.editIdDoc);
        editNomDoc = (EditText) findViewById(R.id.editNomDoc);
        editTipoAct = (EditText) findViewById(R.id.editTipoActividad);
        editNombreAct = (EditText) findViewById(R.id.editNomActividad);
    }

    public void insertarReserva(View v)
    {
        String idReserva = editIdReserva.getText().toString().trim();
        String idAct = editIdAct.getText().toString().trim();
        String alumnoCarnet = editAlumno.getText().toString().trim();
        String alumnoNombre = editNombre.getText().toString().trim();
        String idEscuela = editEscuela.getText().toString().trim();
        String idDocente = editIdDoc.getText().toString().trim();
        String docenteNombre = editNomDoc.getText().toString().trim();
        String tipoActividad = editTipoAct.getText().toString().trim();
        String nombreActividad = editNombreAct.getText().toString().trim();

        if(idReserva.isEmpty() || idAct.isEmpty() || alumnoCarnet.isEmpty() || alumnoNombre.isEmpty() || idEscuela.isEmpty() || idDocente.isEmpty() || docenteNombre.isEmpty() ||tipoActividad.isEmpty() || nombreActividad.isEmpty())
        {
            Toast.makeText(this,"Todos los campos son obligatorios",Toast.LENGTH_SHORT).show();
        }
        else {


            Reserva reserva = new Reserva();
            reserva.setIdReserva(Integer.parseInt(idReserva));
            reserva.setEstado(2);
            reserva.setIdActividad(Integer.parseInt(idAct));

            Alumno alumno = new Alumno();
            alumno.setCarnet(alumnoCarnet);
            alumno.setNombre(alumnoNombre);
            alumno.setIdEscuela(Integer.parseInt(idEscuela));

            Docente docente = new Docente();
            docente.setIdDocente(Integer.parseInt(idDocente));
            docente.setIdEscuela(Integer.parseInt(idEscuela));
            docente.setNombreDoc(docenteNombre);

            Actividad actividad = new Actividad();
            actividad.setIdActividad(Integer.parseInt(idAct));
            actividad.setIdDocente(Integer.parseInt(idDocente));
            actividad.setTipoActividad(Integer.parseInt(tipoActividad));
            actividad.setNomActividad(nombreActividad);

            Participacion participacion = new Participacion();
            participacion.setCarnet(alumnoCarnet);
            participacion.setIdActividad(Integer.parseInt(idAct));
            participacion.setComentario(" ");
            participacion.setValoracion(1);

            Disponible disponible = new Disponible();
            disponible.setIdReserva(Integer.parseInt(idReserva));
            disponible.setIdHorario(Integer.parseInt(idHorario));
            disponible.setIdLocal(Integer.parseInt(idLocal));
            disponible.setIdCiclo(Integer.parseInt(idCiclo));
            disponible.setDisponible(1);

            String regInsert = helper.insertReserva(reserva, alumno, docente, actividad, participacion);
            String dis =helper.insertarDisponible(disponible);
            Toast.makeText(this, regInsert + dis, Toast.LENGTH_SHORT).show();
        }
    }
}
