package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Horario;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ConsultarHorarioActivity extends Activity {
    controlDB helper;
    EditText editIdHorario, editHorarioDesde, editHorarioHasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_horario);
        helper = new controlDB(this);
        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
        editHorarioDesde = (EditText) findViewById(R.id.editHorarioDesde);
        editHorarioHasta = (EditText) findViewById(R.id.editHorarioHasta);
    }
    public void consultarHorario(View v) {
        helper.abrir();
        Horario horario =
                helper.consultarHorario(editIdHorario.getText().toString());
        helper.close();
        if(horario == null)
            Toast.makeText(this, "Id " + editIdHorario.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editHorarioDesde.setText(horario.getHorarioDesde());
            editHorarioHasta.setText(horario.getHorarioHasta());
        }
    }
    public void limpiarTexto(View v){
        editIdHorario.setText("");
        editHorarioDesde.setText("");
        editHorarioHasta.setText("");
    }
}
