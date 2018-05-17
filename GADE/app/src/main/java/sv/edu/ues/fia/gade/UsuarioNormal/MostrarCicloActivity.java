package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.adapter.AdapterLH;
import sv.edu.ues.fia.gade.adapter.AdapterLocal;
import sv.edu.ues.fia.gade.adapter.ArrayAdapterLH;
import sv.edu.ues.fia.gade.clases.Local;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class MostrarCicloActivity extends Activity {


    private ListView lista;
    private controlDB db;
    private Cursor ciclos;
    private String idLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ciclo);

        db = new controlDB(this);

        db.insertarCiclo(1,"Ciclo I");
        db.insertarCiclo(2,"Ciclo II");


        ciclos = db.getData("CICLO");
        idLocal = getIntent().getExtras().getString("id");
        ArrayList<AdapterLH> adap = new ArrayList<AdapterLH>();

        if(ciclos!=null && ciclos.getCount()>0){
            while (ciclos.moveToNext()){
                adap.add(new AdapterLH(ciclos.getInt(0),ciclos.getString(1)));
            }
        }

        ArrayAdapterLH adapter = new ArrayAdapterLH(this,adap);
        lista = (ListView)findViewById(R.id.listaCiclo);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = (TextView)view.findViewById(R.id.txl_h_c);
                String ids = t.getHint().toString();
                verHorario(ids);
            }
        });

    }

    private void verHorario(String ids) {
        //aqui la actividad que queres lanzar
        if(idLocal!=null){
            Intent i = new Intent(this, MostrarHorarioActivity.class);
            i.putExtra("idLocal",idLocal);
            i.putExtra("idCiclo",ids);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this,"Error inseperado trate de nuevo",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}


