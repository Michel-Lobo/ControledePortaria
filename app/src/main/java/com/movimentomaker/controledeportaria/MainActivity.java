package com.movimentomaker.controledeportaria;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button btnRegisrarEntrada;
    private Button btnRegistrarSaida;
    private FirebaseAuth auth;
    private Button btnSair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));

        }else{
            btnRegisrarEntrada = findViewById(R.id.btnRegistrarEntrada);
            btnRegistrarSaida = findViewById(R.id.btnRegistrarSaida);
            btnSair = findViewById(R.id.btnSair);
            btnRegisrarEntrada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, RegistraEntradaActivity.class));
                }
            });
            btnSair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    auth.signOut();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
        }
    }

}
