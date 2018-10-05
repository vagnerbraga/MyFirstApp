package com.vagner.myapplication.activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vagner.myapplication.R;
import com.vagner.myapplication.dao.ClienteDao;
import com.vagner.myapplication.model.Cliente;

public class CadastroCliente extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtEmail;
    private EditText edtTelefone;
    private ConstraintLayout constraintLayout;
    private ClienteDao clienteDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        edtNome = (EditText) findViewById(R.id.editNome);
        edtEndereco = (EditText) findViewById(R.id.editEndereco);
        edtEmail = (EditText) findViewById(R.id.editEmail);
        edtTelefone = (EditText) findViewById(R.id.editTelefone);

        this.constraintLayout = (ConstraintLayout) findViewById(R.id.layoutCadastroCliente);
        this.clienteDao = new ClienteDao(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activiy_cadastro_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case R.id.menu_ok:
                validaCampos();

                Cliente cli = new Cliente();
                cli.nome = edtNome.getText().toString();
                cli.endereco = edtEndereco.getText().toString();
                cli.email = edtEmail.getText().toString();
                cli.telefone = edtTelefone.getText().toString();

                this.clienteDao.inserir(cli);
                cli = new Cliente();
                this.limparCampos();
                Toast.makeText(this,"Cliente cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_voltar:
                Toast.makeText(this,"Cadastro descartado", Toast.LENGTH_SHORT).show();
                break;
        }
        finish();


        return super.onOptionsItemSelected(item);
    }

    private void validaCampos(){

        String nome     = edtNome.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String email    = edtEmail.getText().toString();
        String telefone = edtTelefone.getText().toString();

        if(isCampoVazia(nome)){
            this.mensagem("Campo nome requerido");
            edtNome.requestFocus();
        }else if(isCampoVazia(endereco)){
            this.mensagem("Campo enderecço requerido");
            edtEndereco.requestFocus();
        }else if(!isEmailvalido(email)){
            this.mensagem("Campo email não é valido");
            edtEmail.requestFocus();
        }else if(isCampoVazia(telefone)){
            this.mensagem("Campo telefone requerido");
            edtTelefone.requestFocus();
        }

    }

    private void mensagem(String msg){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle(R.string.str_aviso);
        dlg.setMessage(msg);
        dlg.setNeutralButton(R.string.str_ok, null);
        dlg.show();
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private boolean isCampoVazia(String valor){
        return TextUtils.isEmpty(valor.trim()); // valor== null || valor.isEmpty();
    }

    private boolean isEmailvalido(String valor){
        return Patterns.EMAIL_ADDRESS.matcher(valor).matches();
    }

    public void limparCampos(){
        this.edtNome.setText("");
        this.edtEndereco.setText("");
        this.edtEmail.setText("");
        this.edtTelefone.setText("");

    }

}
