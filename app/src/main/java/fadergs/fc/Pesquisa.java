package fadergs.fc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
        List<Time> time = TimeDAO.getTimeByName(this, nome);
        ListView listaTimes = (ListView) findViewById(R.id.ActivityResultadoPesquisa);
        if ( time.size() == 0 ){
            listaTimes.setEnabled( false );
            Time fake = new Time();
            fake.setNome("Lista Vazia!");
            time.add( fake );
        }else {
            listaTimes.setEnabled( true );
        }

        ArrayAdapter<Time> adapter = new ArrayAdapter<Time>(this, android.R.layout.simple_list_item_1, time);
        listaTimes.setAdapter(adapter);
    }
}
