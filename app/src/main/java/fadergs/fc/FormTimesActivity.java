package fadergs.fc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FormTimesActivity extends AppCompatActivity {

    private Button criaTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_times);
        criaTimes = findViewById(R.id.criaTimes);

        criaTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FormTimesActivity.this, ListaTimesActivity.class);
                startActivity( i );
            }
        });

    }
}
