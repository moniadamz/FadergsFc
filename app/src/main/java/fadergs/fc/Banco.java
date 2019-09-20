package fadergs.fc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME = "FadergsFc";

    public Banco(Context contexto){
        super(contexto, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "create table if not exists jogadores ( " +
                " id integer primary key autoincrement," +
                " nome text ," +
                " camisa integer );" );

        sqLiteDatabase.execSQL( "create table if not exists times ( " +
                " id integer primary key autoincrement," +
                " nome text );" );

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists times");
        onCreate(sqLiteDatabase);

    }
}
