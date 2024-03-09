package com.example.da_app_music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.da_app_music.Adapter.AllAlbum_Adapter;
import com.example.da_app_music.Model.Album;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaalbumActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rvalbum;
    ArrayList<Album> mangalbum;
    AllAlbum_Adapter allAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcaalbum);
        anhxa();
        initt();
        Data();
    }

    private void Data() {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<Album>> callback = datAsrevice.Gettatcaalbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                mangalbum = (ArrayList<Album>) response.body();
                allAlbumAdapter = new AllAlbum_Adapter(DanhsachtatcaalbumActivity.this,mangalbum);
                rvalbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaalbumActivity.this,2));
                rvalbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
            }
        });

    }

    private void initt() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationIcon(null);
        AppCompatImageButton btnback = findViewById(R.id.btnback4);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbaralbum);
        rvalbum = findViewById(R.id.rvalbum);
    }
}