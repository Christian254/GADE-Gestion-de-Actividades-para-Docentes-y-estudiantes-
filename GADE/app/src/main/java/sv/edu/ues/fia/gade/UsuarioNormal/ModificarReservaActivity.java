package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Reserva;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ModificarReservaActivity extends Activity {
    private controlDB helper;
    private EditText editRes, editEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_reserva);
        helper = new controlDB(this);
        editRes = (EditText) findViewById(R.id.editId);
        editEstado = (EditText) findViewById(R.id.editEstadoModificar);
    }
    public void modificarReserva(View v)
    {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(Integer.parseInt(editRes.getText().toString()));
        reserva.setEstado(Integer.parseInt(editEstado.getText().toString()));
        String regAct = helper.modificarReserva(reserva);
        Toast.makeText(this,regAct,Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v){
        editRes.setText("");
        editEstado.setText("");
    }

}
