package com.movimentomaker.controledeportaria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import models.Registro;

public class RegistraEntradaActivity extends AppCompatActivity{

    private Button btnRegistrarCartao;
    private Button btnFoto;
    private IntentIntegrator qrScan;
    private EditText txtCartao;
    private EditText txtNome;
    private EditText txtRgCpf;
    private Button btnRegistrar;
    private ImageView imgFoto;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_entrada);
        btnRegistrarCartao = findViewById(R.id.btnRegistrarCartao);
        qrScan = new IntentIntegrator(this);
        txtCartao = findViewById(R.id.txtCartao);
        txtNome = findViewById(R.id.txtNome);
        txtRgCpf = findViewById(R.id.txtRG);
        imgFoto = findViewById(R.id.imgFoto);
        btnRegistrar = findViewById(R.id.btnRegistrarEntrada);
        sharedPreferences = getSharedPreferences(getResources().getString(R.string.nome_arquivo_setings).toString(), 0);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Registro registro = new Registro();

                registro.setIdUsuario(sharedPreferences.getString("idUsuario", "0"));
                registro.setNomeVisitante(txtNome.getText().toString());
                registro.setNumeroCartao(txtCartao.getText().toString());
                registro.setRgCPF(txtRgCpf.getText().toString());
                registro.setDataHoraEntrada(ServerValue.TIMESTAMP.toString());
registrarEntrada(registro);
            }
        });

        btnRegistrarCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.initiateScan();
            }
        });
        btnFoto= findViewById(R.id.btnRegistrarFoto);



        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(camera.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(camera, REQUEST_IMAGE_CAPTURE);

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Leitura não realizada", Toast.LENGTH_LONG).show();
            } else {
                    txtCartao.setText(result.getContents().toString());
                super.onActivityResult(requestCode, resultCode, data);

                }
            }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgFoto.setImageBitmap(imageBitmap);
        }

    }
    private Registro registrarEntrada(Registro registro) {
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("Registro");
        dbRef.setValue(registro).addOnCompleteListener(RegistraEntradaActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                   return task.getResult().getClass();
                }
            }
        });
    }
}
