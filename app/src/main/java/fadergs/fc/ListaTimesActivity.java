package fadergs.fc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaTimesActivity extends AppCompatActivity {

    private ListView lvTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_times);

        TimeDAO dao = new TimeDAO();

        setTitle("Lista de Times");

        ListView lvTimes = findViewById(R.id.ActivityListaTimes);


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListaTimesActivity.this, FormTimesActivity.class);
                startActivity( i );
            }
        });
    }


    private void carregarLista(){
        List<Time> lista = TimeDAO.getTimes(this);
        lvTimes.setEnabled( true );

/*
        if ( lista.size() == 0 ){
            lvProdutos.setEnabled( false );
            Produto fake = new Produto();
            fake.setQuantidade(0);
            fake.setNome("Lista Vazia!");
            lista.add( fake );
        }else {
            lvProdutos.setEnabled( true );
        }
*/

//        ArrayAdapter<Produto> adapter = new ArrayAdapter(
//                this, android.R.layout.simple_list_item_1,
//                lista);

/*
        AdapterProduto adapter = new AdapterProduto(this, lista);

        lvProdutos.setAdapter( adapter );
*/

    }
}
