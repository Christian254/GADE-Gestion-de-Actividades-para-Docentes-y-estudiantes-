package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class MostrarCicloActivity extends ListActivity {

    private controlDB db;
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new controlDB(this);
        ListView listView = getListView();

        Cursor c = db.getDataCiclo();
        ArrayList<String> datos = new ArrayList<String>();
        //borras esta parte xq solo es para demostracion
        String id =getIntent().getStringExtra("id");
        datos.add("Id introducido en el activity anterior es: "+id);
        datos.add("REcordas borrar esta parte que es solo prueba");
        //Hasta aqui borras kike
        if(c!=null && c.getCount()>0){
            while (c.moveToNext()){
                datos.add(c.getString(1));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        setListAdapter(adapter);
    }
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        try {
            Class<?> clase = Class.forName("sv.edu.ues.fia.gade.UsuarioNormal.MostrarHorarioActivity");
            Intent inte = new Intent(this, clase);
            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
