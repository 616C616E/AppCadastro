package com.example.cadastroapp;

public class Movie {

    public static final int ANO_MINIMO = 1990;

    public int id;
    public String nome;
    public String categoria;
    public String analise;

    private int ano;

    public Movie() {

    }

    public Movie(String nome, int ano, String categoria, String analise) {
        this.nome = nome;
        this.setAno( ano );
        this.categoria = categoria;
        this.analise = analise;

    }

    public Movie(int id, String nome, int ano, String categoria, String analise) {
        this.id = id;
        this.nome = nome;
        this.setAno(ano);
        this.categoria = categoria;
        this.analise = analise;

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

    public String getAnalise() {return analise;}

    public void setAnalise(String analise) {this.analise = analise;}

    @Override
    public String toString() {
        return id + " - " + nome + " | " + ano + "|" + categoria + "|" + analise;
    }
}