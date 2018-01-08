package com.movimentomaker.controledeportaria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class PerfilActivity extends AppCompatActivity {
    private Button btnLogout;
private FirebaseAuth auth;
private TextView txtIdUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        btnLogout = findViewById(R.id.btnLogout);
        txtIdUser = findViewById(R.id.txtIdUser);
        Bundle bundle = new Bundle();
        txtIdUser.setText(bundle.get("idUser").toString());
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth = FirebaseAuth.getInstance();
                auth.signOut();
                startActivity(new Intent(PerfilActivity.this, LoginActivity.class));
            }
        });
    }
}
