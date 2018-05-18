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
    private String[] menu;
    private String[] valores;
    int t=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String tipo = getIntent().getExtras().getString("tipo");
        t = Integer.parseInt(tipo.toString().trim());
        cargarMenu(t);

        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu));
    }
    /*Metodo para poner si x==1 es usuario normal y si x==2 usuaio administrador
    *Por facilidad lo deje asi repetir lo que podra hacer un usuario normal y administrador
    **/
    private void cargarMenu(int t) {
        if(t==2){ //si es administrador podra:
            String[] menu1= {"Gestionar Escuela", "Gestionar Estudiante", "Gestionar Participación" ,"Gestionar Reserva","Gestionar Administrador","Gestionar Local","Gestionar Horario","Gestionar Docente","Gestionar Actividad","Gestionar Tipo Actividad"};
            String[] valores1={"EscuelaGestionarActivity", "EstudianteGestionarActivity", "ParticipacionGestionarActivity" , "GestionarReservaActivity","GestionarAdminActivity","GestionarLocalActivity","GestionarHorarioActivity","DocenteGestionarActivity","ActividadGestionarActivity","TipoActividadGestionarActivity"};
            this.menu=menu1;
            this.valores=valores1;
        }else{
            String[] menu1= {"Consultar Reservas", "Eliminar Reservas","Consultar administradores","Consultar locales"};
            String[] valores1={"ConsultarReservaActivity", "EliminarReservaActivity","ConsultarAdminActivity","ConsultarLocalesActivity"};
            this.menu=menu1;
            this.valores=valores1;
        }
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
