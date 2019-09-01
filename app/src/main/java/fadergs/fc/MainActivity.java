package fadergs.fc;

import android.os.Bundle;
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
    }


}