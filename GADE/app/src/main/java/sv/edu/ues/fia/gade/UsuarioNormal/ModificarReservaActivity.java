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

public class ModificarReservaActivity extends Activity {
    private controlDB helper;
    private EditText editRes, editAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_reserva);
        helper = new controlDB(this);
        editRes = (EditText) findViewById(R.id.editId);
        editAct = (EditText) findViewById(R.id.editAct);
    }
    public void modificarReserva(View v)
    {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(Integer.parseInt(editRes.getText().toString()));
        reserva.setIdActividad(Integer.parseInt(editAct.getText().toString()));
        String regAct = helper.modificarReserva(reserva);
        Toast.makeText(this,regAct,Toast.LENGTH_SHORT).show();
    }

}
