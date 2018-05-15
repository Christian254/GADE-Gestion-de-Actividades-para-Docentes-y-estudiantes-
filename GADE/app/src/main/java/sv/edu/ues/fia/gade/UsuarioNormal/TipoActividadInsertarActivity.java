package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.TipoActividad;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class TipoActividadInsertarActivity extends Activity
{

    controlDB helper;
    EditText editIdTipoActividad,editNomTipoAct;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_actividad_insertar);
        helper = new controlDB(this);
        editIdTipoActividad = (EditText) findViewById(R.id.editIdTipoActividad);
        editNomTipoAct = (EditText) findViewById(R.id.editNomTipoAct);
    }
    public void insertarTipoActividad(View v)
    {
        int idTipoActividad =Integer.parseInt(editIdTipoActividad.getText().toString());
        String nomTipoActividad=editNomTipoAct.getText().toString();
        String regInsertados;
        TipoActividad tipo = new TipoActividad();
        tipo.setIdTipoActividad(idTipoActividad);
        tipo.setNomTipoActividad(nomTipoActividad);

        helper.abrir();
        regInsertados=helper.insertTipoActividad(tipo);
        helper.close();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v)
    {
        editIdTipoActividad.setText("");
        editNomTipoAct.setText("");
    }
}
