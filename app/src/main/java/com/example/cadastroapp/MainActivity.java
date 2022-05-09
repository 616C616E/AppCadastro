package com.example.cadastroapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvFilmes;
    private ListAdapter adapter;
    private List<Movie> listaMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("acao", "novo");
                startActivity( intent );
            }
        });


        lvFilmes = findViewById(R.id.lvFilmes);

        carregarFilmes();

        configurarListView();

    }

    private void configurarListView(){

        lvFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie movieSelecionado = listaMovies.get(position);
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idFilme", movieSelecionado.id );
                startActivity( intent );
            }
        });

        lvFilmes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie movieSelecionado = listaMovies.get(position);
                excluirFilme(movieSelecionado);
                return true;
            }
        });

    }



    private void excluirFilme( Movie movie){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(R.string.txtAtencao);
        alerta.setMessage("Confirma a exclusão do filme " + movie.nome+"?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MovieDAO.excluir( movie.id, MainActivity.this);
                carregarFilmes();
            }
        });
        alerta.show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        carregarFilmes();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void carregarFilmes(){
        listaMovies = MovieDAO.getFilmes(this);
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaFilmes);
        adapter = new ListAdapter(this, listaMovies);
        lvFilmes.setAdapter( adapter );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}