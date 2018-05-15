package sv.edu.ues.fia.gade.view;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class VerUserActivity extends AppCompatActivity {

    private TextView tx,tx1;
    private ListView lista;
    private controlDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_user);
        db = new controlDB(this);
        tx=(TextView)findViewById(R.id.id_tx_user);
        tx1 = (TextView)findViewById(R.id.idTipoUser);
        String usuario = getIntent().getExtras().getString("id");
        tx.setText(usuario);
        tx1.setText("Tipo: "+getIntent().getExtras().getString("tipo"));
        lista =(ListView)findViewById(R.id.listaActividades);
        Cursor c = db.getDataUsuarios(usuario);
        ArrayList<String> datos = new ArrayList<String>();
        datos.add("\tDatos");
        if(c!=null && c.getCount()>0){
            while (c.moveToNext()){
                datos.add(c.getString(5)+": "+c.getString(6));
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        lista.setAdapter(adapter);
    }
}
