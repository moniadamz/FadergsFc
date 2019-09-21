package fadergs.fc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;

import java.util.List;

public class Pesquisa extends AppCompatActivity {
    private Button pesquisar;
    private EditText digitaTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
        pesquisar = findViewById(R.id.pesquisaTime);
        digitaTime = findViewById(R.id.digitaTime);

        setTitle("Pesquisa");

        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesquisar();

            }
        });
    }
    private void pesquisar(){
        String nome = digitaTime.getText().toString();
        Time time = TimeDAO.getTimeByName(this, nome);

        if ( time == null ){
            Time fake = new Time();
            fake.setNome("Item não encontrado.");
        }


    }
}
