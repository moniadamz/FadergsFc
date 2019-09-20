package fadergs.fc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FormularioActivity extends AppCompatActivity {

    private Button activityFormJogadoresBtSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_jogadores);

        activityFormJogadoresBtSalvar = findViewById(R.id.activityFormJogadoresBtSalvar);

        activityFormJogadoresBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FormularioActivity.this, MainActivity.class);
                startActivity( i );
            }
        });
    }
}
