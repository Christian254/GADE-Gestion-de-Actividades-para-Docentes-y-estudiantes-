package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Actividad;
import sv.edu.ues.fia.gade.clases.TipoActividad;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class TipoActividadActualizarActivity extends Activity
{
    private controlDB helper;
    private EditText editIdTipoActividad, editNomTipoAct;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_actividad_actualizar);
        helper = new controlDB(this);
        editIdTipoActividad = (EditText) findViewById(R.id.editIdTipoActividad);
        editNomTipoAct = (EditText) findViewById(R.id.editNomTipoAct);
    }
    public void actualizarTipoActividad(View v)
    {
        TipoActividad tipoActividad = new TipoActividad();
        tipoActividad.setIdTipoActividad(Integer.parseInt(editIdTipoActividad.getText().toString()));
        tipoActividad.setNomTipoActividad(editNomTipoAct.getText().toString());
        String estado = helper.actualizarTipoActividad(tipoActividad);
        Toast.makeText(this,estado,Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v)
    {
        editIdTipoActividad.setText("");
        editNomTipoAct.setText("");
    }
}
