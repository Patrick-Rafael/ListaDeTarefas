package com.patrickrafael.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.patrickrafael.listadetarefas.R;
import com.patrickrafael.listadetarefas.helper.TarefaDAO;
import com.patrickrafael.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {
    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);

        //Recuperar tarefa, caso seja edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //Configurar tarefa na caixa de texto


    }


    //Configurações do Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_adicionar, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemSalvar:
                //Executa Ação

                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());


                if (tarefaAtual != null) {//Editar

                    String nomeTarefa = editTarefa.getText().toString();
                    if(!nomeTarefa.isEmpty()){


                         Tarefa tarefa = new Tarefa();
                         tarefa.setNomeTarefa( nomeTarefa);
                         tarefa.setId( tarefaAtual.getId() );

                         //Atualizar banco de dados
                        if(tarefaDAO.atualizar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao atualizar", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro  ao atualizar", Toast.LENGTH_LONG).show();
                        }

                    }


                } else {//salvar

                    String nomeTarefa = editTarefa.getText().toString();
                    if (!nomeTarefa.isEmpty()) {
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);

                       if( tarefaDAO.salvar(tarefa)){
                           Toast.makeText(getApplicationContext(), "Sucesso ao salvar Tarefa", Toast.LENGTH_LONG).show();
                           finish();

                       }else{
                           Toast.makeText(getApplicationContext(), "Erro ao salvar Tarefa", Toast.LENGTH_LONG).show();

                       }





                    }

                }


                break;
        }


        return super.onOptionsItemSelected(item);
    }

}