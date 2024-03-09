package com.example.da_app_music.Frament;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.da_app_music.Adapter.BaiHatHostAdapter;
import com.example.da_app_music.Model.BaiHat;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Bai_Hat_Host extends Fragment {

    View view;
    RecyclerView rvbaihathost;
    TextView xemthembaihat;
    ArrayList<BaiHat> mangbaihatduocthiches;
    BaiHatHostAdapter baiHatHostAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment__bai__hat__host, container, false);
        rvbaihathost = view.findViewById(R.id.rvbaihatnoibat);
        xemthembaihat = view.findViewById(R.id.txtxemthembaihat);
        Data();
        return view;
    }

    private void Data() {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<BaiHat>> callback = datAsrevice.GetDataBaiHatDuocThich();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
                rvbaihathost.setLayoutManager(layoutManager);
                mangbaihatduocthiches = (ArrayList<BaiHat>) response.body();
                baiHatHostAdapter = new BaiHatHostAdapter(getActivity(), android.R.layout.simple_list_item_1, mangbaihatduocthiches);
                rvbaihathost.setAdapter(baiHatHostAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}