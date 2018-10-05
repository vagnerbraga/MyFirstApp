package com.vagner.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vagner.myapplication.R;
import com.vagner.myapplication.model.Cliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {


    private final List<Cliente> lista;

    public ClienteAdapter(List<Cliente> lista){
        this.lista = lista;
    }

    @Override
    public ClienteAdapter.ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_view, parent, false);

        ClienteViewHolder holder = new ClienteViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ClienteViewHolder holder, int position) {
        Cliente c = this.lista.get(position);

        holder.nome.setText(c.nome);
        holder.endereco.setText(c.endereco);
        holder.email.setText(c.email);
        holder.telefone.setText(c.telefone);
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public static class ClienteViewHolder extends RecyclerView.ViewHolder{

        protected TextView nome;
        protected TextView endereco;
        protected TextView email;
        protected TextView telefone;

        public ClienteViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.cv_tv_nome);
            endereco = (TextView) itemView.findViewById(R.id.cv_tv_endereco);
            email = (TextView) itemView.findViewById(R.id.cv_tv_email);
            telefone = (TextView) itemView.findViewById(R.id.cv_tv_telefone);
        }
    }
}
