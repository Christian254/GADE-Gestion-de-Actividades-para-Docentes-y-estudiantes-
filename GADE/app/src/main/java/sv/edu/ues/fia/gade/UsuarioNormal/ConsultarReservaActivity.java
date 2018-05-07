package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Reserva;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ConsultarReservaActivity extends Activity {
    controlDB helper;
    EditText editId, buscar;
    EditText editEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_reserva);
        helper = new controlDB(this);
        buscar = (EditText) findViewById(R.id.editId);
        editId = (EditText) findViewById(R.id.editIdR);
        editEstado = (EditText) findViewById(R.id.editEstado);
    }

    public void consultaReserva(View v)
    {
        helper.abrir();
        Reserva reserva = helper.consultarReserva(buscar.getText().toString());
        helper.close();
        if(reserva==null)
        {
            Toast.makeText(this, "La reserva con id "+ buscar.getText().toString() + " no fue encontrada",Toast.LENGTH_LONG).show();
        }
        else
        {
            editId.setText(String.valueOf(reserva.getIdReserva()));
            editEstado.setText(String.valueOf(reserva.getEstado()));
        }

    }
    public void limpiarTexto(View v){
        editId.setText("");
        editEstado.setText("");

    }

}
