package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Actividad;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ActividadEliminarActivity extends Activity
{
    EditText editIdActividad;
    controlDB controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_eliminar);
        controlhelper=new controlDB(this);
        editIdActividad=(EditText)findViewById(R.id.editIdActividad);
    }
    public void eliminarActividad(View v)
    {
        String regEliminadas;
        Actividad actividad = new Actividad();
        actividad.setIdActividad(Integer.parseInt(editIdActividad.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarActividad(actividad);
        controlhelper.close();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

}
