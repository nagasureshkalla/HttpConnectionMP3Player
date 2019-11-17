package com.example.hachathonapplication.ui.slideshow;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hachathonapplication.R;
import com.example.hachathonapplication.adapter.Adapter;
import com.example.hachathonapplication.model.Data;

import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);


        final RecyclerView recyclerView=root.findViewById(R.id.subscribe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        try {
            List<Data> list = Adapter.getSubscription();

            Adapter adapter=new Adapter(2,list, R.layout.redycle_songs_layout, getContext(), new Adapter.OnItemClickListener() {
                @Override
                public void onItemClick(Data item) {
                    //Toast.makeText(getContext(), item.getUrl(), Toast.LENGTH_LONG).show();

                    try {
                        String url = item.getUrl(); // your URL here
                        MediaPlayer mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDataSource(url);
                        mediaPlayer.prepare(); // might take long! (for buffering, etc)
                        mediaPlayer.start();
                    } catch (Exception e) {

                    }
                }

            });
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        catch (Exception e){

        }
        return root;
    }
}