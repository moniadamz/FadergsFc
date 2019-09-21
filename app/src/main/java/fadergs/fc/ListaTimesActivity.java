package fadergs.fc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaTimesActivity extends AppCompatActivity {

    private ListView lvTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_times);

        TimeDAO dao = new TimeDAO();
        List<Time> times = dao.getTimes(this);
        //dao.close();

        ListView listaTimes = (ListView) findViewById(R.id.ActivityListaTimes);
        ArrayAdapter<Time> adapter = new ArrayAdapter<Time>(this, android.R.layout.simple_list_item_1, times);
        listaTimes.setAdapter(adapter);

        setTitle("Lista de Times");


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListaTimesActivity.this, FormTimesActivity.class);
                startActivity( i );
            }
        });

    }


    private void excluir(final Time time){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Time");
        alerta.setMessage("Confirma a exclusão do Time "
                + time.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TimeDAO.excluir(ListaTimesActivity.this, time.getId());
                carregarLista();
            }
        });
        alerta.show();

    }
    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    private void carregarLista(){
        List<Time> lista = TimeDAO.getTimes(this);
        Log.i("Carregar lista", "carregarLista: " + lista);

/*        if ( lista.size() == 0 ){
            lvTimes.setEnabled( false );
            Time fake = new Time();
            fake.setNome("Lista Vazia!");
            lista.add( fake );
        }else {
            lvTimes.setEnabled( true );
        }*/
/*
        AdapterTime adapter = new AdapterTime(this, lista);

        lvTimes.setAdapter( adapter );*/

    }
}
