package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Actividad;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ActividadActualizarActivity extends Activity
{

    private controlDB helper;
    private EditText editIdActividad,editTipoActividad,editIdDoc,editNomAct;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_actualizar);
        helper = new controlDB(this);
        editIdActividad = (EditText) findViewById(R.id.editIdActividad);
        editTipoActividad = (EditText) findViewById(R.id.editTipoActividad);
        editIdDoc = (EditText) findViewById(R.id.editIdDoc);
        editNomAct = (EditText) findViewById(R.id.editNomAct);
    }
    public void actualizarActividad(View v)
    {
        Actividad actividad = new Actividad();
        actividad.setIdActividad(Integer.parseInt(editIdActividad.getText().toString()));
        actividad.setTipoActividad(Integer.parseInt(editTipoActividad.getText().toString()));
        actividad.setIdDocente(Integer.parseInt(editIdDoc.getText().toString()));
        actividad.setNomActividad(editNomAct.getText().toString());
        String estado = helper.actualizarActividad(actividad);
        Toast.makeText(this,estado,Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v)
    {
        editIdActividad.setText("");
        editTipoActividad.setText("");
        editIdDoc.setText("");
        editNomAct.setText("");
    }
}
