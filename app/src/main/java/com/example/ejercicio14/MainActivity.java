package com.example.ejercicio14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnTomarfoto,btnListarfoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnTomarfoto=(Button) findViewById(R.id.btnTomarFto);
        btnListarfoto=(Button) findViewById(R.id.btnListarFtos);

        btnTomarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivityTomarfoto.class);
                startActivity(intent);
            }
        });

        btnListarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivityListarFotografiaPersona.class);
                startActivity(intent);
            }
        });
    }
}