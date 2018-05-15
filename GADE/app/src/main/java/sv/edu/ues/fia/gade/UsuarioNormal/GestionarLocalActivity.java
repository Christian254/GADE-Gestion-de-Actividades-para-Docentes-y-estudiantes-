package sv.edu.ues.fia.gade.UsuarioNormal;

import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.adapter.SpinnerAdminAdapter;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;
import sv.edu.ues.fia.gade.model.Escuela;

public class GestionarLocalActivity extends AppCompatActivity {

    TextInputEditText txt1,txt2,txt3,txt4;
    TextView esc;
    private controlDB db;
    private static String antiguoNombre;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_local);
        txt1= (TextInputEditText)findViewById(R.id.idlocal1);
        txt2= (TextInputEditText)findViewById(R.id.nomLocal);
        txt3= (TextInputEditText)findViewById(R.id.idAdmin2);
        txt4= (TextInputEditText)findViewById(R.id.idCupo);
        db = new controlDB(this);
        List<Escuela> admin = new ArrayList<Escuela>();
        Cursor c = db.getData("ADMINISTRADOR");
        if(c!=null && c.getCount()>0){
            while (c.moveToNext()){
                int id = c.getInt(0);
                String nombreEsc = c.getString(2);
                admin.add(new Escuela(id,nombreEsc));
            }
        }else{
            admin.add(new Escuela(0,"none"));
        }

        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setAdapter(new SpinnerAdminAdapter(this,admin));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                int idEsc = ((Escuela) adapterView.getItemAtPosition(position)).getIdentificadorEscuela();
                txt3.setText(String.valueOf(idEsc));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //nothing
            }
        });


        antiguoNombre=" ";
    }

    public void buscar(View view) {
        try {
            String id = txt1.getText().toString().trim();
            String nombre = txt2.getText().toString();
            String idAdmin = txt3.getText().toString().trim();
            String cupomax = txt4.getText().toString().trim();
            antiguoNombre=nombre;
            if(id.isEmpty()){
                Toast.makeText(this,"Digite el numero del local",Toast.LENGTH_SHORT).show();
            }else{
                Cursor c = db.getDataLocal(Integer.parseInt(id));
                String nom=null;
                int admni=0,cupo=0;
                if(c!=null && c.getCount()>0){
                    while (c.moveToNext()){
                        admni=c.getInt(1);
                        nom=c.getString(2);
                        cupo=c.getInt(3);
                    }
                }else{
                    Toast.makeText(this,"Lo siento no existe ese Local",Toast.LENGTH_SHORT).show();
                    return;
                }
                txt2.setText(nom);
                txt3.setText(String.valueOf(admni));
                txt4.setText(String.valueOf(cupo));
            }
        }catch (Exception e){
            Toast.makeText(this,"Lo siento ocurrio un erro inesperado",Toast.LENGTH_SHORT).show();
        }
    }

    public void guardar(View view) {
        try {
            String id = txt1.getText().toString().trim();
            String nombre = txt2.getText().toString().trim();
            String idAdmin = txt3.getText().toString().trim();
            String cupomax = txt4.getText().toString().trim();
            if(id.isEmpty() || nombre.isEmpty() || idAdmin.isEmpty() || cupomax.isEmpty()){
                Toast.makeText(this,"Digite correctamente los datos",Toast.LENGTH_SHORT).show();
            }else{
                int idEsc=0;
                idEsc = Integer.parseInt(idAdmin);
                Cursor c = db.getDataAdministrador(idEsc);
                if(c!=null && c.getCount()>0){
                    while (c.moveToNext()){
                        idEsc = c.getInt(0);
                    }
                }else{
                    Toast.makeText(this,"Lo siento no existe ese administrador",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!db.insertLocal(Integer.parseInt(id),idEsc,nombre,Integer.parseInt(cupomax))){
                    Toast.makeText(this,"Error no se puede guardar posiblemente ya exista ese usuario",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Local insertado con exito",Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(this,"Lo siento ocurrio un erro inesperado",Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizar(View view) {
        try {
            String id = txt1.getText().toString().trim();
            String nombre = txt2.getText().toString().trim();
            String idAdmin = txt3.getText().toString().trim();
            String cupomax = txt4.getText().toString().trim();
            if(id.isEmpty() || nombre.isEmpty() || idAdmin.isEmpty() || cupomax.isEmpty()){
                Toast.makeText(this,"Digite correctamente los datos",Toast.LENGTH_SHORT).show();
            }else{
                int idEsc=0;
                idEsc = Integer.parseInt(idAdmin);
                Cursor c = db.getDataAdministrador(idEsc);
                if(c!=null && c.getCount()>0){
                    while (c.moveToNext()){
                        idEsc = c.getInt(0);
                    }
                }else{
                    Toast.makeText(this,"Lo siento no existe ese administrador",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!db.updateLocal(Integer.parseInt(id),idEsc,nombre,Integer.parseInt(cupomax))){
                    Toast.makeText(this,"Error no se puede guardar posiblemente ya exista ese usuario",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Local insertado con exito",Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(this,"Lo siento ocurrio un erro inesperado",Toast.LENGTH_SHORT).show();
        }
    }

    public void borrar(View view) {
        try {
            String id = txt1.getText().toString().trim();
            if(id.isEmpty()){
                Toast.makeText(this,"Digite el codigo del admin",Toast.LENGTH_SHORT).show();
            }else{
                if(db.deleteLocal(id)>0){
                    Toast.makeText(this,"Se elimino perfectamente ese dato",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"No se elimino ni un dato",Toast.LENGTH_SHORT).show();
                }
            }
            txt3.setText("");
            txt2.setText("");
            txt1.setText("");
        }catch (Exception e){
            Toast.makeText(this,"Ocurrio un error inesperado",Toast.LENGTH_SHORT).show();
        }
    }







}
