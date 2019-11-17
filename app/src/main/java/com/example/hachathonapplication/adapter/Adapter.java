package com.example.hachathonapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hachathonapplication.R;
import com.example.hachathonapplication.model.Data;
import com.example.hachathonapplication.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.hachathonapplication.R.drawable.card_edge;
import static com.example.hachathonapplication.R.drawable.subscribe;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Data> list;
    private int rowLayout;
    int key;
    private Context context;
    HomeFragment homeFragment;
    public static List<Data> s=new ArrayList<>();

    private final OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Data item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView song,artist;
        ImageView image;
        Button play,subscribe;

        public ViewHolder(View v) {
            super(v);
            song=v.findViewById(R.id.song);
            artist=v.findViewById(R.id.artist);
            image=v.findViewById(R.id.image);

            subscribe=v.findViewById(R.id.subscribe);
            play=v.findViewById(R.id.buttonplay);
        }
        public void bind(final Data item, final OnItemClickListener listener) {
            play.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }
    }

    public Adapter(int key,List<Data> list,int rowLayout, Context context,OnItemClickListener listener) {
        this.list=list;
        this.key=key;
        this.rowLayout = rowLayout;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(list.get(position), listener);
        if(key==1) {
            holder.song.setText(list.get(position).getSong());

            Picasso.get().load(list.get(position).getCover_image()).into(holder.image);

            holder.artist.setText(list.get(position).getArtists());

            holder.subscribe.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {

                    holder.subscribe.setText("Subscribed");
                    holder.subscribe.setBackgroundResource(subscribe);

                    s.add(new Data(list.get(position).getUrl(), list.get(position).getSong(), list.get(position).getArtists(), list.get(position).getCover_image()));
                    for (int i = 0; i < s.size(); i++) {
                        Log.d("Subscribe ", s.get(i).getSong());
                    }

                }
            });
        }
        if(key==2){
            holder.song.setText(list.get(position).getSong());

            Picasso.get().load(list.get(position).getCover_image()).into(holder.image);

            holder.artist.setText(list.get(position).getArtists());

            holder.subscribe.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static List<Data> getSubscription(){
        return s;
    }
}
