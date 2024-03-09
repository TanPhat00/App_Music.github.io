package com.example.da_app_music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.da_app_music.Adapter.Danhsachtheloaitheochude_Adapter;
import com.example.da_app_music.Model.ChuDe;
import com.example.da_app_music.Model.TheLoai;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {
    ChuDe chude;
    Toolbar tbtheloaitheochude;
    RecyclerView rvtheloaitheochude;
    ArrayList<TheLoai> mangtheloai;
    Danhsachtheloaitheochude_Adapter danhsachtheloaitheochudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);
        anhxa();
        Getintent();
        initt();
        GetData();
    }
    private void GetData() {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<TheLoai>> callback = datAsrevice.Gettheloaitheochude(chude.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                mangtheloai = (ArrayList<TheLoai>) response.body();
                danhsachtheloaitheochudeAdapter = new Danhsachtheloaitheochude_Adapter(
                        DanhsachtheloaitheochudeActivity.this,mangtheloai);
                rvtheloaitheochude.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this,2));
                rvtheloaitheochude.setAdapter(danhsachtheloaitheochudeAdapter);
            }
            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
            }
        });
    }
    private void initt() {
        setSupportActionBar(tbtheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        tbtheloaitheochude.setNavigationIcon(null);
        AppCompatImageButton btnback = findViewById(R.id.btnback3);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void anhxa() {
        tbtheloaitheochude = findViewById(R.id.toolbertheloaithechude);
        rvtheloaitheochude = findViewById(R.id.rvtheloaitheochude);
    }

    private void Getintent() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")){
            chude = (ChuDe) intent.getSerializableExtra("chude");

        }
    }
}