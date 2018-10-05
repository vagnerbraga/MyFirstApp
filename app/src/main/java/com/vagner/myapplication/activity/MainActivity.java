package com.vagner.myapplication.activity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.vagner.myapplication.R;
import com.vagner.myapplication.adapter.ClienteAdapter;
import com.vagner.myapplication.dao.ClienteDao;
import com.vagner.myapplication.dao.EstruturaOpenHelper;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase con;
    private EstruturaOpenHelper bancoDados;
    private ConstraintLayout layoutActivePrincipal;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter clienteAdapter;
    private ClienteDao clienteDao;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        this.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CadastroCliente.class);
                startActivity(it);
            }
        });

        this.clienteDao = new ClienteDao(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.layoutActivePrincipal = (ConstraintLayout) findViewById(R.id.layoutActivePrincipal);

        this.recyclerView = (RecyclerView) findViewById(R.id.recycleViewCliente);

        this.clienteAdapter = new ClienteAdapter(this.clienteDao.pesquisar(null));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        this.recyclerView.setAdapter(this.clienteAdapter);
    }

    private void criarConexao(){
        try {

            this.bancoDados = new EstruturaOpenHelper(this);

            this.con = this.bancoDados.getWritableDatabase();

            Snackbar.make(this.layoutActivePrincipal, "Conexao criada com sucesso", Snackbar.LENGTH_SHORT).setAction("OK", null).show();


        }catch (SQLException sqlE){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);

            dlg.setTitle("Erro");
            dlg.setMessage(sqlE.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }
    }




}
