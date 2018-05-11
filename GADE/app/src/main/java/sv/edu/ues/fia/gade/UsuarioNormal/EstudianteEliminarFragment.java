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

/**
 * A simple {@link Fragment} subclass.
 */
public class EstudianteEliminarFragment extends Fragment implements View.OnClickListener {
    private controlDB db;
    EditText edtCarnet;
    Button btnLimpiar, btnEliminar;

    public EstudianteEliminarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_estudiante_eliminar, container, false);

        db = new controlDB(getActivity());

        edtCarnet = (EditText) v.findViewById(R.id.carnetEst);

        btnEliminar = (Button) v.findViewById(R.id.btnEliminarEstudianteDatos);
        btnEliminar.setOnClickListener(this);
        btnLimpiar = (Button) v.findViewById(R.id.btnLimpiarEstudianteDatos);
        btnLimpiar.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEliminarEstudianteDatos:
                String carnet = edtCarnet.getText().toString();
                if(carnet.isEmpty()){
                    Toast.makeText(v.getContext(), "Digite carnet.", Toast.LENGTH_SHORT).show();
                }else{
                    String regEl = db.deleteAlumno(carnet);
                    Toast.makeText(v.getContext(),regEl,Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnLimpiarEstudianteDatos:
                edtCarnet.setText("");
                break;
        }
    }
}
