package fadergs.fc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText digitaTime;
    private Button registraTime;
    private Button pesquisaTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        digitaTime = (EditText) findViewById(R.id.digitaTime);
        registraTime = (Button) findViewById(R.id.registraTime);
        pesquisaTime = (Button) findViewById(R.id.pesquisaTime);

        registraTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity( i );
            }
        });
    }


}