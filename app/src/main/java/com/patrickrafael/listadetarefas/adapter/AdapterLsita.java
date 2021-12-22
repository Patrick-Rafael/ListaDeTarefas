package com.patrickrafael.listadetarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickrafael.listadetarefas.R;
import com.patrickrafael.listadetarefas.model.Tarefa;

import java.util.List;

public class AdapterLsita extends RecyclerView.Adapter<AdapterLsita.MyViewHolder> {

    private List<Tarefa> listaTarefa;

    public AdapterLsita(List<Tarefa> lista) {
        this.listaTarefa = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_tarefa_adapter, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Tarefa tarefa = listaTarefa.get(position);
        holder.tarefa.setText(tarefa.getNomeTarefa());

    }

    @Override
    public int getItemCount() {
        return this.listaTarefa.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView tarefa;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefa = itemView.findViewById(R.id.textTarefa);
        }
    }


}
