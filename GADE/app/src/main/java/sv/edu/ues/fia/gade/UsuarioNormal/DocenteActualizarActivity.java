package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Docente;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class DocenteActualizarActivity extends Activity
{

    private controlDB helper;
    private EditText editIdDoc, editIdEscuela, editNomDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar);
        helper = new controlDB(this);
        editIdDoc = (EditText) findViewById(R.id.editIdDoc);
        editIdEscuela = (EditText) findViewById(R.id.editIdEscuela);
        editNomDoc = (EditText) findViewById(R.id.editNomDoc);
    }
    public void actualizarDocente(View v)
    {
        Docente docente = new Docente();
        docente.setIdDocente(Integer.parseInt(editIdDoc.getText().toString()));
        docente.setIdEscuela(Integer.parseInt(editIdEscuela.getText().toString()));
        docente.setNombreDoc(editNomDoc.getText().toString());
        String regAct = helper.actualizarDocente(docente);
        Toast.makeText(this,regAct,Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v)
    {
        editIdDoc.setText("");
        editIdEscuela.setText("");
        editNomDoc.setText("");
    }
}
