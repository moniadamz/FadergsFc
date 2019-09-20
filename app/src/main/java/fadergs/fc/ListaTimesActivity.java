package fadergs.fc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaTimesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_times);
        List<String> times = new ArrayList<>(Arrays.asList("Grêmio", "Inter", "Juventude", "Caxias","Grêmio", "Inter", "Juventude", "Caxias","Grêmio", "Inter", "Juventude", "Caxias","Grêmio", "Inter", "Juventude", "Caxias"));
        ListView listaTimes = findViewById(R.id.ActivityListaTimes);
        listaTimes.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, times));


    }
}
