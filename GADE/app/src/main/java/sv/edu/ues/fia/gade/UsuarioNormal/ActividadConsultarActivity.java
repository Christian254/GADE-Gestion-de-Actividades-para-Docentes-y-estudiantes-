package sv.edu.ues.fia.gade.UsuarioNormal;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Actividad;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ActividadConsultarActivity extends Activity
{
    controlDB helper;

    EditText editIdActividad;
    EditText editTipoActividad;
    EditText editIdDoc;
    EditText editNomActividad;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_consultar);
        helper = new controlDB(this);

        editIdActividad = (EditText) findViewById(R.id.editIdActividad);
        editNomActividad = (EditText) findViewById(R.id.editNomActividad);
        editTipoActividad = (EditText) findViewById(R.id.editTipoActividad);
        editIdDoc = (EditText) findViewById(R.id.editIdDoc);
    }

    public void consultarActividad(View v)
    {

        helper.abrir();
        Actividad actividad = helper.consultarActividad(editIdActividad.getText().toString());
        helper.close();

        if(actividad == null)
            Toast.makeText(this, "Actividad Con Identificador #" + editIdActividad.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            editNomActividad.setText(actividad.getNomActividad());
            editTipoActividad.setText(String.valueOf(actividad.getTipoActividad()));
            editIdDoc.setText(String.valueOf(actividad.getIdDocente()));
        }
    }

    public void limpiarTexto(View v)
    {
        editIdActividad.setText("");
        editNomActividad.setText("");
        editTipoActividad.setText("");
        editIdDoc.setText("");
    }
}
