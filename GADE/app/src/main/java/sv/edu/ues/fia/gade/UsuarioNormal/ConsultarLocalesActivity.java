package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.adapter.AdapterLocal;
import sv.edu.ues.fia.gade.clases.Local;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;


public class ConsultarLocalesActivity extends Activity {


    private ListView lista;
    private controlDB db;
    private Cursor locales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_locales);
        db = new controlDB(this);
        locales = db.getData("LOCAL");
        ArrayList<Local> adap = new ArrayList<>();
        if(locales!=null && locales.getCount()>0){
            while (locales.moveToNext()){
                adap.add(new Local(locales.getInt(0),locales.getString(2),locales.getInt(3)));
            }
        }

        AdapterLocal adapter = new AdapterLocal(this,adap);
        View header = (View)getLayoutInflater().inflate(R.layout.list_header_local,null);
        lista = (ListView)findViewById(R.id.listaLocales);
        lista.addHeaderView(header);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = (TextView)view.findViewById(R.id.txlocal2);
                String ids = t.getHint().toString();
                verLocal(ids);
            }
        });

    }

    private void verLocal(String ids) {
        //aqui la actividad que queres lanzar
        Intent i = new Intent(this, MostrarCicloActivity.class);
        i.putExtra("id",ids);
        startActivity(i);
        //finish();
    }

}

