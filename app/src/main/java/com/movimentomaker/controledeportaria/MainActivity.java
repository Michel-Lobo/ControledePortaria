package com.movimentomaker.controledeportaria;

import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.Registro;

public class MainActivity extends AppCompatActivity {
    private Button btnRegisrarEntrada;
    private Button btnRegistrarSaida;
    private FirebaseAuth auth;
    private Button btnSair;
    private TextView txtUsuarioLogado;

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
            txtUsuarioLogado = findViewById(R.id.txtUsuarioLogado);
            SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.nome_arquivo_setings).toString(), 0);
            txtUsuarioLogado.setText(sharedPreferences.getString("idUsuario", "0"));
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
