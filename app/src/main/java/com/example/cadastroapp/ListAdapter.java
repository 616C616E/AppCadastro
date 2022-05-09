package com.example.cadastroapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<Movie> movieList;
    private Context context;
    private LayoutInflater inflater;

    public ListAdapter(Context context, List<Movie> listaMovies){
        this.movieList = listaMovies;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int i) {
        return movieList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return movieList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemSuporte item;

        if( convertView == null){
            convertView = inflater.inflate(R.layout.layout_list, null);

            item = new ItemSuporte();
            item.tvNome = convertView.findViewById(R.id.tvListaNome);
            item.tvAno = convertView.findViewById(R.id.tvListaAno);
            item.tvCategoria = convertView.findViewById(R.id.tvListaCategoria);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag( item );
        }else {
            item = (ItemSuporte) convertView.getTag();
        }

        Movie movie = movieList.get(i);
        item.tvNome.setText(  movie.nome );
        item.tvCategoria.setText(String.valueOf(movie.getCategoria()));
        item.tvAno.setText(  String.valueOf( movie.getAno() ) );

        if( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }else {
            item.layout.setBackgroundColor( Color.WHITE );
        }
        return convertView;
    }

    private class ItemSuporte{
        TextView tvNome, tvAno, tvCategoria;
        LinearLayout layout;
    }


}