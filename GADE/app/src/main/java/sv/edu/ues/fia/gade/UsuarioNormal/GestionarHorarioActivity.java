package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class GestionarHorarioActivity extends ListActivity {
    String[] menu = {"Insertar Horario", "Eliminar Horario", "Consultar Horario",
            "Actualizar Horario"};
    String[]
            activities = {"InsertarHorarioActivity", "EliminarHorarioActivity", "ConsultarHorarioActivity",
            "ActualizarHorarioActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(0, 255, 255));
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String nombreValue = activities[position];


        try {
            Class<?> clase = Class.forName("sv.edu.ues.fia.gade.UsuarioNormal." + nombreValue);
            Intent inte = new Intent(this, clase);
            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
