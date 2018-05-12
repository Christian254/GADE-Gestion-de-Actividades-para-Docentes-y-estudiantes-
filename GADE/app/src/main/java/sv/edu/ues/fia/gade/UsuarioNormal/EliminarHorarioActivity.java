package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Horario;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class EliminarHorarioActivity extends Activity {
    EditText editIdHorario;
    controlDB controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_horario);
        controlhelper=new controlDB (this);
        editIdHorario=(EditText)findViewById(R.id.editIdHorario);
    }
    public void eliminarHorario(View v){
        String regEliminadas;
        Horario horario=new Horario();
        horario.setIdHorario(Integer.parseInt(editIdHorario.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(horario);
        controlhelper.close();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

}
