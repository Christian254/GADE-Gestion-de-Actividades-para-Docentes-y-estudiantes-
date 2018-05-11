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
public class EscuelaInsertarFragment extends Fragment implements View.OnClickListener {
    private controlDB db;
    EditText editIdEscuela, editNomEscuela;
    Button btnLimpiar, btnInsertar;


    public EscuelaInsertarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_escuela_insertar, container, false);

        editIdEscuela = (EditText) v.findViewById(R.id.idEscuela);
        editNomEscuela = (EditText)v.findViewById(R.id.nomEscuela);
        btnLimpiar = (Button) v.findViewById(R.id.btnLimpiarEscuelaDatos);
        btnLimpiar.setOnClickListener(this);
        btnInsertar = (Button) v.findViewById(R.id.btnInsertarEscuelaDatos);
        btnInsertar.setOnClickListener(this);
        db = new controlDB(getActivity());

        return v;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsertarEscuelaDatos:
                if(editIdEscuela.getText().toString().isEmpty() || editNomEscuela.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), "Campos vacíos, obligatorio rellenarlos.", Toast.LENGTH_SHORT).show();
                }else{
                    int idEscuela = Integer.valueOf(editIdEscuela.getText().toString());
                    String nomEscuela = editNomEscuela.getText().toString();
                    Escuela escuela = new Escuela();
                    escuela.setIdentificadorEscuela(idEscuela);
                    escuela.setNombreEscuela(nomEscuela);
                    String regInsertados=db.insertEscuela(escuela);
                    Toast.makeText(v.getContext(), regInsertados, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnLimpiarEscuelaDatos:
                editIdEscuela.setText("");
                editNomEscuela.setText("");
            break;

        }
    }
}
