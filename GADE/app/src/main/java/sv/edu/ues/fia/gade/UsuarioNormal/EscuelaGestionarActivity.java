package sv.edu.ues.fia.gade.UsuarioNormal;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sv.edu.ues.fia.gade.R;

public class EscuelaGestionarActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnEscuelaIns, btnEscuelaAc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_gestionar);
        btnEscuelaIns = (Button) findViewById(R.id.btnInsertarEscuela);
        btnEscuelaAc = (Button) findViewById(R.id.btnActualizarEscuela);


        btnEscuelaIns.setOnClickListener(this);
        btnEscuelaAc.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsertarEscuela:
                EscuelaInsertarFragment inEsFrag = new EscuelaInsertarFragment();
                FragmentTransaction transactionIn = getSupportFragmentManager().beginTransaction();
                transactionIn.replace(R.id.contenedorFragmentsEscuela, inEsFrag);
                transactionIn.commit();
                break;
            case R.id.btnActualizarEscuela:
                EscuelaActualizarFragment acEsFrag = new EscuelaActualizarFragment();
                FragmentTransaction transactionAc = getSupportFragmentManager().beginTransaction();
                transactionAc.replace(R.id.contenedorFragmentsEscuela, acEsFrag);
                transactionAc.commit();
                break;
        }
    }
}
