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
public class EstudianteActualizarFragment extends Fragment implements View.OnClickListener {
    private controlDB db;
    EditText edtCarnet, edtEscuela, edtNomEstudiante;
    Button btnLimpiar, btnActualizar;

    public EstudianteActualizarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_estudiante_actualizar, container, false);

        db = new controlDB(v.getContext());

        edtCarnet = (EditText) v.findViewById(R.id.carnetEst);
        edtEscuela = (EditText) v.findViewById(R.id.idEscuela);
        edtNomEstudiante = (EditText) v.findViewById(R.id.nomEstudiante);

        btnActualizar = (Button) v.findViewById(R.id.btnActualizarEstudianteDatos);
        btnActualizar.setOnClickListener(this);

        btnLimpiar = (Button) v.findViewById(R.id.btnLimpiarEstudianteDatos);
        btnLimpiar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnActualizarEstudianteDatos:
                String carnet = edtCarnet.getText().toString();
                String strIdEs = edtEscuela.getText().toString();
                String nombre = edtNomEstudiante.getText().toString();

                if(carnet.isEmpty() || strIdEs.isEmpty() || nombre.isEmpty()){
                    Toast.makeText(v.getContext(), "Los campos son obligatorios.", Toast.LENGTH_SHORT).show();
                }else{
                    int idEs = Integer.parseInt(strIdEs);
                    Cursor c = db.getDataEscuela(idEs);
                    if(c!=null && c.getCount()>0){
                        Alumno estudiante = new Alumno();
                        estudiante.setCarnet(carnet);
                        estudiante.setIdEscuela(idEs);
                        estudiante.setNombre(nombre);

                        String regAc = db.updateAlumno(estudiante);
                        Toast.makeText(v.getContext(), regAc, Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(v.getContext(), "EL registro de escuela, no existe.", Toast.LENGTH_SHORT).show();
                    }
                }

                break;

            case R.id.btnLimpiarEstudianteDatos:
                edtCarnet.setText("");
                edtEscuela.setText("");
                edtNomEstudiante.setText("");
                break;
        }
    }
}
