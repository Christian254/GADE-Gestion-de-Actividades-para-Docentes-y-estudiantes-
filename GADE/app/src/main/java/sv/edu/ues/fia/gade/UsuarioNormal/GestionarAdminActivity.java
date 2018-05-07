package sv.edu.ues.fia.gade.UsuarioNormal;

import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;
import sv.edu.ues.fia.gade.model.Usuario;

public class GestionarAdminActivity extends AppCompatActivity {

    TextInputEditText txt1,txt2,txt3;
    TextView esc;
    private controlDB db;
    private static String antiguoNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_admin);
        txt1= (TextInputEditText)findViewById(R.id.idAdmin1);
        txt2= (TextInputEditText)findViewById(R.id.nomAdmin);
        txt3= (TextInputEditText)findViewById(R.id.idEsc);
        db = new controlDB(this);
        //db.insertEscuela(1,"Ing sistemas");
        //db.insertEscuela(2,"Ing Electrica");
        //db.insertEscuela(3,"Ing Mecanica");
        antiguoNombre=" ";
    }

    public void buscar(View view) {
        try {
            String id = txt1.getText().toString().trim();
            String nombre = txt2.getText().toString();
            antiguoNombre=nombre;
            if(id.isEmpty() && nombre.isEmpty()){
                Toast.makeText(this,"Digite el numero del administrador",Toast.LENGTH_SHORT).show();
            }else{
                Cursor c = db.getDataAdministrador(Integer.parseInt(id));
                String nom=null, escuela=null;
                int idEscuela=0;
                if(c!=null && c.getCount()>0){
                    while (c.moveToNext()){
                        idEscuela = c.getInt(1);
                        nom = c.getString(2);
                    }
                }else{
                    Toast.makeText(this,"Lo siento no existe ese administrador",Toast.LENGTH_SHORT).show();
                    return;
                }
                txt2.setText(nom);
                txt3.setText(String.valueOf(idEscuela));
            }
        }catch (Exception e){
            Toast.makeText(this,"Lo siento ocurrio un erro inesperado",Toast.LENGTH_SHORT).show();
        }
    }

    public void guardar(View view) {
        try {
            String id = txt1.getText().toString().trim();
            String nombre = txt2.getText().toString().trim();
            int x = nombre.compareToIgnoreCase(antiguoNombre);

            String idEscuela = txt3.getText().toString().trim();
            if(id.isEmpty() || nombre.isEmpty() || idEscuela.isEmpty()){
                Toast.makeText(this,"Digite el numero del administrador y el nombre",Toast.LENGTH_SHORT).show();
            }else{
                int idEsc=0;
                idEsc = Integer.parseInt(idEscuela);
                Cursor c = db.getDataEscuela(idEsc);
                if(c!=null && c.getCount()>0){
                    while (c.moveToNext()){
                        idEsc = c.getInt(0);
                    }
                }else{
                    Toast.makeText(this,"Lo siento no existe esa escuela",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!db.insertAdministrador(Integer.parseInt(id),idEsc,nombre)){
                    Toast.makeText(this,"Error no se puede guardar posiblemente ya exista ese usuario",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Administrador insertado con exito",Toast.LENGTH_SHORT).show();
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
            int x = nombre.compareToIgnoreCase(antiguoNombre);

            String idEscuela = txt3.getText().toString().trim();
            if(id.isEmpty() || nombre.isEmpty() || idEscuela.isEmpty()){
                Toast.makeText(this,"Digite el numero del administrador y el nombre",Toast.LENGTH_SHORT).show();
            }else{
                int idEsc=0;
                idEsc = Integer.parseInt(idEscuela);
                Cursor c = db.getDataEscuela(idEsc);
                if(c!=null && c.getCount()>0){
                    while (c.moveToNext()){
                        idEsc = c.getInt(0);
                    }
                }else{
                    Toast.makeText(this,"Lo siento no existe esa escuela",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!db.updateAdmin(Integer.parseInt(id),idEsc,nombre)){
                    Toast.makeText(this,"Error no se puede guardar posiblemente ya exista ese usuario",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Administrador actualizado con exito",Toast.LENGTH_SHORT).show();
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
                if(db.deleteAdmin(id)>0){
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
