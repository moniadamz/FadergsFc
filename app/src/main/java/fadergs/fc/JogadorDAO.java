package fadergs.fc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class JogadorDAO {

    public void inserir(Context contexto, Jogador jogador){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", jogador.getNome() );
        valores.put( "camisa", jogador.getCamisa() );
        valores.put( "idTime", jogador.getTime() );

        db.insert("jogadores" , null , valores );
    }

    public static List<Jogador> getJogadores(Context contexto){
        List<Jogador> listaDeJogadores = new ArrayList<Jogador>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM jogadores", null);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Jogador j = new Jogador();
                j.setId(  cursor.getInt( 0 ) );
                j.setNome( cursor.getString( 1 ) );
                j.setCamisa(cursor.getInt(2));
                j.setTime(cursor.getInt(3));
                listaDeJogadores.add( j );
            }while ( cursor.moveToNext() );
        }
//        Log.i("jogadores do banco", "getJogadores: " + listaDeJogadores);
        return listaDeJogadores;
    }
    public static List<Jogador> getJogadoresById(Context contexto, int idTime){
        List<Jogador> listaDeJogadores = new ArrayList<Jogador>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM jogadores where idTime=" + idTime, null);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Jogador j = new Jogador();
                j.setId(  cursor.getInt( 0 ) );
                j.setNome( cursor.getString( 1 ) );
                j.setCamisa(cursor.getInt(2));
                j.setTime(cursor.getInt(3));
                listaDeJogadores.add( j );
            }while ( cursor.moveToNext() );
        }
//        Log.i("jogadores do banco", "getJogadores: " + listaDeJogadores);
        return listaDeJogadores;
    }

    public Jogador buscarJogadorPorNome(Context contexto, String nome){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();
        Jogador j;
        Cursor cursor = db.rawQuery("SELECT nome, camisa FROM jogadores WHERE nome = ?", new String[] {nome});
        cursor.moveToFirst();

        if(cursor.getCount() > 0){

            j = new Jogador();
            j.setNome(cursor.getString(0));
            j.setCamisa(cursor.getInt(1));

        } else {
            j = null;
        }

        cursor.close();
        db.close();

        return j;
    }
}
