package com.example.arsalankhan.savingdataintofirebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arsalan khan on 5/26/2017.
 */

public class MyAdapter extends ArrayAdapter<Artist> {
    private Context context;
    ArrayList<Artist> artistArrayList;
    public MyAdapter(Context context,ArrayList<Artist> list){
        super(context,R.layout.single_row,list);
        this.context=context;
        artistArrayList=list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.single_row,null,false);

        TextView tv_name= (TextView) view.findViewById(R.id.tv_name);
        TextView tv_artist= (TextView) view.findViewById(R.id.tv_artist);
        Artist artist=artistArrayList.get(position);
        tv_name.setText(artist.getName());
        tv_artist.setText(artist.getArtist());
        return view;

    }
}
