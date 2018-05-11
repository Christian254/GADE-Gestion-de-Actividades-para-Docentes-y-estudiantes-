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
import sv.edu.ues.fia.gade.clases.Alumno;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstudianteInsertarFragment extends Fragment implements View.OnClickListener {
    private controlDB db;
    EditText edtCarnet, edtIdEsc, edtNomEst;
    Button btnLimpiar, btnInsertar;

    public EstudianteInsertarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_estudiante_insertar, container, false);

        db = new controlDB(getActivity());
        edtCarnet = (EditText) v.findViewById(R.id.carnetEst);
        edtIdEsc = (EditText) v.findViewById(R.id.idEscuela);
        edtNomEst = (EditText) v.findViewById(R.id.nomEstudiante);

        btnInsertar = (Button) v.findViewById(R.id.btnInsertarEstudianteDatos);
        btnLimpiar = (Button) v.findViewById(R.id.btnLimpiarEstudianteDatos);

        btnInsertar.setOnClickListener(this);
        btnLimpiar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsertarEstudianteDatos:
                String carnet = edtCarnet.getText().toString();
                String codEsc = edtIdEsc.getText().toString();
                String nombreEst = edtNomEst.getText().toString();

                if(carnet.isEmpty() || codEsc.isEmpty() || nombreEst.isEmpty()){
                    Toast.makeText(v.getContext(),"Rellenar los campos.", Toast.LENGTH_SHORT).show();
                }else{
                    int codEscuela = Integer.valueOf(codEsc);
                    Cursor c = db.getDataEscuela(codEscuela);

                    if(c!=null && c.getCount()>0){
                        Alumno estudiante = new Alumno();
                        estudiante.setCarnet(carnet);
                        estudiante.setIdEscuela(codEscuela);
                        estudiante.setNombre(nombreEst);

                        String regInsertado = db.insertAlum(estudiante);
                        Toast.makeText(v.getContext(), regInsertado, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(v.getContext(),"Escuela no registrada.",Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btnLimpiarEstudianteDatos:
                edtCarnet.setText("");
                edtIdEsc.setText("");
                edtNomEst.setText("");
                break;
        }

    }
}
