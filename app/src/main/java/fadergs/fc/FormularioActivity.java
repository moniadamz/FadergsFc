package fadergs.fc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FormularioActivity extends AppCompatActivity {

    private Button salvaJogadores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        salvaJogadores = findViewById(R.id.salvaJogadores);

        salvaJogadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FormularioActivity.this, MainActivity.class);
                startActivity( i );
            }
        });
    }
}
