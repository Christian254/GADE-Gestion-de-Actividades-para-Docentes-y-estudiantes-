/*package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Horario;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class MostrarHorarioActivity extends Activity {
    private ArrayList<Horario> horario= new ArrayList<>();
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_horario);
        listView = (ListView) findViewById(R.id.ListConsulta);


        mostrarLista();
    }


    public void mostrarLista(){
        controlDB helper=new controlDB(this);
        if (helper!=null){
            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM HORARIO",null);
            if (cursor.moveToFirst()){
                do {
                    horario.add(new Horario(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
                }while (cursor.moveToNext());
            }
        }

        String[] arreglo=new String[horario.size()];
        for (int i=0;i<arreglo.length;i++){
            arreglo[i]=horario.get(i).getDia()+"    "+horario.get(i).getHorarioDesde()+"-"+horario.get(i).getHorarioHasta();
        }

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(MostrarHorarioActivity.this,android.R.layout.simple_list_item_1,arreglo);
        listView.setAdapter(adaptador);

    }
    protected void onListItemClick(ListView l, View v, int position, long id) {


        try {

            Class<?> clase = Class.forName("sv.edu.ues.fia.gade.UsuarioNormal.SolicitarLocalActivity");
            Intent inte = new Intent(this, clase);


            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

*/
package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.adapter.AdapterLH;
import sv.edu.ues.fia.gade.adapter.ArrayAdapterLH;
import sv.edu.ues.fia.gade.clases.Horario;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class MostrarHorarioActivity extends Activity {


    private ListView lista;
    private controlDB db;
    private Cursor horario;
    private String idLocal,idCiclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_horario);

        db = new controlDB(this);

        db.insertar(new Horario(1,"Lunes","8:00","9:45"));
        db.insertar(new Horario(2,"Martes","9:50","9:50"));
        db.insertar(new Horario(3,"Miercoles","11:35","13:15"));

        horario = db.getData("HORARIO");
        idLocal = getIntent().getExtras().getString("idLocal");
        idCiclo = getIntent().getExtras().getString("idCiclo");

        ArrayList<AdapterLH> adap = new ArrayList<AdapterLH>();

        if(horario!=null && horario.getCount()>0){
            while (horario.moveToNext()){
                String h = "Dia: "+horario.getString(1) + " Desde: "+horario.getString(2)+" Hasta: "+ horario.getString(3);
                adap.add(new AdapterLH(horario.getInt(0),h));
            }
        }

        ArrayAdapterLH adapter = new ArrayAdapterLH(this,adap);
        lista = (ListView)findViewById(R.id.ListConsulta);
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
        if(idLocal!=null && idCiclo!=null){
            Intent i = new Intent(this,SolicitarLocalActivity.class);
            i.putExtra("idLocal",idLocal);
            i.putExtra("idCiclo",idCiclo);
            i.putExtra("idHorario",ids);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this,"Error inseperado trate de nuevo",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
