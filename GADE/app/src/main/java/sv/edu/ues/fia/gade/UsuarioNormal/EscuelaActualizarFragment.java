package sv.edu.ues.fia.gade.UsuarioNormal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;
import sv.edu.ues.fia.gade.model.Escuela;

/**
 * A simple {@link Fragment} subclass.
 */
public class EscuelaActualizarFragment extends Fragment implements View.OnClickListener {
    private controlDB db;
    EditText edtIdEs, edtNomEs;
    Button btnLimpiar, btnAct;


    public EscuelaActualizarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_escuela_actualizar, container, false);

        db = new controlDB(v.getContext());
        edtIdEs = (EditText) v.findViewById(R.id.idEscuela);
        edtNomEs = (EditText) v.findViewById(R.id.nomEscuela);

        btnAct = (Button) v.findViewById(R.id.btnActualizarEscuelaDatos);
        btnAct.setOnClickListener(this);

        btnLimpiar = (Button) v.findViewById(R.id.btnLimpiarEscuelaDatos);
        btnLimpiar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnActualizarEscuelaDatos:

                if(edtIdEs.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), "Campos vacíos, rellenar antes de enviar.", Toast.LENGTH_SHORT).show();
                }else{
                    Escuela escuela = new Escuela();
                    escuela.setIdentificadorEscuela(Integer.valueOf(edtIdEs.getText().toString()));
                    escuela.setNombreEscuela(edtNomEs.getText().toString());
                    String regAc = db.updateEscuela(escuela);
                    Toast.makeText(v.getContext(),regAc,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnLimpiarEscuelaDatos:
                edtIdEs.setText("");
                edtNomEs.setText("");
                break;
        }
    }
}
