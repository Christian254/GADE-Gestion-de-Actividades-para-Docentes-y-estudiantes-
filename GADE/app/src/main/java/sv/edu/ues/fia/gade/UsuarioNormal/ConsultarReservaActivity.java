package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Actividad;
import sv.edu.ues.fia.gade.clases.Alumno;
import sv.edu.ues.fia.gade.clases.Docente;
import sv.edu.ues.fia.gade.clases.Participacion;
import sv.edu.ues.fia.gade.clases.Reserva;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ConsultarReservaActivity extends Activity {
    controlDB helper;
    EditText editId, buscar, buscarCarnet;
    EditText editEstado, editAct, editNomActividad, editNomDoc, editNomEst;


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
        editNomEst = (EditText) findViewById(R.id.editNomEstudiante);
        buscarCarnet = (EditText) findViewById(R.id.carnetBuscar);
    }

    public void consultaReserva(View v)
    {
        String dato="";
        String idReserva = buscar.getText().toString().trim();
        String carnet = buscarCarnet.getText().toString().trim();
        helper.abrir();
        if(idReserva.isEmpty() || carnet.isEmpty())
        {
            Toast.makeText(this,"Llene los dos campos",Toast.LENGTH_LONG).show();
        }
        else {

        int idRes = Integer.parseInt(idReserva);
        Cursor cursor = helper.getDataReserva(idRes,carnet);

        if(cursor.moveToFirst()) {
            editId.setText(cursor.getString(0));

            switch (cursor.getInt(1)) {
                case 1:
                    editEstado.setText("Aprobado");
                    break;
                case 2:
                    editEstado.setText("Pendiente");
                    break;
                case 3:
                    editEstado.setText("Denegado");
                    break;
            }
            editAct.setText(cursor.getString(2));
            editNomActividad.setText(cursor.getString(6));
            editNomDoc.setText(cursor.getString(16));
            editNomEst.setText(cursor.getString(13));
        }
        else
        {
            Toast.makeText(this, "La reserva con id "+ buscar.getText().toString() +"y carnet "+buscarCarnet.getText().toString() + " no fue encontrada",Toast.LENGTH_LONG).show();
        }



        helper.close();
        }







    }
    public void limpiarTexto(View v){
        editId.setText("");
        editEstado.setText("");
        editAct.setText("");
        buscar.setText("");
        buscarCarnet.setText("");
        editNomActividad.setText("");
        editNomDoc.setText("");
        editNomEst.setText("");

    }

}
