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

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListaTimesActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Times";
    private TimeDAO dao = new TimeDAO();
    private ListView lvTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_times);

        setTitle(TITULO_APPBAR);

        configuraFabNovoTime();
    }

    private void configuraFabNovoTime() {
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormTimeActivity();
            }
        });
    }

    private void abreFormTimeActivity() {
        startActivity( new Intent(this, FormTimesActivity.class) );
    }

    @Override
    protected void onResume() {
        super.onResume();

        configuraLista();
    }

    @NotNull
    private void configuraLista() {
        ListView listaTimes = findViewById(R.id.ActivityListaTimes);
        final List<Time> times = dao.getTimes(this);
        listaTimes.setAdapter( new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, times));

        listaTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Time timeClicado = times.get(posicao);
                Intent formJogadores = new Intent(ListaTimesActivity.this, FormularioActivity.class);
                formJogadores.putExtra("time", timeClicado);
//                formJogadores.putExtra("nomeJogador", timeClicado);
//                formJogadores.putExtra("numeroCamisa", timeClicado);
                startActivity(formJogadores);

                Log.i("lista", "onItemClick:"+ timeClicado);
            }
        });

        listaTimes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                excluir((Time) adapterView.getItemAtPosition(posicao));
                return true;
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

        ListView listaTimes = findViewById(R.id.ActivityListaTimes);
        ArrayAdapter<Time> adapter = new ArrayAdapter<Time>(this, android.R.layout.simple_list_item_1, dao.getTimes(this));

        listaTimes.setAdapter(adapter);
        Log.i("Carregar lista", "carregarLista: " + lista);

    }
}
