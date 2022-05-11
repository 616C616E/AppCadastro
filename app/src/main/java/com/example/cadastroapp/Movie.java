package com.example.cadastroapp;

public class Movie {

    public static final int ANO_MINIMO = 1990;

    public int id;
    public String nome;
    public String categoria;
    public String analise;
    public boolean recomenda;

    private int ano;

    public Movie() {

    }

    public Movie(String nome, int ano, String categoria, String analise, boolean recomenda) {
        this.nome = nome;
        this.setAno( ano );
        this.categoria = categoria;
        this.analise = analise;
        this.recomenda = recomenda;

    }

    public Movie(int id, String nome, int ano, String categoria, String analise, boolean recomenda) {
        this.id = id;
        this.nome = nome;
        this.setAno(ano);
        this.categoria = categoria;
        this.analise = analise;
        this.recomenda = recomenda;

    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if( ano >= ANO_MINIMO )
            this.ano = ano;

    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public boolean getRecomenda(){
        return recomenda;
    }

    public void setRecomenda(boolean recomenda){
        this.recomenda = recomenda;
    }

    public String getAnalise() {return analise;}

    public void setAnalise(String analise) {this.analise = analise;}

    @Override
    public String toString() {
        return id + " - " + nome + " | " + ano + "|" + categoria + "|" + recomenda + "|" + analise;
    }
}