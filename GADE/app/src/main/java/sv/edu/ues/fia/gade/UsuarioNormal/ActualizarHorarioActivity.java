package sv.edu.ues.fia.gade.UsuarioNormal;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Horario;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ActualizarHorarioActivity extends Activity {
    controlDB helper;
    EditText editIdHorario, editHorarioDesde, editHorarioHasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_horario);
        helper = new controlDB(this);
        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
        editHorarioDesde = (EditText) findViewById(R.id.editHorarioDesde);
        editHorarioHasta = (EditText) findViewById(R.id.editHorarioHasta);
    }
    public void actualizarHorario(View v) {
        Horario horario = new Horario();

        horario.setIdHorario(Integer.parseInt(editIdHorario.getText().toString()));
        horario.setHorarioDesde(editHorarioDesde.getText().toString());
        horario.setHorarioHasta(editHorarioHasta.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(horario);
        helper.close();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdHorario.setText("");
        editHorarioDesde.setText("");
        editHorarioHasta.setText("");
    }
}
