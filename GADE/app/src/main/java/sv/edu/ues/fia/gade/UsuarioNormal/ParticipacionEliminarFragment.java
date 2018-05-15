package sv.edu.ues.fia.gade.UsuarioNormal;


import android.database.Cursor;
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
public class ParticipacionEliminarFragment extends Fragment implements View.OnClickListener {

    private controlDB db;
    EditText edtIdAct, edtCarnet;
    Button btnLimpiar, btnEliminar;

    public ParticipacionEliminarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_participacion_eliminar, container, false);

        db = new controlDB(getActivity());

        edtIdAct = (EditText) v.findViewById(R.id.idAct);
        edtCarnet = (EditText) v.findViewById(R.id.carnetEst);

        btnEliminar = (Button) v.findViewById(R.id.btnEliminarParticipacionDatos);
        btnEliminar.setOnClickListener(this);

        btnLimpiar = (Button) v.findViewById(R.id.btnLimpiarParticipacionDatos);
        btnLimpiar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEliminarParticipacionDatos:
                String idActStr = edtIdAct.getText().toString();
                String carnet = edtCarnet.getText().toString();

                if(idActStr.isEmpty() || carnet.isEmpty()){
                    Toast.makeText(v.getContext(), "Rellene campos vacíos",Toast.LENGTH_SHORT).show();
                }else{
                    int idAct = Integer.valueOf(idActStr);
                    String regDel = db.deleteParticipacion(idAct, carnet);
                    Toast.makeText(v.getContext(), regDel,Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnLimpiarParticipacionDatos:
                edtIdAct.setText("");
                edtCarnet.setText("");
                break;
        }
    }
}
