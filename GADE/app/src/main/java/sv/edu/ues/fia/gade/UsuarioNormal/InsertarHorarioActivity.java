package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Horario;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class InsertarHorarioActivity extends Activity
{

    controlDB helper;
    EditText editIdHorario, editDia, editHorarioDesde, editHorarioHasta;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_horario);
        helper = new controlDB(this);
        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
        editDia= (EditText) findViewById(R.id.editDia);
        editHorarioDesde = (EditText) findViewById(R.id.editHorarioDesde);
        editHorarioHasta = (EditText) findViewById(R.id.editHorarioHasta);
    }
    public void insertarHorario(View v){
        int idHorario=Integer.parseInt(editIdHorario.getText().toString());
        String dia=editDia.getText().toString();
        String horarioDesde=editHorarioDesde.getText().toString();
        String horarioHasta=editHorarioHasta.getText().toString();
        String regInsertados;
        Horario horario=new Horario();
        horario.setIdHorario(idHorario);
        horario.setDia(dia);
        horario.setHorarioDesde(horarioDesde);
        horario.setHorarioHasta(horarioHasta);
        helper.abrir();
        regInsertados=helper.insertar(horario);
        helper.close();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v) {
        editIdHorario.setText("");
        editDia.setText("");
        editHorarioDesde.setText("");
        editHorarioHasta.setText("");
    }
}
