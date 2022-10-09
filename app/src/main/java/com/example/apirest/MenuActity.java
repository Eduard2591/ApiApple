package com.example.apirest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Objects;

public class MenuActity extends AppCompatActivity {

    ListView lista;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_actity);
        Objects.requireNonNull(getSupportActionBar()).hide();

        lista=findViewById(R.id.ListaMenu);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            String valor= extras.getString("extra");
            if (valor.equals("apps")){
                adaptador = new AppAdapter(this);
                lista.setAdapter(adaptador);
            }else{
                adaptador = new CategoryAdapter(this);
                lista.setAdapter(adaptador);
            }
        }

    }
}