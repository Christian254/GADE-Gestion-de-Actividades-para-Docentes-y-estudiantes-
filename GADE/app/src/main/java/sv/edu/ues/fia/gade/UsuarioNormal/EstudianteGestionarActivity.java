package sv.edu.ues.fia.gade.UsuarioNormal;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sv.edu.ues.fia.gade.R;

public class EstudianteGestionarActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnInEst, btnAcEst, btnConEst, btnElEst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_gestionar);

        btnInEst = (Button) findViewById(R.id.btnInsertarEstudiante);
        btnAcEst = (Button) findViewById(R.id.btnActualizarEstudiante);
        btnConEst = (Button) findViewById(R.id.btnConsultarEstudiante);
        btnElEst = (Button) findViewById(R.id.btnEliminarEstudiante);

        btnInEst.setOnClickListener(this);
        btnAcEst.setOnClickListener(this);
        btnConEst.setOnClickListener(this);
        btnElEst.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsertarEstudiante:
                EstudianteInsertarFragment estInsFrag = new EstudianteInsertarFragment();
                FragmentTransaction transactionIn = getSupportFragmentManager().beginTransaction();
                transactionIn.replace(R.id.contenedorFragmentsEstudiante, estInsFrag);
                transactionIn.commit();
                break;

            case R.id.btnActualizarEstudiante:
                EstudianteActualizarFragment estAcFrag = new EstudianteActualizarFragment();
                FragmentTransaction transactionAc = getSupportFragmentManager().beginTransaction();
                transactionAc.replace(R.id.contenedorFragmentsEstudiante, estAcFrag);
                transactionAc.commit();
            break;

            case R.id.btnConsultarEstudiante:
                EstudianteConsultarFragment estConFrag = new EstudianteConsultarFragment();
                FragmentTransaction transactionCon = getSupportFragmentManager().beginTransaction();
                transactionCon.replace(R.id.contenedorFragmentsEstudiante, estConFrag);
                transactionCon.commit();
            break;

            case R.id.btnEliminarEstudiante:
                EstudianteEliminarFragment estEliFrag = new EstudianteEliminarFragment();
                FragmentTransaction transactionEl = getSupportFragmentManager().beginTransaction();
                transactionEl.replace(R.id.contenedorFragmentsEstudiante, estEliFrag);
                transactionEl.commit();
            break;
        }
    }
}
