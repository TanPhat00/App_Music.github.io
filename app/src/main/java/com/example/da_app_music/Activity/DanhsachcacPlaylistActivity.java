package com.example.da_app_music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.da_app_music.Adapter.DanhsachcacPlayList_Adapter;
import com.example.da_app_music.Model.Playlist;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacPlaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachcacplaylist;
    ArrayList<Playlist> mangplaylist = new  ArrayList<>();
    DanhsachcacPlayList_Adapter danhsachcacPlayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcac_playlist);
        anhxa();
        GetData();
        initt();
    }

    private void GetData() {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<Playlist>> callback = datAsrevice.GetDanhsachcacplaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(DanhsachcacPlaylistActivity.this,2);
                recyclerViewdanhsachcacplaylist.setLayoutManager(gridLayoutManager);
                mangplaylist = (ArrayList<Playlist>) response.body();
                danhsachcacPlayListAdapter = new DanhsachcacPlayList_Adapter(DanhsachcacPlaylistActivity.this,mangplaylist);
                recyclerViewdanhsachcacplaylist.setAdapter(danhsachcacPlayListAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
            }
        });
    }

    private void initt() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationIcon(null);
        AppCompatImageButton btnback = findViewById(R.id.btnback1);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbardanhsachcacplaylist);
        recyclerViewdanhsachcacplaylist = findViewById(R.id.rvdanhsachcacplaylist);
    }
}