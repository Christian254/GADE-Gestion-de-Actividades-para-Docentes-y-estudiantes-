package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import sv.edu.ues.fia.gade.R;

public class GestionarReservaActivity extends ListActivity {

    String[] menu={"Consultar Reserva","Modificar Reserva","Eliminar Reserva"};
    String[] activities={"ConsultarReservaActivity","ModificarReservaActivity","EliminarReservaActivity"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        if(position!=3){
            String nombreValue=activities[position];
            try{
                Class<?> clase=Class.forName("sv.edu.ues.fia.gade.UsuarioNormal."+nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

}
