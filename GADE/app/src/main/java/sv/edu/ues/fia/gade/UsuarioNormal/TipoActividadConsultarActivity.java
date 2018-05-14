package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Docente;
import sv.edu.ues.fia.gade.clases.TipoActividad;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class TipoActividadConsultarActivity extends Activity
{
    controlDB helper;

    EditText editIdTipoActividad;
    EditText editNomTipoAct;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_actividad_consultar);
        helper = new controlDB(this);

        editIdTipoActividad = (EditText) findViewById(R.id.editIdTipoActividad);
        editNomTipoAct = (EditText) findViewById(R.id.editNomTipoAct);
    }

    public void consultarTipoActividad(View v)
    {

        helper.abrir();
        TipoActividad tipoActividad = helper.consultarTipoActividad(editIdTipoActividad.getText().toString());
        helper.close();

        if(tipoActividad == null)
            Toast.makeText(this, "Tipo Actividad Con Identificador " + editIdTipoActividad.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            editNomTipoAct.setText(tipoActividad.getNomTipoActividad());
        }
    }

    public void limpiarTexto(View v)
    {
        editIdTipoActividad.setText("");
        editNomTipoAct.setText("");
    }

}
