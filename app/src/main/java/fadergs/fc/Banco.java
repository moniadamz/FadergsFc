package fadergs.fc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 4;
    private static final String NOME = "FadergsFc";

    public Banco(Context contexto){
        super(contexto, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_TIMES = "CREATE TABLE IF NOT EXISTS times (idtime integer NOT NULL PRIMARY KEY AUTOINCREMENT, nome text)";
        String SQL_JOGADORES =  "CREATE TABLE IF NOT EXISTS jogadores (idjogador integer NOT NULL PRIMARY KEY AUTOINCREMENT, nomejogador text, camisajogador integer, timejogador integer, FOREIGN KEY(timejogador) REFERENCES Time(idtime))";

        sqLiteDatabase.execSQL(SQL_TIMES);
        sqLiteDatabase.execSQL(SQL_JOGADORES);

        sqLiteDatabase.execSQL( "CREATE TABLE IF NOT EXISTS timejogador ( "+" idtimejogador integer NOT NULL PRIMARY KEY AUTOINCREMENT,"+" nometime text ,"+" nomejogador text ,"+" camisa int );" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS timejogador");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS jogadores");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS times");
        onCreate(sqLiteDatabase);

    }
}
