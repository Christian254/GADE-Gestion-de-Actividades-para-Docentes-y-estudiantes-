package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Docente;
import sv.edu.ues.fia.gade.clases.Reserva;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class DocenteEliminarActivity extends Activity
{
    EditText editIdDoc;
    controlDB controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_eliminar);
        controlhelper=new controlDB (this);
        editIdDoc=(EditText)findViewById(R.id.editIdDoc);
    }
    public void eliminarDocente(View v)
    {
        String regEliminadas;
        Docente docente = new Docente();
        docente.setIdDocente(Integer.parseInt(editIdDoc.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarDocente(docente);
        controlhelper.close();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
