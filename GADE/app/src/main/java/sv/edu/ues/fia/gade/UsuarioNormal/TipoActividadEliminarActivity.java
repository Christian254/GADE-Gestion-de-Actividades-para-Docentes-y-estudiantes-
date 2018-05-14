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

public class TipoActividadEliminarActivity extends Activity
{

    EditText editIdTipoActividad;
    controlDB controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_actividad_eliminar);
        controlhelper=new controlDB (this);
        editIdTipoActividad=(EditText)findViewById(R.id.editIdTipoActividad);
    }
    public void eliminarTipoActividad(View v)
    {
        String regEliminadas;
        TipoActividad tipo = new TipoActividad();
        tipo.setIdTipoActividad(Integer.parseInt(editIdTipoActividad.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarTipoActividad(tipo); //ultimo trabajado
        controlhelper.close();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
