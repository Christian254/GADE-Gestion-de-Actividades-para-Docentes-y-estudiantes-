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
public class EscuelaEliminarFragment extends Fragment implements View.OnClickListener {

    private controlDB db;
    EditText editIdEscuela;
    Button btnLimpiar, btnEliminar;


    public EscuelaEliminarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_escuela_eliminar, container, false);

        editIdEscuela = (EditText) v.findViewById(R.id.idEscuela);
        btnLimpiar = (Button) v.findViewById(R.id.btnLimpiarEscuelaDatos);
        btnEliminar = (Button) v.findViewById(R.id.btnEliminarEscuelaDatos);
        db = new controlDB(getActivity());

        btnEliminar.setOnClickListener(this);
        btnLimpiar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEliminarEscuelaDatos:
                if(editIdEscuela.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), "Campo obligatorio a rellenar.", Toast.LENGTH_SHORT).show();
                }else{
                    String regElim;
                    Escuela escuela = new Escuela();
                    escuela.setIdentificadorEscuela(Integer.valueOf(editIdEscuela.getText().toString()));
                    regElim = db.deleteEscuela(escuela);
                    Toast.makeText(v.getContext(), regElim, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnLimpiarEscuelaDatos:
                editIdEscuela.setText("");
                break;
        }
    }
}
