package com.example.da_app_music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.da_app_music.Adapter.DanhsachcacChuDe_Adapter;
import com.example.da_app_music.Model.ChuDe;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacChuDeActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerchude;
    ArrayList<ChuDe> mangchude = new ArrayList<>();
    DanhsachcacChuDe_Adapter danhssachchudeadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcac_chu_de);

        anhxa();
        initt();
        GetData();
    }

    private void GetData() {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<ChuDe>> callback = datAsrevice.Getdatatatcacchude();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(DanhsachcacChuDeActivity.this,2);
                recyclerchude.setLayoutManager(gridLayoutManager);
                mangchude = (ArrayList<ChuDe>) response.body();
                danhssachchudeadapter = new DanhsachcacChuDe_Adapter(DanhsachcacChuDeActivity.this,mangchude);
                recyclerchude.setAdapter(danhssachchudeadapter);
            }
            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }
    private void initt() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationIcon(null);
        AppCompatImageButton bnt2 = findViewById(R.id.btnback2);
        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.idanhsachcacchude);
        recyclerchude = findViewById(R.id.rvAllchude);
    }
}