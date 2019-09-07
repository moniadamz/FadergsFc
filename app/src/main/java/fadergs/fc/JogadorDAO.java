package fadergs.fc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.GenericDeclaration;

public class JogadorDAO {
    public void inserir(Context contexto, Jogador jogador){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", jogador.getNome() );
        valores.put( "camisa", jogador.getCamisa() );

        db.insert("jogadores" , null , valores );
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
