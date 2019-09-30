package fadergs.fc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
        setTitle(getResources().getString(R.string.titleAppBarPlayer));

        eTNomeTime = findViewById(R.id.jogadoresTime);
        camisa = findViewById(R.id.eTCamisaJogador);
        nomeJogador = findViewById(R.id.eTNomeJogador);

        idTime = getIntent().getExtras().getInt("time");
        nomeTime = getIntent().getExtras().getString("nomeTime");
        eTNomeTime.setText(nomeTime);

        activityFormJogadoresBtSalvar = findViewById(R.id.activityFormJogadoresBtSalvar);

        activityFormJogadoresBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    private void salvar() {
        String nome = nomeJogador.getText().toString();
        String nCamisa = camisa.getText().toString();

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
            finish();
        }
    }

    private void carregarLista() {

        ListView listaJogadores = findViewById(R.id.form_Activity_Lv_Jogadores);
        ArrayAdapter<Jogador> adapter = new ArrayAdapter<Jogador>(this, android.R.layout.simple_list_item_1, daoJogador.getJogadoresById(this, idTime));

        listaJogadores.setAdapter(adapter);
        listaJogadores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                excluir((Jogador) adapterView.getItemAtPosition(posicao));
                return true;
            }
        });
    }
    private void excluir(final Jogador jogador){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(getResources().getString(R.string.alertDelete));
        alerta.setMessage(getResources().getString(R.string.alertMessage) + jogador.getNome() + "?");
        alerta.setNeutralButton(getResources().getString(R.string.alertNeutralButton), null);
        alerta.setPositiveButton(getResources().getString(R.string.positiveButton), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                JogadorDAO.excluir(FormularioActivity.this, jogador.getId());
                carregarLista();
            }
        });
        alerta.show();

    }

}