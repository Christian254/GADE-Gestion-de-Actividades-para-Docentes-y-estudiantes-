package sv.edu.ues.fia.gade.UsuarioNormal;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sv.edu.ues.fia.gade.R;

public class ParticipacionGestionarActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnInsertarP, btnConsultarP, btnEliminarP, btnActualizarP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participacion_gestionar);

        btnActualizarP = (Button) findViewById(R.id.btnActualizarParticipacion);
        btnActualizarP.setOnClickListener(this);
        btnConsultarP = (Button) findViewById(R.id.btnConsultarParticipacion);
        btnConsultarP.setOnClickListener(this);
        btnEliminarP = (Button) findViewById(R.id.btnEliminarParticipacion);
        btnEliminarP.setOnClickListener(this);
        btnInsertarP = (Button) findViewById(R.id.btnInsertarParticipacion);
        btnInsertarP.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnActualizarParticipacion:
                ParticipacionActualizarFragment parAcFrag = new ParticipacionActualizarFragment();
                FragmentTransaction transactionAc = getSupportFragmentManager().beginTransaction();
                transactionAc.replace(R.id.contenedorFragmentsParticipacion, parAcFrag);
                transactionAc.commit();
                break;
            case R.id.btnConsultarParticipacion:
                ParticipacionConsultarFragment parConFrag = new ParticipacionConsultarFragment();
                FragmentTransaction transactionCon = getSupportFragmentManager().beginTransaction();
                transactionCon.replace(R.id.contenedorFragmentsParticipacion, parConFrag);
                transactionCon.commit();
                break;
            case R.id.btnEliminarParticipacion:
                ParticipacionEliminarFragment parElFrag = new ParticipacionEliminarFragment();
                FragmentTransaction transactionEl = getSupportFragmentManager().beginTransaction();
                transactionEl.replace(R.id.contenedorFragmentsParticipacion, parElFrag);
                transactionEl.commit();
                break;
            case R.id.btnInsertarParticipacion:
                ParticipacionInsertarFragment parInsFrag = new ParticipacionInsertarFragment();
                FragmentTransaction transactionIns = getSupportFragmentManager().beginTransaction();
                transactionIns.replace(R.id.contenedorFragmentsParticipacion, parInsFrag);
                transactionIns.commit();
                break;



        }
    }
}
