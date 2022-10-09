package com.example.apirest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    static final String[] titulos_menu = new String[]{"Listado de Apps", "categorias"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_ApiRest);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        lista = findViewById(R.id.lista);

        ArrayAdapter adaptador = new Menu_Adapter(this,titulos_menu);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    Intent intent= new Intent(MainActivity.this,MenuActity.class);
                    intent.putExtra("extra", "apps");
                    startActivity(intent);
                }else {
                    Intent intent= new Intent(MainActivity.this,MenuActity.class);
                    intent.putExtra("extra", "category");
                    startActivity(intent);
                }
            }
        });



    }
}