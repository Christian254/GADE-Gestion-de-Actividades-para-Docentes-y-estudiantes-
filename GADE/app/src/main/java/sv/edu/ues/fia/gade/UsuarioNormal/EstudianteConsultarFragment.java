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
import sv.edu.ues.fia.gade.clases.Alumno;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstudianteConsultarFragment extends Fragment implements View.OnClickListener {
    private controlDB bd;
    EditText edtCarnet, edtEscuela, edtNombreEst;
    Button btnLimpiar, btnConsultar;


    public EstudianteConsultarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_estudiante_consultar, container, false);

       bd = new controlDB(v.getContext());

       edtCarnet = (EditText) v.findViewById(R.id.carnetEst);
       edtEscuela = (EditText) v.findViewById(R.id.idEscuela);
       edtNombreEst = (EditText) v.findViewById(R.id.nomEstudiante);

       btnConsultar = (Button) v.findViewById(R.id.btnConsultarEstudianteDatos);
       btnConsultar.setOnClickListener(this);

       btnLimpiar = (Button) v.findViewById(R.id.btnLimpiarEstudianteDatos);
       btnLimpiar.setOnClickListener(this);

       return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnConsultarEstudianteDatos:
                String carnet = edtCarnet.getText().toString();

                if(carnet.isEmpty()){
                    Toast.makeText(v.getContext(), "Campo obligatorio.", Toast.LENGTH_SHORT).show();
                }else{
                    Alumno alumno = bd.consultarAlumno(carnet);
                    if(alumno != null){
                        edtEscuela.setText(String.valueOf(alumno.getIdEscuela()));
                        edtNombreEst.setText(alumno.getNombre());
                    }else{
                        Toast.makeText(v.getContext(), "No existe registro de alumno con carnet " + carnet + ".", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.btnLimpiarEstudianteDatos:
                edtCarnet.setText("");
                edtEscuela.setText("");
                edtNombreEst.setText("");
                break;

        }
    }
}
