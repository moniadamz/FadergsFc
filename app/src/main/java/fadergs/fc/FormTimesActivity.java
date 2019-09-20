package fadergs.fc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FormTimesActivity extends AppCompatActivity {

    private EditText etNome;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_times);

        etNome = findViewById(R.id.activityFormTimesNome);
        btnSalvar = findViewById(R.id.activityFormTimesBtSalvar);

        setTitle("Adicionando Times");

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();

            }
        });

    }

    private void salvar() {
        String nome = etNome.getText().toString();

        if (nome.isEmpty()){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon( android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve inserir o nome do time.");
            alerta.setPositiveButton("OK", null);
            alerta.show();

        }else{
            Time timeCriado = new Time();
            timeCriado.setNome(nome);
            Toast.makeText(FormTimesActivity.this, timeCriado.getNome(),Toast.LENGTH_SHORT).show();

            TimeDAO.inserir(this, timeCriado);

//            Intent i = new Intent(FormTimesActivity.this, ListaTimesActivity.class);
//            startActivity( i );

            this.finish();

        }


    }


}
