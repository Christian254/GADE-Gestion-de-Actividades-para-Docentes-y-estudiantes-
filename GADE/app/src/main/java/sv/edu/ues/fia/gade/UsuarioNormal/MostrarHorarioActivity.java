package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
        listView=(ListView) findViewById(R.id.ListConsulta);
        mostrarLista();
    }
    public void mostrarLista(){
        controlDB helper=new controlDB(this);
        if (helper!=null){
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM HORARIO",null);
            if (cursor.moveToFirst()){
                do {
                    horario.add(new Horario(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
                }while (cursor.moveToNext());
            }
        }

        String[] arreglo=new String[horario.size()];
        for (int i=0;i<arreglo.length;i++){
            arreglo[i]=horario.get(i).getHorarioDesde()+"-"+horario.get(i).getHorarioHasta();
        }
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(MostrarHorarioActivity.this,android.R.layout.simple_list_item_1,arreglo);
        listView.setAdapter(adaptador);

    }
}