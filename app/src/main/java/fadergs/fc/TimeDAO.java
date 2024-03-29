package fadergs.fc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TimeDAO {

    public static void inserir(Context contexto, Time time){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", time.getNome() );

        db.insert("times" , null , valores );

    }
    public static void editar(Context contexto, Time time){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", time.getNome() );

        db.update("times" , valores, "id = " + time.getId(), null );

    }
    public static void excluir(Context contexto, int idTime){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete("times" , "idTime = " + idTime , null );

    }

    public static List<Time> getTimes(Context contexto){
        List<Time> listaDeTimes = new ArrayList<Time>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM times ORDER BY nome", null);
        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Time t = new Time();
                t.setId(  cursor.getInt( 0 ) );
                t.setNome( cursor.getString( 1 ) );
                listaDeTimes.add( t );
            }while ( cursor.moveToNext() );
        }
        return listaDeTimes;
    }
    public static Time getTimeById(Context contexto, int idTime){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sql = "SELECT * FROM times where id = " + idTime;
        Cursor cursor = db.rawQuery(sql,null);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();

                Time t = new Time();
                t.setId(  cursor.getInt( 0 ) );
                t.setNome( cursor.getString( 1 ) );

                return t;

        }else {
            return null;
        }
    }
    public static List<Time> getTimeByName(Context contexto, String nomeTime){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Time> time = new ArrayList<Time>();

//        String sql = "SELECT * FROM times where nome = " + nomeTime;
        Cursor cursor = db.rawQuery("SELECT * FROM times where nome like '%" + nomeTime + "%'", null);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Time t = new Time();
                t.setId(  cursor.getInt( 0 ) );
                t.setNome( cursor.getString( 1 ) );
                time.add( t );
            }while ( cursor.moveToNext() );
        }
        Log.i("retornando times", "getTimeByName: " + time);
        return time;
    }
}
