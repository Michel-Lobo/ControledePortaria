package com.movimentomaker.controledeportaria;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.movimentomaker.seguranca.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private Button btnCadastrar;
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtSenha;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        auth = FirebaseAuth.getInstance();

           btnCadastrar = findViewById(R.id.btnCadastrar);
            txtEmail = findViewById(R.id.txtEmail);
            txtNome = findViewById(R.id.txtNome);
            txtSenha = findViewById(R.id.txtSenha);


            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    auth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtSenha.getText().toString())
                            .addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        db = FirebaseDatabase.getInstance();
                                        dbRef = db.getReference("Usuario");
                                        dbRef.setValue(task.getResult().getUser().getUid());
                                        Usuario usuario = new Usuario();
                                        usuario.setId(task.getResult().getUser().getUid());
                                        usuario.setEmail(txtEmail.getText().toString());
                                        usuario.setNome(txtNome.getText().toString());
                                        dbRef.child(task.getResult().getUser().getUid()).setValue(usuario);

                                        Log.i("criado", task.getResult().getUser().getUid());
                                    } else {

                                        Log.i("problema", task.getException().getMessage());
                                    }
                                }
                            });
                }
            });

        }

    }

