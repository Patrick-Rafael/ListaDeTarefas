package com.patrickrafael.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patrickrafael.listadetarefas.R;
import com.patrickrafael.listadetarefas.activity.AdicionarTarefaActivity;
import com.patrickrafael.listadetarefas.adapter.AdapterLsita;
import com.patrickrafael.listadetarefas.helper.DbHelper;
import com.patrickrafael.listadetarefas.helper.RecyclerItemClickListner;
import com.patrickrafael.listadetarefas.helper.TarefaDAO;
import com.patrickrafael.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private FloatingActionButton addFab;
    private RecyclerView recyclerView;
    private AdapterLsita adapter;
    private List<Tarefa> listaDeTarefas = new ArrayList<>();
    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFab = findViewById(R.id.fabAdicionar);
        recyclerView = findViewById(R.id.recyclerView);





        //Evento de click na lista

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListner(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Recupera tarefa para edição
                        Tarefa tarefaSelecionada = listaDeTarefas.get(position);

                        //Enviar para a tela adicionar tarefa

                        Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
                        intent.putExtra("tarefaSelecionada", tarefaSelecionada);

                        startActivity( intent );


                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        //Recupera tarefa
                        tarefaSelecionada = listaDeTarefas.get(position);


                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                        //Titulo e msg

                        dialog.setTitle("Confirmar Exclusão");
                        dialog.setMessage("Deseja excluir a tarefa: " + tarefaSelecionada.getNomeTarefa() + "?");

                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                                if(tarefaDAO.deletar(tarefaSelecionada)){
                                    carregarlistaTarefas();
                                    Toast.makeText(getApplicationContext(), "Sucesso ao excluir", Toast.LENGTH_LONG).show();



                                }else{
                                    Toast.makeText(getApplicationContext(), "Erro ao Excluir", Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                        dialog.setNegativeButton("Não", null);

                        dialog.create();
                        dialog.show();

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ) );

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                startActivity(intent);


            }
        });


    }



    public void carregarlistaTarefas(){


        //Configurar Adapter
        adapter = new AdapterLsita(listaDeTarefas);

        //Lista de tarefas
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listaDeTarefas = tarefaDAO.listar();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        carregarlistaTarefas();
        super.onStart();
    }


}