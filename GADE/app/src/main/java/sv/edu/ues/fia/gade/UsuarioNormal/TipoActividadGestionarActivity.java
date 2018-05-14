package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import sv.edu.ues.fia.gade.R;

public class TipoActividadGestionarActivity extends ListActivity
{
    String [] menu = {"Insertar Tipo Actividad","Eliminar Tipo Actividad","Actualizar Tipo Actividad","Consultar Tipo Actividad"};
    String [] activities = {"TipoActividadInsertarActivity","TipoActividadEliminarActivity","TipoActividadActualizarActivity","TipoActividadConsultarActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        //listView.setBackgroundColor(Color.rgb(200, 255, 155));
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        String nombreValue = activities[position];

        //l.getChildAt(position).setBackgroundColor(Color.rgb(128, 128, 0));

        try {
            Class<?> clase = Class.forName("sv.edu.ues.fia.gade.UsuarioNormal." + nombreValue);
            Intent inte = new Intent(this, clase);
            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
