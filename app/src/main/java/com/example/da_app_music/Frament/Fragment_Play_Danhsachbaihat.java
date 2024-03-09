package com.example.da_app_music.Frament;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.da_app_music.Activity.PlaynhacActivity;
import com.example.da_app_music.Adapter.Playnhac_Adapter;
import com.example.da_app_music.Model.BaiHat;
import com.example.da_app_music.R;

import java.util.ArrayList;


public class Fragment_Play_Danhsachbaihat extends Fragment {

    // ...


    RecyclerView recyclerViewdanhsachcacbaihat;
    View view;
    Playnhac_Adapter playnhac_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment__play__danhsachbaihat, container, false);
        anhxa();
        Data();
        return view;
    }

    private void Data() {
        if (PlaynhacActivity.mangbaihat.size() > 0){
            playnhac_adapter = new Playnhac_Adapter(getActivity(), PlaynhacActivity.mangbaihat);
            recyclerViewdanhsachcacbaihat.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewdanhsachcacbaihat.setAdapter(playnhac_adapter);
        }
    }

    private void anhxa() {
        recyclerViewdanhsachcacbaihat = view.findViewById(R.id.rvdanhsachcacbaihat);
    }
}