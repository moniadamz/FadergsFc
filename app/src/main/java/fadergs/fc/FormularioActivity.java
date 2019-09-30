package fadergs.fc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FormularioActivity extends AppCompatActivity {

    private Button activityFormJogadoresBtSalvar;
    private TextView eTNomeTime;
    private EditText nomeJogador;
    private EditText camisa;
    private int idTime;
    private String nomeTime;

    private JogadorDAO daoJogador = new JogadorDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_jogadores);
        setTitle("Adicionando Jogadores ao time ");

//        ListaJogadores = daoJogador.getJogadores(this);
//        Log.i("Lista de jogadores", "onCreate: Buscando jogadores" + ListaJogadores);

//        if (ListaJogadores.size() == 0) {

        eTNomeTime = findViewById(R.id.jogadoresTime);
        camisa = findViewById(R.id.eTCamisaJogador);
        nomeJogador = findViewById(R.id.eTNomeJogador);

        idTime = getIntent().getExtras().getInt("time");
        nomeTime = getIntent().getExtras().getString("nomeTime");
        eTNomeTime.setText(nomeTime);

        Log.i("IdTime", "onCreate: " + idTime);

        activityFormJogadoresBtSalvar = findViewById(R.id.activityFormJogadoresBtSalvar);

        activityFormJogadoresBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
//        }else{
//            Log.i("Else", "onCreate: Entrou no else para digitar pegar times" + eTNomeTime);
//            eTNomeTime = findViewById(R.id.jogadoresTime);
//            camisa = findViewById(R.id.eTCamisaJogador);
//            nomeJogador = findViewById(R.id.eTNomeJogador);

//        }
    }

    @Override
    protected void onResume() {
        super.onResume();

//        configuraLista();
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    private void salvar() {
        String nome = nomeJogador.getText().toString();
        String nCamisa = camisa.getText().toString();
        Log.i("entrou no salvar", "salvar jogadores: " + nome + nCamisa);

        if (nome.isEmpty() || nCamisa.isEmpty()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon(android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve inserir o nome do jogador.");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        } else {
            Jogador jogadorCriado = new Jogador();
            jogadorCriado.setNome(nome);
            jogadorCriado.setCamisa(Integer.valueOf(nCamisa));
            jogadorCriado.setTime(idTime);

            daoJogador.inserir(this, jogadorCriado);
//            startActivity(new Intent(FormularioActivity.this, ListaTimesActivity.class));
            finish();
        }
    }

    private void carregarLista() {
//        List<Jogador> lista = JogadorDAO.getJogadoresById(this, idTime);

        ListView listaJogadores = findViewById(R.id.form_Activity_Lv_Jogadores);
        ArrayAdapter<Jogador> adapter = new ArrayAdapter<Jogador>(this, android.R.layout.simple_list_item_1, daoJogador.getJogadoresById(this, idTime));

        listaJogadores.setAdapter(adapter);
//        Log.i("Carregar lista", "carregarLista da dao: " + lista);
//        Log.i("carregar lista ", "carregarLista de jgadores: "+ listaJogadores);


    }
}