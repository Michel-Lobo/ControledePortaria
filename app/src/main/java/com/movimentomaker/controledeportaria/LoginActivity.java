package com.movimentomaker.controledeportaria;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogar;
    private Button btnCadastrar;
    private EditText txtEmail;
    private EditText txtSenha;
    private FirebaseAuth auth;
    private GoogleSignInOptions gso;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnCadastrar = findViewById(R.id.btnCriarUsuario);
        btnLogar = findViewById(R.id.btnLogar);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        auth = FirebaseAuth.getInstance();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CadastroUsuarioActivity.class));
            }
        });
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(txtEmail.getText().toString(), txtSenha.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    //intent.putExtra("idUser", task.getResult().getUser().getUid().toString());
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(LoginActivity.this, "Usuário ou senha inválido", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

    }

}
