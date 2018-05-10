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
public class EscuelaConsultarFragment extends Fragment implements View.OnClickListener {

    private controlDB db;
    EditText editIdEscuela, editNomEscuela;
    Button btnLimpiar, btnConsultar;

    public EscuelaConsultarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_escuela_consultar, container, false);

        editIdEscuela = (EditText) v.findViewById(R.id.idEscuela);
        editNomEscuela = (EditText)v.findViewById(R.id.nomEscuela);
        btnLimpiar = (Button) v.findViewById(R.id.btnLimpiarEscuelaDatos);
        btnLimpiar.setOnClickListener(this);
        btnConsultar= (Button) v.findViewById(R.id.btnConsultarEscuelaDatos);
        btnConsultar.setOnClickListener(this);
        db = new controlDB(getActivity());

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnConsultarEscuelaDatos:
                if(editIdEscuela.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), "Campo vacío, rellenar antes de enviar.", Toast.LENGTH_SHORT).show();
                }else{
                    Escuela escuela = db.consultarEscuela(Integer.valueOf(editIdEscuela.getText().toString()));
                    if (escuela == null){
                        Toast.makeText(v.getContext(), "No existe el registro de escuela", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        editNomEscuela.setText(escuela.getNombreEscuela());
                    }
                }

                break;
            case R.id.btnLimpiarEscuelaDatos:
                editIdEscuela.setText("");
                editNomEscuela.setText("");
                break;

        }
    }
}
