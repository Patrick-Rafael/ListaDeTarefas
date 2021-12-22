package com.patrickrafael.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patrickrafael.listadetarefas.R;
import com.patrickrafael.listadetarefas.activity.AdicionarTarefaActivity;
import com.patrickrafael.listadetarefas.adapter.AdapterLsita;
import com.patrickrafael.listadetarefas.helper.RecyclerItemClickListner;
import com.patrickrafael.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private FloatingActionButton addFab;
    private RecyclerView recyclerView;
    private AdapterLsita adapter;
    private List<Tarefa> listaDeTarefas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFab = findViewById(R.id.fabAdicionar);


        //Evento de click na lista

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListner(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListner.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )


        );


        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                startActivity(intent);

            }
        });


    }


    @Override
    protected void onStart() {
        carregarlistaTarefas();
        super.onStart();
    }

    public void carregarlistaTarefas(){

        //Configurar Recycler
        recyclerView = findViewById(R.id.recyclerView);

        //Configurar Adapter
        adapter = new AdapterLsita(listaDeTarefas);

        //Lista de tarefas estatica

        Tarefa tarefa1 = new Tarefa();
        tarefa1.setNomeTarefa("Ir ao mercado");
        listaDeTarefas.add(tarefa1);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setNomeTarefa("Ir a feira");
        listaDeTarefas.add(tarefa2);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);


    }
}