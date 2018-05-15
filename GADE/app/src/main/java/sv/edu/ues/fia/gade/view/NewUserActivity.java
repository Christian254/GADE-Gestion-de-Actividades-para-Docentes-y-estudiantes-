package sv.edu.ues.fia.gade.view;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class NewUserActivity extends AppCompatActivity {

    TextInputEditText txtusername, txtpassword;
    private RadioGroup rad;
    private FloatingActionButton add;
    private controlDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        rad = (RadioGroup)findViewById(R.id.radioGrup1);
        add = (FloatingActionButton)findViewById(R.id.agUser);
        db = new controlDB(this);
    }

    public void insertarUsuario(View view) {
        txtusername = (TextInputEditText)findViewById(R.id.txtUsername);
        txtpassword = (TextInputEditText)findViewById(R.id.txtPassword);
        String nombre = txtusername.getText().toString().trim();
        String contra = txtpassword.getText().toString().trim();
        if(nombre.isEmpty() || contra.isEmpty()){
            Toast.makeText(this,"No seas loco digita algo",Toast.LENGTH_SHORT).show();
        }else if (contra.length()>=4){
            int x=rad.getCheckedRadioButtonId();
            int res=0;
            switch (x){
                case R.id.radUsuario:
                    res=1;
                    break;
                case R.id.radAdmin:
                    res=2;
                    break;
                default:
                    Toast.makeText(this,"Tiene que elejir una opcion",Toast.LENGTH_SHORT).show();
                    return;
            }
            if(db.insertUser(nombre,contra,res)){
                finish();
            }else{
                Toast.makeText(this,"Lo sentimos no se puede ingresar ese usuario seguramente ya existia",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
