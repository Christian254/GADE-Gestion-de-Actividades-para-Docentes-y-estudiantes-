package sv.edu.ues.fia.gade.view;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.adapter.Adaptador;
import sv.edu.ues.fia.gade.adapter.UsersAdapter;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;
import sv.edu.ues.fia.gade.model.AccesoUsuario;
import sv.edu.ues.fia.gade.model.OpcionCrud;
import sv.edu.ues.fia.gade.model.Usuario;

public class UsersActivity extends AppCompatActivity {

    private ListView lista;
    private controlDB db;
    private Cursor usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        db = new controlDB(this);
        usuarios = db.getDataUsuarios();
        ArrayList<Adaptador> adap = new ArrayList<>();
        if(usuarios!=null && usuarios.getCount()>0){
            while (usuarios.moveToNext()){
                adap.add(new Adaptador(new Usuario(usuarios.getString(0),usuarios.getString(1),usuarios.getInt(2)),new AccesoUsuario(usuarios.getInt(4),usuarios.getString(3)),new OpcionCrud(usuarios.getString(6),usuarios.getInt(5),usuarios.getInt(7))));
            }
        }

        UsersAdapter adapter = new UsersAdapter(this,adap);
        View header = (View)getLayoutInflater().inflate(R.layout.list_header,null);
        lista = (ListView)findViewById(R.id.listaUser);
        lista.addHeaderView(header);
        lista.setAdapter(adapter);

    }
}
