package sv.edu.ues.fia.gade.controlBaseDato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.model.AccesoUsuario;
import sv.edu.ues.fia.gade.model.OpcionCrud;
import sv.edu.ues.fia.gade.model.Usuario;

/**
 * Created by HP on 28/4/2018.
 */

public class controlDB extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "datos.db";
    //AQUI LOS DATOS DE LAS TABLAS USUARIO, OPCION, ACCESO
    public static final String table_nameU = "USUARIO";
    public static final String COl_1U = "USERNAME";
    public static final String COl_2U = "CLAVE";
    public static final String COl_3U = "TIPO";

    public static final String table_nameO = "OPCION";
    public static final String COl_1O = "ID"; //ESA ES O DE OSO xd
    public static final String COl_2O = "NOMBRECRUD";
    public static final String COl_3O = "NUMCRUD";

    public static final String table_nameA = "ACCESO";
    public static final String COl_1A = "UERNAME";
    public static final String COl_2A = "ID";


    public controlDB(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table USUARIO  (USERNAME TEXT primary key,CLAVE TEXT not null, TIPO INTEGER not null )");
            db.execSQL("create table OPCION  (ID INTEGER primary key, NOMBRECRUD TEXT, NUMCRUD INTEGER not null )");
            db.execSQL("create table ACCESO  (USERNAME TEXT not null, ID INTEGER not null, primary key (USERNAME, ID))");

            //Tablas que faltaban.
            db.execSQL("create table ACTIVIDAD(IDACTIVIDAD INTEGER not null, IDTIPOACTIVIDAD INTEGER not null, IDDOCENTE INTEGER not null, NOMACTIVIDAD TEXT not null, primary key(IDACTIVIDAD, IDTIPOACTIVIDAD, IDDOCENTE))");
            db.execSQL("create table TIPOACTIVIDAD(IDTIPOACTIVIDAD INTEGER not null,NOMTIPOACTIVIDAD TEXT not null,primary key (IDTIPOACTIVIDAD))");
            db.execSQL("create table DOCENTE(IDDOCENTE INTEGER not null,IDESCUELA INTEGER not null,NOMDOCENTE TEXT not null,primary key (IDDOCENTE, IDESCUELA))");
            db.execSQL("create table ESCUELA(IDESCUELA INTEGER not null,NOMESCUELA TEXT not null,primary key (IDESCUELA))");
            db.execSQL("create table ADMINISTRADOR(IDADMIN INTEGER not null, IDESCUELA INTEGER not null, NOMADMIN TEXT not null, primary key(IDADMIN, IDESCUELA))");
            db.execSQL("create table CICLO(IDCICLO INTEGER not null,CICLODESDE DATE not null, CICLOHASTA DATE not null, primary key (IDCICLO))");
            db.execSQL("create table HORARIO(IDHORARIO INTEGER not null,HORARIODESDE DATE not null,HORARIOHASTA DATE not null,primary key (IDHORARIO))");
            db.execSQL("create table LOCAL(IDLOCAL INTEGER not null,IDADMIN INTEGER not null,NUMLOCAL TEXT not null,CUPO INTEGER not null,primary key (IDLOCAL, IDADMIN))");
            db.execSQL("create table RESERVA(IDRESERVA INTEGER not null,ESTADO INTEGER not null,IDACTIVIDAD INTEGER not null,primary key (IDRESERVA))");
            db.execSQL("create table DISPONIBLE(IDHORARIO INTEGER not null,IDLOCAL INTEGER not null,IDCICLO INTEGER not null,IDRESERVA INTEGER not null, DISPONIBLE  INTEGER not null, primary key (IDHORARIO, IDLOCAL, IDCICLO, IDRESERVA))");
            db.execSQL("create table ESTUDIANTE(CARNET INTEGER not null,IDESCUELA INTEGER not null,NOMESTUDIANTE TEXT not null,primary key (CARNET, IDESCUELA))");
            db.execSQL("create table PARTICIPACION(IDACTIVIDAD INTEGER not null,CARNET INTEGER not null,VALORACION INTEGER not null,COMENTARIO TEXT not null, primary key (IDACTIVIDAD, CARNET)) ");


        }catch (Exception e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS USUARIO");
            db.execSQL("DROP TABLE IF EXISTS OPCION");
            db.execSQL("DROP TABLE IF EXISTS ACCESO");
            db.execSQL("DROP TABLE IF EXISTS TIPOACTIVIDAD");
            db.execSQL("DROP TABLE IF EXISTS ACTIVIDAD");
            db.execSQL("DROP TABLE IF EXISTS DOCENTE");
            db.execSQL("DROP TABLE IF EXISTS ESCUELA");
            db.execSQL("DROP TABLE IF EXISTS ADMINISTRADOR");
            db.execSQL("DROP TABLE IF EXISTS CICLO");
            db.execSQL("DROP TABLE IF EXISTS HORARIO");
            db.execSQL("DROP TABLE IF EXISTS LOCAL");
            db.execSQL("DROP TABLE IF EXISTS RESERVA");
            db.execSQL("DROP TABLE IF EXISTS DISPONIBLE");
            db.execSQL("DROP TABLE IF EXISTS ESTUDIANTE");
            db.execSQL("DROP TABLE IF EXISTS PARTICIPACION");



        }catch (Exception e){

        }
    }

    public boolean insertUser(String username, String password, int tipo){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COl_1U,username);
        contentValues.put(COl_2U,password);
        contentValues.put(COl_3U,tipo);
        long resul = db.insert("USUARIO",null,contentValues);
        db.close();
        if (resul ==-1){
            retorno=false;
        }else{
            retorno=true;
        }
        return retorno;
    }



    public boolean insertOpcion(int id, String nombreCRUD,  int numeroCrud){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COl_1O,id);
        contentValues.put(COl_2O,nombreCRUD);
        contentValues.put(COl_3O,numeroCrud);
        long resul = db.insert("OPCION",null,contentValues);
        db.close();
        if (resul ==-1){
            retorno=false;
        }else{
            retorno=true;
        }
        return retorno;
    }

    //ACCESO  (USERNAME TEXT not null, ID INTEGER primary key)

    public boolean insertAcceso(String username,  int id){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME",username);
        contentValues.put("ID",id);
        long resul = db.insert("ACCESO",null,contentValues);
        db.close();
        if (resul ==-1){
            retorno=false;
        }else{
            retorno=true;
        }
        return retorno;
    }

    public Cursor getDataUser(String username){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from  USUARIO where USERNAME= \""+username+"\"",null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    public Cursor getData(String tabla){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from "+tabla,null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    public boolean llenarDBGp01(){
        try {
            ArrayList<Usuario> users = new ArrayList<>();
            ArrayList<OpcionCrud> option = new ArrayList<>();
            ArrayList<AccesoUsuario> accesoUsuarios = new ArrayList<>();
            users.add(new Usuario("Herman","gD21d",1));
            users.add(new Usuario("Alberto","jA3f2",1));
            users.add(new Usuario("Carlos","Ch1q2",1));
            users.add(new Usuario("walter","1234",2));

            option.add(new OpcionCrud("CREAR USUARIO",1,1));
            option.add(new OpcionCrud("EDITAR USUARIO",2,2));
            option.add(new OpcionCrud("ELIMINAR USUARIO",3,3));
            option.add(new OpcionCrud("SELECCIONAR USUARIO",4,4));

            option.add(new OpcionCrud("CREAR LOCAL",5,1));
            option.add(new OpcionCrud("EDITAR LOCAL",6,2));
            option.add(new OpcionCrud("ELIMINAR LOCAL",7,3));
            option.add(new OpcionCrud("SELECCIONAR LOCAL",8,4));

            option.add(new OpcionCrud("CREAR RESERVACION",9,1));
            option.add(new OpcionCrud("EDITAR RESERVACION",10,2));
            option.add(new OpcionCrud("ELIMINAR RESERVACION",11,3));
            option.add(new OpcionCrud("SELECCIONAR RESERVACION",12,4));


            int i=1;
            for (OpcionCrud op : option){
                insertOpcion(op.getIdOpcion(),op.getNombreCRUD(),op.getNumCRUD());
                for(Usuario u : users){
                    if(op.getNumCRUD()==4){
                        insertAcceso(u.getUsername(),op.getIdOpcion());
                    }else if(u.getTipo()==2){
                        insertAcceso(u.getUsername(),op.getIdOpcion());
                    }
                }
            }

            for (Usuario u : users){
                insertUser(u.getUsername(),u.getClave(),u.getTipo());
            }


            return true;
        }catch (Exception e){
            return false;
        }
    }

    private Cursor getData(String opcion, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+opcion+ " WHERE ID= "+i,null);
        return cursor;
    }

    public Cursor getDataUsuarios(String usuario) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from USUARIO inner join  ACCESO ON USUARIO.USERNAME = ACCESO.USERNAME inner join OPCION on ACCESO.id=OPCION.ID WHERE ACCESO.USERNAME =\""+usuario.toString().trim()+"\"" ,null);
            return cursor;
        }catch(Exception e){
            return null;
        }
    }
}














