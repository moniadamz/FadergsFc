package fadergs.fc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FormularioActivity extends AppCompatActivity {

    private Button activityFormJogadoresBtSalvar;
    private EditText nomeTime;
    private EditText nomeJogador;
    private EditText camisa;
    private EditText idTime;
    TimeDAO dao = new TimeDAO();
    JogadorDAO daoJogador = new JogadorDAO();
    private Time time;
    private Jogador jogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_jogadores);
        setTitle("Adicionando Jogadores ao time ");

        nomeTime = findViewById(R.id.activityFormJogadoresTime);
        nomeJogador = findViewById(R.id.activityFormJogadoresNome);
        camisa = findViewById(R.id.activityFormJogadoresCamisa);

        Intent dadosTime = getIntent();
        Time time  = (Time) dadosTime.getSerializableExtra("time");
        nomeTime.setText(time.getNome());

        Intent dadosJogadores = getIntent();
//        Jogador jogador = (Jogador) dadosJogadores.getSerializableExtra("nomeJogador");
//        nomeJogador.setText(jogador.getNome());
//        nomeJogador.setText(jogador.getCamisa());

        activityFormJogadoresBtSalvar = findViewById(R.id.activityFormJogadoresBtSalvar);

        activityFormJogadoresBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();

//                Intent i = new Intent(FormularioActivity.this, MainActivity.class);
//                startActivity( i );
            }
        });
    }

    private void salvar() {
        String nome = nomeJogador.getText().toString();
        String nCamisa = camisa.getText().toString();
        Log.i("entrou no salvar", "salvar: "+nome + nCamisa);

        if (nome.isEmpty() || nCamisa.isEmpty() ){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon( android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve inserir o nome do jogador.");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        }else{
            Jogador jogadorCriado = new Jogador();
            jogadorCriado.setNome(nome);
            jogadorCriado.setCamisa( Integer.valueOf(nCamisa));
            Log.i("criou jogador", "salvar: " + jogadorCriado);

            daoJogador.inserir(this, jogadorCriado, time);

            startActivity( new Intent(FormularioActivity.this, ListaTimesActivity.class) );

            finish();

        }
    }
}
