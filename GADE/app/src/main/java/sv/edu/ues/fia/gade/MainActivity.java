package sv.edu.ues.fia.gade;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import sv.edu.ues.fia.gade.controlBaseDato.controlDB;
import sv.edu.ues.fia.gade.view.NewUserActivity;
import sv.edu.ues.fia.gade.view.UsersActivity;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txtusername, txtpassword;
    private Button btnLogin;
    private controlDB db;

    private FloatingActionButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button)findViewById(R.id.btnLog);
        db = new controlDB(this);
        add = (FloatingActionButton)findViewById(R.id.agregarUser);
    }

    public void login(View view) {
        txtusername = (TextInputEditText)findViewById(R.id.user);
        txtpassword = (TextInputEditText)findViewById(R.id.password);
        String nombre = txtusername.getText().toString().trim();
        String contra = txtpassword.getText().toString().trim();
        if(nombre.isEmpty() || contra.isEmpty()){
            Toast.makeText(this,"Digite sus credenciales",Toast.LENGTH_SHORT).show();
        }else if (contra.length()>=4){
            String us="";
            String pas="";
            int tip =0;
            Cursor c = db.getDataUser(nombre);
            if(c!=null && c.getCount()==1){
                while (c.moveToNext()){
                    us = c.getString(0);
                    pas = c.getString(1);
                    tip = c.getInt(2);
                }
                int x = us.compareToIgnoreCase(nombre);
                int y = pas.compareTo(contra);
                if (x==0 && y ==0){
                    if(tip==1){
                        Intent i = new Intent(this,IndexActivity.class);
                        i.putExtra("user",us);
                        i.putExtra("passwor",pas);
                        i.putExtra("tipo",String.valueOf(tip));
                        startActivity(i);
                        finish();
                    }else if(tip==2){
                        Intent i = new Intent(this,IndexActivity.class);
                        i.putExtra("user",us);
                        i.putExtra("passwor",pas);
                        i.putExtra("tipo",String.valueOf(tip));
                        startActivity(i);
                        finish();
                    }
                }else {
                    Toast.makeText(this,"Datos erroneos no hay usuarios con esas descripciones",Toast.LENGTH_SHORT).show();
                }
            }else if(c.getCount()==0){
                boolean vuelto = db.insertUser("admin","root",new Integer(2));
                vuelto = db.insertUser("MH15012","user",new Integer(1));
                if(vuelto){
                    Toast.makeText(this,"Usuario administrador inicializado exitosamente",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Lo sentimos ese usuario no existe o no tiene permisos",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"Lo sentimos datos completamente equivocados",Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void agregar(View view) {
        Intent i = new Intent(this, NewUserActivity.class);
        startActivity(i);
    }



    public static class AdminActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_salir,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.opcion:
                if (db.llenarDBGp01()){
                    Toast.makeText(this,"Base de datos llenado con exito",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"La base de datos ya se lleno",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.opcion1:
                finish();
                break;
            case R.id.opcion2:
                Intent i = new Intent(this, UsersActivity.class);
                startActivity(i);
//                finish();
                break;
            case R.id.opcion3:
                Intent i2 = new Intent(this, NewUserActivity.class);
                startActivity(i2);
                break;
        }
        return true;
    }


}
