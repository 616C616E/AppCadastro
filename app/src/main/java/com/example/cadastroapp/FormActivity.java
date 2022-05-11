package com.example.cadastroapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    private EditText etNome;
    private Spinner spAno;
    private Spinner spCategorias;
    private EditText etAnalise;
    private Button btnSalvar;
    private Button btnExcluir;
    private String acao;
    private Movie movie;
    private boolean recomenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etNome = findViewById( R.id.etNome );
        spAno = findViewById( R.id.spAno );
        spCategorias = findViewById(R.id.spCategorias);
        etAnalise = findViewById(R.id.etAnalise);
        btnSalvar = findViewById( R.id.btnSalvar );
        btnExcluir = findViewById(R.id.btnExcluir);

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluir();
            }
        });

    }

    private void carregarFormulario(){
        int idFilme = getIntent().getIntExtra("idFilme", 0);
        if( idFilme != 0) {
            movie = MovieDAO.getFilmeById(this, idFilme);

            etNome.setText( movie.nome );
            etAnalise.setText(movie.analise);
            String [] arrayCategorias = getResources().getStringArray(R.array.arrCategorias);
            for (int i=1; i < arrayCategorias.length; i++){
                if (movie.getCategoria().equals(arrayCategorias[i])){
                    spCategorias.setSelection(i);
                    break;
                }
            }
            String[] arrayAno = getResources().getStringArray(R.array.arrAno);
            for(int i = 1; i < arrayAno.length ; i++){
                if( Integer.valueOf( arrayAno[i] ) == movie.getAno()){
                    spAno.setSelection( i );
                    break;
                }
            }
            if(movie.recomenda){
                RadioButton rdRecomenda = findViewById(R.id.rdRecomendo);
                rdRecomenda.setChecked(true);
            } else {
                RadioButton rdNRecomenda = findViewById(R.id.rdNRecomendo);
                rdNRecomenda.setChecked(true);
            }
        }
    }

    private void salvar(){
        if( spAno.getSelectedItemPosition() == 0 || etNome.getText().toString().isEmpty() || spCategorias.getSelectedItemPosition() == 0 || etAnalise.getText().toString().isEmpty() ) {

            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

        }else{

            if (acao.equals("novo")) {
                movie = new Movie();
            }

            movie.nome = etNome.getText().toString();
            movie.setAno( Integer.valueOf( spAno.getSelectedItem().toString()  ) );
            movie.setCategoria(spCategorias.getSelectedItem().toString());
            movie.analise = etAnalise.getText().toString();
            movie.setRecomenda(recomenda);

            if( acao.equals("editar")){
                MovieDAO.editar (movie, this);
                finish();

            }else {
                MovieDAO.inserir(movie, this);
                etNome.setText("");
                spAno.setSelection(0);
                spCategorias.setSelection(0, true);
                etAnalise.setText("");
            }
            Intent intent = new Intent(FormActivity.this, MainActivity.class);
            startActivity( intent );
        }
    }

    private void excluir() {
        if (spAno.getSelectedItemPosition() == 0 || etNome.getText().toString().isEmpty() || spCategorias.getSelectedItemPosition() == 0 || etAnalise.getText().toString().isEmpty()) {

            Toast.makeText(this, "Não é possível excluir um item que não foi cadastrado.", Toast.LENGTH_SHORT).show();

        } else {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon(android.R.drawable.ic_input_delete);
            alerta.setTitle(R.string.txtAtencao);
            alerta.setMessage("Confirma a exclusão do filme " + movie.nome+"?");
            alerta.setNeutralButton("Cancelar", null);
            alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MovieDAO.excluir( movie.id, FormActivity.this);
                    etNome.setText("");
                    spAno.setSelection(0);
                    spCategorias.setSelection(0, true);
                    etAnalise.setText("");
                    Intent intent = new Intent(FormActivity.this, MainActivity.class);
                    startActivity( intent );
                }
            });
            alerta.show();
        }

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rdRecomendo:
                if (checked)
                    recomenda = true;
                    break;
            case R.id.rdNRecomendo:
                if (checked)
                    recomenda = false;
                    break;
        }
    }
}