                                                                                    package com.vagner.myapplication.dao;

public class ScriptDLL {

    public static String getCreateTableCliente(){

        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE cliente ( ");
        sql.append("    id       INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL,");
        sql.append("    endereco STRING (100) NOT NULL DEFAULT (''), ");
        sql.append("    email    STRING (100) NOT NULL DEFAULT (''), " );
        sql.append("    telefone STRING (50)  NOT NULL DEFAULT (''),  ");
        sql.append("    nome     STRING (100) NOT NULL DEFAULT ('') ); ");
        return sql.toString();
    }
}
