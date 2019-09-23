package fadergs.fc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private Button registraTime;
    private Button pesquisaTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registraTime = findViewById(R.id.registraTime);
        pesquisaTime = findViewById(R.id.pesquisaTime);


        registraTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FormTimesActivity.class);
                startActivity( i );
            }
        });

        pesquisaTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListaTimesActivity.class);
                startActivity( i );
            }
        });
/*
        pesquisaTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Pesquisa.class);
                startActivity( i );
            }
        });
*/
    }


}