package sv.edu.ues.fia.gade;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class IndexActivity extends ListActivity {
    String[] menu= {"Solicitar Local", "Consultar Reservas","Modificar Reserva", "Eliminar Reservas"};
    String[] valores={"SolicitarLocalActivity", "ConsultarReservaActivity","ModificarReservaActivity", "EliminarReservaActivity"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu));
    }

    @Override
    protected  void  onListItemClick(ListView listView, View view, int position, long id)
    {
        String nombreValue=valores[position];
        try {
            Class<?> clase = Class.forName("sv.edu.ues.fia.gade.UsuarioNormal."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
