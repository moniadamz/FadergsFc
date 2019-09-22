package fadergs.fc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {

    private Button activityFormJogadoresBtSalvar;
    private EditText nomeTime;
    private EditText nomeJogador;
    private EditText camisa;
    TimeDAO dao = new TimeDAO();
    private Time time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_jogadores);


        nomeTime = findViewById(R.id.activityFormJogadoresTime);

        Intent dadosTime = getIntent();
        Time time  = (Time) dadosTime.getSerializableExtra("time");
        nomeTime.setText(time.getNome());

/*
        activityFormJogadoresBtSalvar = findViewById(R.id.activityFormJogadoresBtSalvar);
        activityFormJogadoresBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FormularioActivity.this, MainActivity.class);
                startActivity( i );
            }
        });
*/

    }
}
