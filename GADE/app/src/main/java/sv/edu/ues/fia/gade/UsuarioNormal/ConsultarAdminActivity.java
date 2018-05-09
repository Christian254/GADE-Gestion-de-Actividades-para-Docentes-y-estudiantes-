package sv.edu.ues.fia.gade.UsuarioNormal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ConsultarAdminActivity extends AppCompatActivity {

    private controlDB db;
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_admin);
        db = new controlDB(this);
        lista = (ListView)findViewById(R.id.listaAdmins);
        Cursor c = db.getData("ADMINISTRADOR");
        ArrayList<String> datos = new ArrayList<String>();
        if(c!=null && c.getCount()>0){
            while (c.moveToNext()){
                datos.add(c.getString(0)+": "+c.getString(2));
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        lista.setAdapter(adapter);
    }
}
