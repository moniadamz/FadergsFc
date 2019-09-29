package fadergs.fc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class FormularioActivity extends AppCompatActivity {

    private Button activityFormJogadoresBtSalvar;
    private TextView eTNomeTime;
    private EditText nomeJogador;
    private EditText camisa;
    private int idTime;
    private String nomeTime;

    TimeDAO dao = new TimeDAO();
    JogadorDAO daoJogador = new JogadorDAO();
    private Time time;
    private Jogador jogador;
    private List<Jogador> ListaJogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_jogadores);
        setTitle("Adicionando Jogadores ao time ");

        ListaJogadores = daoJogador.getJogadores(this);

        if (ListaJogadores.size() == 0) {

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
        }else{
            eTNomeTime = findViewById(R.id.jogadoresTime);
            camisa = findViewById(R.id.eTCamisaJogador);
            nomeJogador = findViewById(R.id.eTNomeJogador);

        }
    }
    private void salvar() {
        String nome = nomeJogador.getText().toString();
        String nCamisa = camisa.getText().toString();
        Log.i("entrou no salvar", "salvar: " + nome + nCamisa);

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

            startActivity(new Intent(FormularioActivity.this, ListaTimesActivity.class));

            finish();

        }
    }

}
