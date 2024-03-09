package com.example.da_app_music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;

import com.example.da_app_music.Adapter.DanhSachBaiHatAdapter;
import com.example.da_app_music.Model.Album;
import com.example.da_app_music.Model.BaiHat;
import com.example.da_app_music.Model.Playlist;
import com.example.da_app_music.Model.Quangcao;
import com.example.da_app_music.Model.TheLoai;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    Quangcao quangcao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView imageViewdanhsachbaihat;
    ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    AppBarLayout appBarLayout;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    Playlist playlist;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasnhachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        anhxa();
        Dataintent();
        initt();
        if (quangcao != null && !quangcao.getTenBaiHat().equals("")){
            setValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangcao(quangcao.getIdQuangcao());
        }
        if (playlist != null && !playlist.getTen().equals("")){
            setValueInView(playlist.getTen(),playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheloai());
            GetDataTheloai(theLoai.getIdTheLoai());
        }
        if (album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
        appBarLayout.addLiftOnScrollListener(new AppBarLayout.LiftOnScrollListener() {
            @Override
            public void onUpdate(float elevation, int backgroundColor) {
                // Lấy tham số layout của AppBarLayout để đặt các cờ cuộn
                AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();

                // Đặt các cờ cuộn cho CollapsingToolbarLayout
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
            }
        });
    }

    private void GetDataAlbum(String idalbum) {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<BaiHat>> callback = datAsrevice.Getdanhsachbaihattheoalbum(idalbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication(),RecyclerView.VERTICAL,false);
                recyclerViewdanhsachbaihat.setLayoutManager(layoutManager);
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivity.this, android.R.layout.simple_list_item_1,mangbaihat);
                recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {}
        });
    }

    private void GetDataTheloai(String idtheloai) {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<BaiHat>> callback = datAsrevice.Getdanhsachtheotheloai(idtheloai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication(),RecyclerView.VERTICAL,false);
                recyclerViewdanhsachbaihat.setLayoutManager(layoutManager);
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivity.this, android.R.layout.simple_list_item_1,mangbaihat);
                recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
            }
        });
    }
    private void GetDataPlaylist(String idplaylist) {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<BaiHat>> callback = datAsrevice.Getdanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(),RecyclerView.VERTICAL,false);
                recyclerViewdanhsachbaihat.setLayoutManager(linearLayoutManager);
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivity.this,android.R.layout.simple_list_item_1,mangbaihat);
                recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
            }
        });
    }

    private class DownloadTask extends AsyncTask<String, Void ,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            String imageUrl = strings[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap bitmap) {
            if (!isCancelled() && bitmap != null) {
                // Cập nhật ImageView với hình ảnh đã tải
                imageViewdanhsachbaihat.setImageBitmap(bitmap);
            }
        }
    }
    private void loadImage(String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(imageViewdanhsachbaihat);
        }
    }
        private void setValueInView(String tenBaiHat, String hinhBaiHat) {
            if (quangcao != null) {
                String imageUrl = quangcao.getHinhanh();
                new DownloadTask().execute(imageUrl);
                loadImage(imageUrl);
                collapsingToolbarLayout.setTitle(tenBaiHat);
            }else if (playlist != null){
                String imageUrl = playlist.getHinhPlaylist();
                loadImage(imageUrl);
                collapsingToolbarLayout.setTitle(tenBaiHat);
            } else if (theLoai != null){
                String imageUrl = theLoai.getHinhTheloai();
                loadImage(imageUrl);
                collapsingToolbarLayout.setTitle(tenBaiHat);
            }else if (album != null){
                String imageUrl = album.getHinhAlbum();
                collapsingToolbarLayout.setTitle(tenBaiHat);
                loadImage(imageUrl);
            }
        }
    private void GetDataQuangcao(String idquangcao){
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<BaiHat>> callback = datAsrevice.Getdanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                    mangbaihat = (ArrayList<BaiHat>) response.body();
                    danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivity.this,android.R.layout.simple_list_item_1,mangbaihat);
                    recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                    recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                    eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initt() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationIcon(null);
        AppCompatImageButton btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));
        floatingActionButton.setEnabled(false);
    }

    private void anhxa() {

        coordinatorLayout = findViewById(R.id.coordinatorlayoutdanhsachbaihat);
        collapsingToolbarLayout = findViewById(R.id.collapsingbar);
        toolbar = findViewById(R.id.toolbardanhsachbanner);
        recyclerViewdanhsachbaihat = findViewById(R.id.rvdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactiobbutton);
        imageViewdanhsachbaihat = findViewById(R.id.iddanhsachcakhuc);
        appBarLayout = findViewById(R.id.appbarlayout);
    }
    private void Dataintent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("banner")) {
            quangcao = (Quangcao) intent.getSerializableExtra("banner");
        }
            if (intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if (intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if (intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
            }
    }
    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhsachbaihatActivity.this, PlaynhacActivity.class);
                intent.putExtra("cacbaihat",mangbaihat);
                startActivity(intent);
            }
        });
    }
}