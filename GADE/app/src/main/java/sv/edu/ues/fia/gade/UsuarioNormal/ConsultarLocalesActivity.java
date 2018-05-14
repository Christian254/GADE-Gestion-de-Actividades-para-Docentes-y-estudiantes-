package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ConsultarLocalesActivity extends Activity {

    private controlDB db;
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_locales);
        db = new controlDB(this);
        lista = (ListView)findViewById(R.id.listaLocales);
        Cursor c = db.getData("LOCAL");
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

