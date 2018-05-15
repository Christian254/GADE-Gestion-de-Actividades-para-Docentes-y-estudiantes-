package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Reserva;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class EliminarReservaActivity extends Activity {
    private controlDB helper;
    private EditText editId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_reserva);
        helper = new controlDB(this);
        editId = (EditText) findViewById(R.id.editId);
    }

    public void  eliminar(View view)
    {
        int id = Integer.parseInt(editId.getText().toString());
        Reserva reserva = new Reserva();
        reserva.setIdReserva(id);
        String regElim = helper.eliminarReserva(reserva);
        Toast.makeText(this, regElim, Toast.LENGTH_SHORT).show();
    }
}
