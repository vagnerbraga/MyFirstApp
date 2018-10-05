package com.vagner.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.vagner.myapplication.interfaces.Dao;
import com.vagner.myapplication.model.Cliente;
import com.vagner.myapplication.util.Conexao;

import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements Dao<Cliente> {

    private SQLiteDatabase con;
    private final String tabela = "cliente";

    public ClienteDao(Context context){
        this.con = Conexao.getInstance(context);
    }

    @Override
    public void inserir(Cliente entidade) {

        long idCliente = this.con.insertOrThrow(this.tabela, null, this.getContentValues(entidade));
        entidade.id = idCliente;

    }

    @Override
    public void alterar(Cliente entidade) {

        String[] parametros = new String[1];
        parametros[0] = entidade.id.toString();

        this.con.update(this.tabela, this.getContentValues(entidade), "id = ? ", parametros);
    }

    @Override
    public void excluir(Long id) {
        String[] parametros = new String[1];
        parametros[0] = id.toString();

        this.con.delete(this.tabela,"id = ? ", parametros);
    }

    @Override
    public List<Cliente> pesquisar(Cliente entidade) {

        List<Cliente> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, endereco, telefone, email ");
        sql.append(" from cliente ");
        sql.append("");

        Cursor resultado = this.con.rawQuery(sql.toString(), null);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();

            do {
                Cliente c = new Cliente();

                c.id = resultado.getLong(resultado.getColumnIndexOrThrow("id"));
                c.nome = resultado.getString(resultado.getColumnIndexOrThrow("nome"));
                c.endereco = resultado.getString(resultado.getColumnIndexOrThrow("endereco"));
                c.email = resultado.getString(resultado.getColumnIndexOrThrow("email"));
                c.telefone = resultado.getString(resultado.getColumnIndexOrThrow("telefone"));

                lista.add(c);

            }while (resultado.moveToNext());

        }


        return lista;
    }

    @Override
    public Cliente selecionar(Cliente entidade) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, endereco, telefone, email ");
        sql.append(" from cliente ");
        sql.append(" where id = ? ");

        String[] parametros = new String[1];
        parametros[0] = entidade.id.toString();

        Cursor resultado = this.con.rawQuery(sql.toString(), parametros);

        if(resultado.getCount() > 0){
            Cliente c = new Cliente();

            resultado.moveToFirst();

            c.id = resultado.getLong(resultado.getColumnIndexOrThrow("id"));
            c.nome = resultado.getString(resultado.getColumnIndexOrThrow("nome"));
            c.endereco = resultado.getString(resultado.getColumnIndexOrThrow("endereco"));
            c.email = resultado.getString(resultado.getColumnIndexOrThrow("email"));
            c.telefone = resultado.getString(resultado.getColumnIndexOrThrow("telefone"));

            return c;
        }

        return null;
    }

    @NonNull
    private ContentValues getContentValues(Cliente entidade) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("nome", entidade.nome);
        contentValues.put("email", entidade.email);
        contentValues.put("telefone", entidade.telefone);
        contentValues.put("endereco", entidade.endereco);
        return contentValues;
    }

}
