package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Actividad;
import sv.edu.ues.fia.gade.clases.Docente;
import sv.edu.ues.fia.gade.clases.Reserva;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ConsultarReservaActivity extends Activity {
    controlDB helper;
    EditText editId, buscar;
    EditText editEstado, editAct, editNomActividad, editNomDoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_reserva);
        helper = new controlDB(this);
        buscar = (EditText) findViewById(R.id.editId);
        editId = (EditText) findViewById(R.id.editIdR);
        editEstado = (EditText) findViewById(R.id.editEstado);
        editAct = (EditText) findViewById(R.id.editActividad);
        editNomActividad = (EditText) findViewById(R.id.editNomAct);
        editNomDoc = (EditText) findViewById(R.id.editNombreDocente);
    }

    public void consultaReserva(View v)
    {
        helper.abrir();
        Reserva reserva = helper.consultarReserva(buscar.getText().toString());
        helper.close();
        if(reserva==null)
        {
            Toast.makeText(this, "La reserva con id "+ buscar.getText().toString() + " no fue encontrada",Toast.LENGTH_LONG).show();
        }
        else
        {
            editId.setText(String.valueOf(reserva.getIdReserva()));
            editEstado.setText(String.valueOf(reserva.getEstado()));
            editAct.setText(String.valueOf(reserva.getIdActividad()));

            helper.abrir();
            Actividad actividad = helper.consultarActividad(String.valueOf(reserva.getIdActividad()));
            String id_docente = String.valueOf(actividad.getIdDocente());
            helper.close();
            editNomActividad.setText(actividad.getNomActividad());

            helper.abrir();
            Docente docente = helper.consultarDocente(id_docente);
            int id_escuela = docente.getIdEscuela();
            helper.close();
            editNomDoc.setText(docente.getNombreDoc());




        }



    }
    public void limpiarTexto(View v){
        editId.setText("");
        editEstado.setText("");
        editAct.setText("");
        buscar.setText("");

    }

}
