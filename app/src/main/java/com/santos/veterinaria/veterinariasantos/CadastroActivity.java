package com.santos.veterinaria.veterinariasantos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CadastroActivity extends AppCompatActivity {

    private EditText nomeAnimal, dataNascimento, raca;
    private EditText nomeCliente, email, telefone, senha;
    private Button cadastrar;

    private String nomeAnima, dataNasciment, rac;
    private String nomeClient, emai, telefon, senh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializarWidgets();
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomeAnima = nomeAnimal.getText().toString();
                dataNasciment = dataNascimento.getText().toString();
                rac = raca.getText().toString();
                nomeClient = nomeCliente.getText().toString();
                emai = email.getText().toString();
                telefon = telefone.getText().toString();
                senh = senha.getText().toString();

                //Abertura do Banco de Dados
                SQLiteDatabase dbVeterinaria = openOrCreateDatabase("veterinaria.db", Context.MODE_PRIVATE,null);
                //Montagem do SQL para inserção
                String sql_insert = "insert into animal_cliente (nome,raca,nascimento,dono,email,telefone,senha) values (?,?,?,?,?,?,?)";
                Object dados[] = new Object[]{nomeAnima,rac,dataNasciment,nomeClient,emai,telefon,senh};
                //Execução da Inserção
                dbVeterinaria.execSQL(sql_insert,dados);
                //Retorno ao usuário
                Toast.makeText(CadastroActivity.this,"Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void inicializarWidgets(){
        nomeAnimal = (EditText)findViewById(R.id.nome_animal);
        dataNascimento = (EditText)findViewById(R.id.data_animal);
        raca = (EditText)findViewById(R.id.raca);
        nomeCliente = (EditText)findViewById(R.id.dono_animal);
        email = (EditText)findViewById(R.id.email_cadastro);
        telefone = (EditText)findViewById(R.id.telefone);
        senha = (EditText)findViewById(R.id.senha);
        cadastrar = (Button)findViewById(R.id.cadastrar);
    }
}
