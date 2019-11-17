package com.example.hachathonapplication.ui.home;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hachathonapplication.R;
import com.example.hachathonapplication.adapter.Adapter;
import com.example.hachathonapplication.model.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment {
    List<Data> list=new ArrayList<>();
    List<String> subscrptions=new ArrayList<String>();
    Call<List<Data>> call;
    LayoutAnimationController controller;
    RecyclerView recyclerView;
    HttpConnectionGetData httpConnectionGetData;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = (RecyclerView) root.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        controller =
                AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_leftto_right);
        try{
            httpConnectionGetData = new HttpConnectionGetData(HomeFragment.this);
            httpConnectionGetData.execute();

        }
        catch (Exception e){

        }
        return root;
    }


    public void callback(){
        try{
            Log.d("Response", httpConnectionGetData.getResponse());

            String res= httpConnectionGetData.getResponse();

            JSONArray arr = new JSONArray(res);

            for(int i = 0; i < arr.length(); i++){
                list.add(new Data(arr.getJSONObject(i).getString("song"),arr.getJSONObject(i).getString("url"),arr.getJSONObject(i).getString("artists"),arr.getJSONObject(i).getString("cover_image")));
            }
            for (int i=0;i<list.size();i++){
                Log.d("Data",list.get(i).getUrl());
            }

            recyclerView.setAdapter(new Adapter(1,list, R.layout.redycle_songs_layout, getContext(), new Adapter.OnItemClickListener() {
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
                    }
                    catch (Exception e){

                    }
                }
            }));
            recyclerView.setLayoutAnimation(controller);

            recyclerView.scheduleLayoutAnimation();

        }
        catch (Exception e){
        }
    }
    public  void callfunctionSubscribe(String song){
        try {
            subscrptions.add(song);
            Log.d("Subscription Song", song);
        }
        catch (Exception e){

        }
    }
}