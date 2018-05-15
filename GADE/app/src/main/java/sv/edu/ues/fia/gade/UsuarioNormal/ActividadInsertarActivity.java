package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Actividad;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ActividadInsertarActivity extends Activity
{

    controlDB helper;
    EditText editIdActvidad, editNomAct, editTipoActividad, editIdDoc;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_insertar);
        helper = new controlDB(this);
        editIdDoc = (EditText) findViewById(R.id.editIdDoc);
        editIdActvidad = (EditText) findViewById(R.id.editIdActividad);
        editNomAct = (EditText) findViewById(R.id.editNomAct);
        editTipoActividad = (EditText) findViewById(R.id.editTipoActividad);
    }
    public void insertarActividad(View v)
    {
        int idDocente=Integer.parseInt(editIdDoc.getText().toString());
        int idActividad=Integer.parseInt(editIdActvidad.getText().toString());
        int tipoActvidad = Integer.parseInt(editTipoActividad.getText().toString());
        String nomActividad=editNomAct.getText().toString();
        String regInsertados;
        Actividad actividad =  new Actividad();
        actividad.setIdDocente(idDocente);
        actividad.setIdActividad(idActividad);
        actividad.setTipoActividad(tipoActvidad);
        actividad.setNomActividad(nomActividad);
        helper.abrir();
        regInsertados=helper.insertActividad(actividad);
        helper.close();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v)
    {
        editIdDoc.setText("");
        editIdActvidad.setText("");
        editNomAct.setText("");
        editTipoActividad.setText("");
    }
}
