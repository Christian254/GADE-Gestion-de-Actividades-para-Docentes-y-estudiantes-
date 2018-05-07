package sv.edu.ues.fia.gade.view;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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
        usuarios = db.getData("USUARIO");
        ArrayList<Usuario> adap = new ArrayList<>();
        if(usuarios!=null && usuarios.getCount()>0){
            while (usuarios.moveToNext()){
                adap.add(new Usuario(usuarios.getString(0),usuarios.getString(1),usuarios.getInt(2)));
            }
        }

        UsersAdapter adapter = new UsersAdapter(this,adap);
        View header = (View)getLayoutInflater().inflate(R.layout.list_header,null);
        lista = (ListView)findViewById(R.id.listaUser);
        lista.addHeaderView(header);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //verUser("1","d",2);
                TextView t = (TextView)view.findViewById(R.id.tx);
                TextView s = (TextView)view.findViewById(R.id.tx2);
                String tip=null;
                int x = Integer.parseInt(s.getText().toString());
                if(x==2){
                    tip="Administrador";
                }else{
                    tip="Usuario normal";
                }
                verUser(t.getHint().toString(),t.getText().toString(),tip);
            }
        });

    }


    public void verUser(String id,String clave,String tipo){
        Intent i = new Intent(this, VerUserActivity.class);
        i.putExtra("id",id);
        i.putExtra("clave",clave);
        i.putExtra("tipo",tipo);
        startActivity(i);
//        finish();
    }



}



