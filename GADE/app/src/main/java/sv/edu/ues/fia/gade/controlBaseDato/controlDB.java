package sv.edu.ues.fia.gade.controlBaseDato;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.clases.Actividad;
import sv.edu.ues.fia.gade.clases.Alumno;

import sv.edu.ues.fia.gade.clases.Disponible;

import sv.edu.ues.fia.gade.clases.Ciclo;

import sv.edu.ues.fia.gade.clases.Docente;

import sv.edu.ues.fia.gade.clases.Participacion;
import sv.edu.ues.fia.gade.clases.Horario;

import sv.edu.ues.fia.gade.clases.Reserva;
import sv.edu.ues.fia.gade.clases.TipoActividad;
import sv.edu.ues.fia.gade.model.AccesoUsuario;
import sv.edu.ues.fia.gade.model.Escuela;
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
    private static final String[]camposReserva = new String [] {"idreserva","estado", "idactividad"};
    private static final String[]camposEscuela = new String [] {"idescuela","nomescuela"};
    private static final String[]camposHorario = new String [] {"idHorario","dia","horarioDesde", "horarioHasta"};
    private static final String[]camposCiclo = new String [] {"idHorario","numCiclo"};
    private static final String[]camposEstudiante = new String [] {"carnet","idescuela","nomestudiante"};
    private static final String[]camposActividad = new String [] {"idactividad", "idtipoactividad", "iddocente", "nomactividad"};
    private static final String [] camposDocente = new String[] {"iddocente","idescuela","nomdocente"};
    private static final String [] camposTipoActividad = new String[] {"idtipoactividad","nomTipoActividad"};
    public static final String delete_escuela_administrador = "CREATE TRIGGER if not exists delete_escuela_administrador AFTER DELETE ON escuela for each row BEGIN DELETE FROM administrador WHERE idescuela = old.idescuela; END";
    public static final String delete_escuela_estudiante = "CREATE TRIGGER if not exists delete_escuela_estudiante AFTER DELETE ON escuela for each row BEGIN DELETE FROM estudiante WHERE idescuela = old.idescuela; END";




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
            db.execSQL("create table ACTIVIDAD(IDACTIVIDAD INTEGER not null, IDTIPOACTIVIDAD INTEGER not null, IDDOCENTE INTEGER not null, NOMACTIVIDAD TEXT not null, primary key(IDACTIVIDAD))");
            db.execSQL("create table TIPOACTIVIDAD(IDTIPOACTIVIDAD INTEGER not null,NOMTIPOACTIVIDAD TEXT not null,primary key (IDTIPOACTIVIDAD))");
            db.execSQL("create table DOCENTE(IDDOCENTE INTEGER not null,IDESCUELA INTEGER not null,NOMDOCENTE TEXT not null,primary key (IDDOCENTE))");
            db.execSQL("create table ESCUELA(IDESCUELA INTEGER not null,NOMESCUELA TEXT not null unique,primary key (IDESCUELA))");
            db.execSQL("create table ADMINISTRADOR(IDADMIN INTEGER primary key, IDESCUELA INTEGER not null, NOMADMIN TEXT not null)");
            db.execSQL("create table CICLO(IDCICLO INTEGER not null,NUMCICLO DATE not null, primary key (IDCICLO))");
            db.execSQL("create table HORARIO(IDHORARIO INTEGER not null,DIA TEXT not null, HORARIODESDE DATE not null,HORARIOHASTA DATE not null,primary key (IDHORARIO))");
            db.execSQL("create table LOCAL(IDLOCAL INTEGER primary key autoincrement,IDADMIN INTEGER not null,NUMLOCAL TEXT not null,CUPO INTEGER not null)");
            db.execSQL("create table RESERVA(IDRESERVA INTEGER not null,ESTADO INTEGER not null,IDACTIVIDAD INTEGER not null,primary key (IDRESERVA))");
            db.execSQL("create table DISPONIBLE(IDHORARIO INTEGER not null,IDLOCAL INTEGER not null,IDCICLO INTEGER not null,IDRESERVA INTEGER not null, DISPONIBLE  INTEGER not null, primary key (IDHORARIO, IDLOCAL, IDCICLO, IDRESERVA))");
            db.execSQL("create table ESTUDIANTE(CARNET TEXT not null,IDESCUELA INTEGER not null,NOMESTUDIANTE TEXT not null,primary key (CARNET))");
            db.execSQL("create table PARTICIPACION(IDACTIVIDAD INTEGER not null,CARNET INTEGER not null,VALORACION INTEGER not null,COMENTARIO TEXT not null, primary key (IDACTIVIDAD,CARNET)) ");


            /* Trigger */
            db.execSQL(delete_escuela_administrador); // delete trigger
            db.execSQL(delete_escuela_estudiante); // delete trigger

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
    public void abrir() throws SQLException
    {

        SQLiteDatabase db = this.getWritableDatabase();
        return; }





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
            ArrayList<Ciclo> ciclos = new ArrayList<>();
            ArrayList<AccesoUsuario> accesoUsuarios = new ArrayList<>();
            users.add(new Usuario("Herman","gD21d",1));
            users.add(new Usuario("Alberto","jA3f2",1));
            users.add(new Usuario("Carlos","Ch1q2",1));
            users.add(new Usuario("walter","1234",2));
            users.add(new Usuario("Katlheen","1234",2));
            users.add(new Usuario("maury","admin",2));

            ciclos.add(new Ciclo(1,"Ciclo 1"));
            ciclos.add(new Ciclo(2,"Ciclo 2"));

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
            for (Ciclo c : ciclos){
                insertarCiclo(c.getIdCiclo(),c.getNumCiclo());
            }
           // insertarReservas(1,1,1);


            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Cursor getData(String opcion, int i) {
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

    /*CRUD PARA ADMINISTRADOR Y LOCALES XD
    *  ADMINISTRADOR(IDADMIN INTEGER not null, IDESCUELA INTEGER not null, NOMADMIN TEXT not null, primary key(IDADMIN))");
    * LOCAL(IDLOCAL INTEGER not null,IDADMIN INTEGER not null,NUMLOCAL TEXT not null,CUPO INTEGER not null,primary key (IDLOCAL))");
    * */

    public boolean insertAdministrador(int id,int idEscuela,String nombre){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDADMIN",id);
        contentValues.put("IDESCUELA",idEscuela);
        contentValues.put("NOMADMIN",nombre);
        long resul = db.insert("ADMINISTRADOR",null,contentValues);
        db.close();
        if (resul ==-1){
            retorno=false;
        }else{
            retorno=true;
        }
        return retorno;
    }

    public Cursor getDataAdministrador(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from ADMINISTRADOR WHERE IDADMIN= "+i,null);
        return cursor;
    }

    public boolean updateAdmin(int id, int escuela,String nombre){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDESCUELA",escuela);
        contentValues.put("NOMADMIN",nombre);
        int res = db.update("ADMINISTRADOR",contentValues,"IDADMIN =?",new String[]{String.valueOf(id)});
        if (res>0){
            retorno=true;
        }
        return retorno;
    }

    public int deleteAdmin(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete("ADMINISTRADOR","IDADMIN = ?",new String[]{id});

        return res;
    }


    //CRUD LOCALES
    //LOCAL(IDLOCAL INTEGER not null,IDADMIN INTEGER not null,NUMLOCAL TEXT not null,CUPO INTEGER not null,primary key (IDLOCAL))");
    public boolean insertLocal(int id,int idAdmin,String numLocal,int cupo){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDLOCAL",id);
        contentValues.put("IDADMIN",idAdmin);
        contentValues.put("NUMLOCAL",numLocal);
        contentValues.put("CUPO",cupo);
        long resul = db.insert("LOCAL",null,contentValues);
        db.close();
        if (resul ==-1){
            retorno=false;
        }else{
            retorno=true;
        }
        return retorno;
    }

    public Cursor getDataLocal(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from LOCAL WHERE IDLOCAL= "+i,null);
        return cursor;
    }

    public boolean updateLocal(int id,int idAdmin,String numLocal,int cupo){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDADMIN",idAdmin);
        contentValues.put("NUMLOCAL",numLocal);
        contentValues.put("CUPO",cupo);
        int res = db.update("LOCAL",contentValues,"IDLOCAL =?",new String[]{String.valueOf(id)});
        if (res>0){
            retorno=true;
        }
        return retorno;
    }

    public int deleteLocal(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete("LOCAL","IDLOCAL = ?",new String[]{id});

        return res;
    }




    /*FIN DE LOS CRUD PARA ADMINISTRADOR Y LOCALES*/

    /*Dato que necesitaba para hacer pruebas */
    // ESCUELA(IDESCUELA INTEGER not null,NOMESCUELA TEXT not null,primary key IDESCUELA

    public String insertEscuela(Escuela escuela){
        String regInsertado = "Registro Escuela #";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDESCUELA",escuela.getIdentificadorEscuela());
        contentValues.put("NOMESCUELA", escuela.getNombreEscuela());
        contador = db.insert("ESCUELA",null,contentValues);
        db.close();

        if(contador == -1 || contador == 0){
            regInsertado = "Error al insertar Escuela. Registro duplicado.";
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;
    }

    public Escuela consultarEscuela(int idEscuela){
        String idEsc = String.valueOf(idEscuela);
        String[] id = {idEsc};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("escuela", camposEscuela, "idescuela = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Escuela escuela = new Escuela();
            escuela.setIdentificadorEscuela(cursor.getInt(0));
            escuela.setNombreEscuela(cursor.getString(1));
            return escuela;
        }else{
            return null;
        }

    }

    public String updateEscuela(Escuela escuela){
        String regAc = "Registro Actualizado #";
        String idEscuela = String.valueOf(escuela.getIdentificadorEscuela());
        String[] id = {idEscuela};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nomescuela",escuela.getNombreEscuela());
        int resultado = db.update("escuela",cv,"idescuela = ?",id);

        if(resultado>0){
            regAc += idEscuela;
        }else{
            regAc = "No se encuentra registro Escuela para ser actualizado";
        }
        return regAc;
    }
    

    public String deleteEscuela(Escuela escuela){
        String regEscuelaElim = "Registro eliminado, escuela #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idEscuela = String.valueOf(escuela.getIdentificadorEscuela());
        String[] id = {idEscuela};

        int res = db.delete("escuela", "idescuela = ?",id);

        if(res>0){
            regEscuelaElim += idEscuela;
        }else{
            regEscuelaElim = "Este registro no existe";
        }
        return regEscuelaElim;
    }


    public Cursor getDataEscuela(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from  ESCUELA WHERE NOMESCUELA= "+id,null);
        return cursor;
    }

    public Cursor getDataEscuela(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from  ESCUELA WHERE IDESCUELA= "+id,null);
        return cursor;
    }


    //CRUD para las reservas
    public  String insertReserva(Reserva reserva, Alumno alumno, Docente docente, Actividad actividad)
    {
        String regInsertado = "Registro Reserva #";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDRESERVA",reserva.getIdReserva());
        contentValues.put("ESTADO", reserva.getEstado());
        contentValues.put("IDACTIVIDAD", reserva.getIdActividad());
        contador = db.insert("RESERVA",null,contentValues);
        db.close();

        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe la reserva";
        }else{
            insertAlum(alumno);
            insertDocente(docente);
            insertActividad(actividad);
            regInsertado = regInsertado + contador + " " + alumno.getCarnet() + " "+docente.getNombreDoc() +" Actividad" + actividad.getIdActividad() ;

        }
        return regInsertado;
    }


    public Reserva consultarReserva(String idReserva)
    {
        String [] id = {idReserva};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("reserva", camposReserva, "idReserva = ?", id, null, null, null);
        if(cursor.moveToFirst())
        {
            Reserva reserva = new Reserva();
            reserva.setIdReserva(cursor.getInt(0));
            reserva.setEstado(cursor.getInt(1));
            reserva.setIdActividad(cursor.getInt(2));
            return  reserva;
        }
        else
        {
            return null;
        }
    }

    public String modificarReserva(Reserva reserva)
    {
        String registroActualizado = "El Registro #";
        String idReserva = String.valueOf(reserva.getIdReserva());
        String [] id = {idReserva};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("IDACTIVIDAD", reserva.getIdActividad());
        int resultado = db.update("RESERVA",contentValues,"IDRESERVA = ?",id);

        if(resultado>0){
            registroActualizado += idReserva + " se actualizo";
        }else{
            registroActualizado = "No se encuentra registro";
        }
        return registroActualizado;
    }

    public String eliminarReserva(Reserva reserva)
    {
        String regEliminado = "Se elimino la reserva #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idReserva = String.valueOf(reserva.getIdReserva());
        String [] id = {idReserva};
        int res = db.delete("RESERVA", "IDRESERVA = ?",id);

        if(res>0){
            regEliminado += idReserva;
        }else{
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }

    public String insertar(Horario horario){
        SQLiteDatabase db = this.getWritableDatabase();
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues hora = new ContentValues();
        hora.put("IDHORARIO", horario.getIdHorario());
        hora.put("DIA",horario.getDia());
        hora.put("HORARIODESDE", horario.getHorarioDesde());
        hora.put("HORARIOHASTA", horario.getHorarioHasta());
        contador=db.insert("HORARIO", null, hora);
        db.close();

        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el horario";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public Horario consultarHorario(String idHorario){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] id = {idHorario};
        Cursor cursor = db.query("HORARIO", camposHorario, "idHorario = ?", id,
                null, null, null);
        if(cursor.moveToFirst()){
            Horario horario = new Horario();
            horario.setIdHorario(cursor.getInt(0));
            horario.setDia(cursor.getString(1));
            horario.setHorarioDesde(cursor.getString(2));
            horario.setHorarioHasta(cursor.getString(3));
            return horario;
        }else{
            return null;
        }
    }
    public String actualizar(Horario horario){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] id = {String.valueOf(horario.getIdHorario())};
        ContentValues cv = new ContentValues();
        cv.put("DIA", horario.getDia());
        cv.put("HORARIODESDE", horario.getHorarioDesde());
        cv.put("HORARIOHASTA", horario.getHorarioHasta());
        int res=db.update("HORARIO", cv, "IDHORARIO = ?", id);
        if(res>0){
            return "Horario Actualizado Correctamente";
        }
        else {
            return "Horario no encontrado";
        }
    }
    public String eliminar(Horario horario){

        SQLiteDatabase db = this.getWritableDatabase();
        String [] id = {String.valueOf(horario.getIdHorario())};
        int res = db.delete("HORARIO", "IDHORARIO = ?",id);

        if(res>0){
            return "Horario eliminado con éxito";
        }else{
            return "Este horario no existe";
        }

    }
    public boolean insertarCiclo(int idCiclo, String numCiclo){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean retorno=false;
        long contador=0;
        ContentValues hora = new ContentValues();
        hora.put("IDCICLO", idCiclo);
        hora.put("NUMCICLO", numCiclo);
        contador=db.insert("CICLO", null, hora);
        db.close();
        if (contador ==-1){
            retorno=false;
        }else{
            retorno=true;
        }
        return retorno;
    }
    public Cursor getDataCiclo() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from  CICLO", null);
            return cursor;
        } catch (Exception e) {
            return null;
        }
    }




    /* Alumno */
    // db.execSQL("create table ESTUDIANTE(CARNET TEXT not null,IDESCUELA INTEGER not null,NOMESTUDIANTE TEXT not null,primary key (CARNET))");

    public  String insertAlum(Alumno alumno)    //lo necesitaba
    {
        String regInsertado = "Alumno: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CARNET",alumno.getCarnet());
        contentValues.put("IDESCUELA", alumno.getIdEscuela());
        contentValues.put("NOMESTUDIANTE", alumno.getNombre());
        contador = db.insert("ESTUDIANTE",null,contentValues);
        db.close();

        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe el alumno." + alumno.getCarnet();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;
    }

    public String updateAlumno(Alumno estudiante){
        String regAc = "Registro actualizado #";
        String id[] = {estudiante.getCarnet()};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("idescuela", estudiante.getIdEscuela());
        cv.put("nomestudiante", estudiante.getNombre());
        int contador = db.update("estudiante", cv, "carnet = ?", id);

        if(contador>0){
            regAc = regAc + contador;
        }else{
            regAc = "Registro no existe. Verificar carnet";
        }
        return regAc;
    }


    public Alumno consultarAlumno(String carnet){
        String id[] = {carnet};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("estudiante", camposEstudiante, "carnet = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Alumno estudiante = new Alumno();

            estudiante.setCarnet(cursor.getString(0));
            estudiante.setIdEscuela(cursor.getInt(1));
            estudiante.setNombre(cursor.getString(2));

            return estudiante;

        }else{
            return null;
        }

    }

    public String deleteAlumno(String carnet){
        String regEl = "Registro a eliminar con carnet: ";
        String id[] = {carnet};
        SQLiteDatabase db = this.getWritableDatabase();

        int res = db.delete("estudiante","carnet = ?", id);
        if(res>0){
            regEl += res;
        }else{
            regEl = "No existe, carnet.";
        }
        return regEl;

    }

    //Tabla Participación.
    /*  db.execSQL("create table PARTICIPACION(IDACTIVIDAD INTEGER not null,CARNET INTEGER not null,VALORACION INTEGER not null,COMENTARIO TEXT not null, primary key (IDACTIVIDAD, CARNET)) ");*/

    public String insertParticipacion(Participacion participacion){
        String regIns = "Registro de participación #";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        return regIns;
    }


    public  String insertDocente(Docente docente)   // también lo necesitaba

    {
        String regInsertado = "Docente: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDDOCENTE",docente.getIdDocente());
        contentValues.put("IDESCUELA", docente.getIdEscuela());
        contentValues.put("NOMDOCENTE", docente.getNombreDoc());
        contador = db.insert("DOCENTE",null,contentValues);
        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe el docente." + docente.getIdDocente();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;

    }

    public String eliminarDocente(Docente docente)
    {
        String regEliminado = "Se elimino el docente #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idDocente = String.valueOf(docente.getIdDocente());
        String [] id = {idDocente};
        int res = db.delete("DOCENTE", "IDDOCENTE = ?",id);

        if(res>0){
            regEliminado += idDocente;
        }else{
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }

    public String actualizarDocente(Docente docente)
    {
        String registroActualizado = "El Registro #";
        String idDocente = String.valueOf(docente.getIdDocente());
        String [] id = {idDocente};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("IDDOCENTE", docente.getIdDocente());
        int resultado = db.update("DOCENTE",contentValues,"IDDOCENTE = ?",id);

        if(resultado>0){
            registroActualizado += idDocente+ " se actualizo";
        }else{
            registroActualizado = "No se encuentra registro";
        }
        return registroActualizado;
    }


    public Docente consultarDocente(String idDocente)
    {
        String [] id = {idDocente};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("DOCENTE", camposDocente, "IDDOCENTE = ?", id, null, null, null);
        if(cursor.moveToFirst())
        {
            Docente docente = new Docente();
            docente.setIdDocente(cursor.getInt(0));
            docente.setIdEscuela(cursor.getInt(1));
            docente.setNombreDoc(cursor.getString(2));
            return  docente;
        }
        else
        {
            return null;
        }
    }


    public String insertActividad(Actividad actividad) // lo necesitaba
    {
        String regInsertado = "Actividad: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDACTIVIDAD",actividad.getIdActividad());
        contentValues.put("IDTIPOACTIVIDAD",actividad.getTipoActividad());
        contentValues.put("IDDOCENTE",actividad.getIdDocente());
        contentValues.put("NOMACTIVIDAD",actividad.getNomActividad());
        contador = db.insert("ACTIVIDAD", null,contentValues);
        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe la actividad." + actividad.getIdActividad();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;
    }

    public String eliminarActividad(Actividad actividad)
    {
        String regEliminado = "Se elimino la actividad #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idActividad = String.valueOf(actividad.getIdActividad());
        String [] id = {idActividad};
        int res = db.delete("ACTIVIDAD", "IDACTIVIDAD = ?",id);

        if(res>0){
            regEliminado += idActividad;
        }else{
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }

    public String actualizarActividad(Actividad actividad)
    {
        String registroActualizado = "El Registro #";
        String idActividad = String.valueOf(actividad.getIdActividad());
        String [] id = {idActividad};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("IDACTIVIDAD", actividad.getIdActividad());
        int resultado = db.update("ACTIVIDAD",contentValues,"IDACTIVIDAD = ?",id);

        if(resultado>0){
            registroActualizado += idActividad+ " se actualizo";
        }else{
            registroActualizado = "No se encuentra registro";
        }
        return registroActualizado;
    }


    public  Actividad consultarActividad(String act) // lo necesitaba
    {
        String [] idAct = {act};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("actividad", camposActividad, "idactividad = ?", idAct, null, null, null);
        if (cursor.moveToFirst())
        {
            Actividad actividad = new Actividad();

            actividad.setIdActividad(cursor.getInt(0));
            actividad.setTipoActividad(cursor.getInt(1));
            actividad.setIdDocente(cursor.getInt(2));
            actividad.setNomActividad(cursor.getString(3));

            return actividad;
        }
        else {
            return null;
        }
    }



    /*  TABLA DISPONIBLE */
    public String insertarDisponible(Disponible disponible)
    {
        String regInsertado = "Registro insertado: ";
        long contador = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDHORARIO", disponible.getIdHorario());
        contentValues.put("IDLOCAL", disponible.getIdLocal());
        contentValues.put("IDCICLO", disponible.getIdCiclo());
        contentValues.put("IDRESERVA", disponible.getIdReserva());
        contador = db.insert("DISPONIBLE",null,contentValues);
        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe.";
            disponible.setDisponible(2);
        }else{
            regInsertado = regInsertado + contador;
            disponible.setDisponible(1);
        }
        return regInsertado;
    }

    public  String insertTipoActividad(TipoActividad tipoActvidad)   // también lo necesitaba

    {
        String regInsertado = "TipoActividad: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDTIPOACTIVIDAD",tipoActvidad.getIdTipoActividad());
        contentValues.put("NOMTIPOACTIVIDAD", tipoActvidad.getNomTipoActividad());
        contador = db.insert("TIPOACTIVIDAD",null,contentValues);
        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe el tipo de actividad." + tipoActvidad.getIdTipoActividad();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;

    }

    public String eliminarTipoActividad(TipoActividad tipoactividad)
    {
        String regEliminado = "Se elimino el tipo actividad #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idTipoActividad = String.valueOf(tipoactividad.getIdTipoActividad());
        String [] id = {idTipoActividad};
        int res = db.delete("TIPOACTIVIDAD", "IDTIPOACTIVIDAD = ?",id);

        if(res>0){
            regEliminado += idTipoActividad;
        }else{
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }

    public String actualizarTipoActividad(TipoActividad tipoActividad)
    {
        String registroActualizado = "El Registro #";
        String idTipoActividad = String.valueOf(tipoActividad.getIdTipoActividad());
        String [] id = {idTipoActividad};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("IDTIPOACTIVIDAD", tipoActividad.getIdTipoActividad());
        int resultado = db.update("TIPOACTIVIDAD",contentValues,"IDTIPOACTIVIDAD = ?",id);

        if(resultado>0){
            registroActualizado += idTipoActividad+ " se actualizo";
        }else{
            registroActualizado = "No se encuentra registro";
        }
        return registroActualizado;
    }
    
    public TipoActividad consultarTipoActividad(String idTipoActividad)
    {
        String [] id = {idTipoActividad};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TIPOACTIVIDAD", camposTipoActividad, "IDTIPOACTIVIDAD = ?", id, null, null, null);
        if(cursor.moveToFirst())
        {
            TipoActividad tipoActividad = new TipoActividad();
            tipoActividad.setIdTipoActividad(cursor.getInt(0));;
            tipoActividad.setNomTipoActividad(cursor.getString(1));
            return  tipoActividad;
        }
        else
        {
            return null;
        }
    }
}














