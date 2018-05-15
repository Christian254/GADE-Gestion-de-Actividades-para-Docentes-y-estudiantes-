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
import sv.edu.ues.fia.gade.clases.Participacion;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipacionConsultarFragment extends Fragment implements View.OnClickListener {
    private controlDB db;
    EditText edtIdAct, edtCarnet, edtVal, edtCom;
    Button edtLimpiar, edtConsutar;

    public ParticipacionConsultarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_participacion_consultar, container, false);

       db = new controlDB(v.getContext());

       edtIdAct = (EditText) v.findViewById(R.id.idAct);
       edtCarnet = (EditText) v.findViewById(R.id.carnetEst);
       edtVal = (EditText) v.findViewById(R.id.valoracionPar);
       edtCom = (EditText) v.findViewById(R.id.comentarioPar);

       edtConsutar = (Button) v.findViewById(R.id.btnConsultarParticipacionDatos);
       edtConsutar.setOnClickListener(this);

       edtLimpiar = (Button) v.findViewById(R.id.btnLimpiarParticipacionDatos);
       edtLimpiar.setOnClickListener(this);

       return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnConsultarParticipacionDatos:
                String idActStr = edtIdAct.getText().toString();
                String carnet = edtCarnet.getText().toString();

                if(idActStr.isEmpty() || carnet.isEmpty()){
                    Toast.makeText(v.getContext(), "Rellene campos vacíos",Toast.LENGTH_SHORT).show();
                }else{
                    int idAct = Integer.valueOf(idActStr);
                    Cursor cCarnet = db.getDataAlumno(carnet);

                    if(cCarnet != null && cCarnet.getCount()>0){
                        Participacion part = db.consultarParticipacion(idAct, carnet);
                        if(part!= null){
                            edtVal.setText(String.valueOf(part.getValoracion()));
                            edtCom.setText(part.getComentario());
                        }else{
                            Toast.makeText(v.getContext(),"No existe la participación.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(v.getContext(),"Referencia a ID ACTIVIDAD y CARNET, no existe.", Toast.LENGTH_SHORT).show();
                    }
                }

                break;

            case R.id.btnLimpiarParticipacionDatos:
                edtIdAct.setText("");
                edtCarnet.setText("");
                edtVal.setText("");
                edtCom.setText("");
                break;
        }
    }
}
