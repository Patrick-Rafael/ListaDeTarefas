package com.patrickrafael.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.textfield.TextInputEditText;
import com.patrickrafael.listadetarefas.R;
import com.patrickrafael.listadetarefas.helper.TarefaDAO;
import com.patrickrafael.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {
    private TextInputEditText editTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);

    }





    //Configurações do Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        getMenuInflater().inflate(R.menu.menu_adicionar, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemSalvar:
                //Executa Ação
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                Tarefa tarefa = new Tarefa();
                tarefa.setNomeTarefa("Ir ao mercado");
                tarefaDAO.salvar(tarefa);


                break;
        }




        return super.onOptionsItemSelected(item);
    }

}