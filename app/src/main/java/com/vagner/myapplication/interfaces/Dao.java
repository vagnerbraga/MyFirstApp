package com.vagner.myapplication.interfaces;

import com.vagner.myapplication.model.Entidade;

import java.util.List;

public interface Dao<T extends Entidade> {

    public void inserir(T entidade);

    public void alterar(T entidade);

    public void excluir(Long id);

    public List<T> pesquisar(T entidade);

    public T selecionar(T entidade);

}
