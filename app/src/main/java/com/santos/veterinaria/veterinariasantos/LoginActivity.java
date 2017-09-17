package com.santos.veterinaria.veterinariasantos;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {

    private TextView cadastrar;
    private EditText email, senha;
    private Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.email);
        senha = (EditText)findViewById(R.id.password);
        entrar = (Button)findViewById(R.id.entrar);

        entrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String emai = email.getText().toString();
                String senh = senha.getText().toString();
                login(emai,senh);
            }
        });

        cadastrar = (TextView)findViewById(R.id.quero_cadastrar);
        cadastrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this , CadastroActivity.class);
                startActivity(it);
            }
        });
    }

    public void login(String email, String senha){
        //Abertura do Banco de Dados
        SQLiteDatabase veterinaria = openOrCreateDatabase("veterinaria.db", Context.MODE_PRIVATE,null);
        //Montagem do SQL para SELECT
        String sql_select = "select * from animal_cliente where email = ? and senha = ?";
        String dados[] = new String[]{email,senha};
        //Execução da Consulta
        Cursor res = veterinaria.rawQuery(sql_select, dados);
        if (res.moveToFirst()){
            Intent it = new Intent(LoginActivity.this, ServicoActivity.class);
            startActivity(it);
            Log.d("DATABASE","RETURN TRUE");
        }else{
            Log.d("DATABASE","RETURN FALSE");
        }
    }


}

