package sv.edu.ues.fia.gade.UsuarioNormal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sv.edu.ues.fia.gade.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstudianteEliminarFragment extends Fragment {


    public EstudianteEliminarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estudiante_eliminar, container, false);
    }

}