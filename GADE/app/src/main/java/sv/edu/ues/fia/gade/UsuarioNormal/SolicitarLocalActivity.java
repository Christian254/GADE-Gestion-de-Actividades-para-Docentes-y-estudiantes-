package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Alumno;
import sv.edu.ues.fia.gade.clases.Docente;
import sv.edu.ues.fia.gade.clases.Reserva;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class SolicitarLocalActivity extends Activity {
    controlDB helper;
    EditText editIdReserva, editIdAct,editAlumno, editEscuela, editNombre, editNomDoc, editIdDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_local);
        helper = new controlDB(this);
        editIdReserva = (EditText) findViewById(R.id.editIdReserva);
        editIdAct = (EditText) findViewById(R.id.editIdActividad);
        editAlumno = (EditText) findViewById(R.id.editCarnet);
        editEscuela = (EditText) findViewById(R.id.editIdEscuela);
        editNombre = (EditText) findViewById(R.id.editNomEst);
        editIdDoc = (EditText) findViewById(R.id.editIdDoc);
        editNomDoc = (EditText) findViewById(R.id.editNomDoc);
    }

    public void insertarReserva(View v)
    {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(Integer.parseInt(editIdReserva.getText().toString()));
        reserva.setEstado(2);
        reserva.setIdActividad(Integer.parseInt(editIdAct.getText().toString()));

        Alumno alumno = new Alumno();
        alumno.setCarnet(editAlumno.getText().toString());
        alumno.setNombre(editNombre.getText().toString());
        alumno.setIdEscuela(Integer.parseInt(editEscuela.getText().toString()));

        Docente docente = new Docente();
        docente.setIdDocente(Integer.parseInt(editIdDoc.getText().toString()));
        docente.setIdEscuela(Integer.parseInt(editEscuela.getText().toString()));
        docente.setNombreDoc(editNomDoc.getText().toString());

        String regInsert= helper.insertReserva(reserva, alumno, docente);
        Toast.makeText(this,regInsert,Toast.LENGTH_SHORT).show();
    }
}
