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

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Horario;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class MostrarHorarioActivity extends ListActivity {

    private controlDB db;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new controlDB(this);

        Cursor c = db.getDataHorario();
        ArrayList<String> datos = new ArrayList<>();
        //borras esta parte xq solo es para demostracion
        //Hasta aqui borras kike
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {

                datos.add(c.getInt(0)+ " " + c.getString(1)+" "+ c.getString(2)+"-"+c.getString(3));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        setListAdapter(adapter);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        try {
            Class<?> clase = Class.forName("sv.edu.ues.fia.gade.UsuarioNormal.SolicitarLocalActivity");
            Intent inte = new Intent(this, clase);


            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}