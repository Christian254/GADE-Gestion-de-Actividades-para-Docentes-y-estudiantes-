package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Reserva;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class SolicitarLocalActivity extends Activity {
    controlDB helper;
    EditText editId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_local);
        editId = (EditText) findViewById(R.id.editIdReserva);
    }

    public void insertarReservas(View v)
    {
        int idreserva= Integer.parseInt(editId.getText().toString());
        String regInsertados="registro insertado";
        Reserva reserva = new Reserva();
        reserva.setIdReserva(1);
        reserva.setEstado(2);
        reserva.setIdActividad(1);
        helper.abrir();
        // helper.insertarReservas(reserva);
        helper.close();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
}
