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
public class ParticipacionActualizarFragment extends Fragment implements View.OnClickListener {
    private controlDB db;
    EditText edtIDAct, edtCarnet, edtVal, edtCom;
    Button btnAc, btnLim;

    public ParticipacionActualizarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_participacion_actualizar, container, false);

        db = new controlDB(v.getContext());

        edtIDAct = (EditText) v.findViewById(R.id.idAct);
        edtCarnet = (EditText) v.findViewById(R.id.carnetEst);
        edtVal = (EditText) v.findViewById(R.id.valoracionPar);
        edtCom = (EditText) v.findViewById(R.id.comentarioPar);

        btnAc = (Button) v.findViewById(R.id.btnActualizarParticipacionDatos) ;
        btnAc.setOnClickListener(this);

        btnLim = (Button) v.findViewById(R.id.btnLimpiarParticipacionDatos);
        btnLim.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnActualizarParticipacionDatos:
                String idActStr = edtIDAct.getText().toString();
                String carnet = edtCarnet.getText().toString();
                String valStr = edtVal.getText().toString();
                String comentario = edtCom.getText().toString();

                if(idActStr.isEmpty() || carnet.isEmpty() || valStr.isEmpty() || comentario.isEmpty()){
                    Toast.makeText(v.getContext(),"Rellenar los campos.", Toast.LENGTH_SHORT).show();
                }else{
                    int val = Integer.valueOf(valStr);
                    int idAct = Integer.valueOf(idActStr);
                    Cursor cCarnet = db.getDataAlumno(carnet);
                    Cursor cAct = db.getDataActividad(idAct);

                    if(cCarnet!=null && cCarnet.getCount()>0 && cAct != null && cAct.getCount()>0){
                        Participacion participacion = new Participacion();
                        participacion.setIdActividad(idAct);
                        participacion.setCarnet(carnet);
                        participacion.setValoracion(val);
                        participacion.setComentario(comentario);

                        String regAct = db.updateParticipacion(participacion);
                        Toast.makeText(v.getContext(),regAct,Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(v.getContext(),"Referencia a ID ACTIVIDAD y CARNET, no existe.", Toast.LENGTH_SHORT).show();
                    }
                }

                break;

            case R.id.btnLimpiarParticipacionDatos:
                edtIDAct.setText("");
                edtCarnet.setText("");
                edtVal.setText("");
                edtCom.setText("");
                break;
        }
    }
}
