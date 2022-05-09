package com.example.cadastroapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public static void inserir(Movie filme, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome", filme.nome );
        valores.put("ano", filme.getAno() );
        valores.put("categoria", filme.getCategoria().toString());
        valores.put("analise", filme.getAnalise().toString());

        DB banco = new DB(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("movie", null, valores);
    }

    public static void editar(Movie filme, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome", filme.nome );
        valores.put("ano", filme.getAno() );
        valores.put("categoria", filme.getCategoria().toString());
        valores.put("analise", filme.getAnalise().toString());

        DB banco = new DB(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("movie", valores, " id = " + filme.id , null );
    }

    public static void excluir(int id, Context context){
        DB banco = new DB(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("movie", " id = " + id , null);
    }

    public static List<Movie> getFilmes(Context context){
        List<Movie> lista = new ArrayList<>();
        DB banco = new DB(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, ano, categoria, analise FROM movie ORDER BY nome", null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Movie filme = new Movie();
                filme.id = cursor.getInt( 0);
                filme.nome = cursor.getString(1);
                filme.setAno( cursor.getInt(2) );
                filme.setCategoria(cursor.getString(3));
                filme.setAnalise(cursor.getString(4));
                lista.add( filme );
            }while( cursor.moveToNext() );
        }
        return lista;
    }

    public static Movie getFilmeById(Context context, int id){
        DB banco = new DB(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, ano, categoria, analise FROM movie WHERE id = " + id, null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            Movie filme = new Movie();
            filme.id = cursor.getInt( 0);
            filme.nome = cursor.getString(1);
            filme.setAno( cursor.getInt(2) );
            filme.setCategoria(cursor.getString(3));
            filme.setAnalise(cursor.getString(4));
            return filme;
        }else{
            return null;
        }
    }



}
