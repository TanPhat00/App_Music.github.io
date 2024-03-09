package com.example.da_app_music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da_app_music.Adapter.ViewPagePlaylistnhac_Adapter;
import com.example.da_app_music.Frament.Fragment_Play_Danhsachbaihat;
import com.example.da_app_music.Frament.Fragment_Dia_nhac;
import com.example.da_app_music.Model.BaiHat;
import com.example.da_app_music.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlaynhacActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txttimesong,txtTotaltimesong;
    SeekBar sktime;
    AppCompatImageButton imagerandom,imgbtnprevious,imgbtnplaycircle,imgbtnnext,imagexoaybai;
    ViewPager viewPager;
    Fragment_Dia_nhac dianhac;
    Fragment_Play_Danhsachbaihat fragmentPlayDanhsachbaihat;

    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;


    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static ViewPagePlaylistnhac_Adapter adapternhac;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playnhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        anhxa();
        GetDataFromIntent();
        eventclick();
    }
    private void eventclick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) != null){
                    if (mangbaihat.size() > 0){
                        dianhac.PlayNhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,500);
                    }
                }
            }
        },1000);
        imgbtnplaycircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgbtnplaycircle.setImageResource(R.drawable.baseline_play_circle_24);
                }else {
                    mediaPlayer.start();
                    imgbtnplaycircle.setImageResource(R.drawable.baseline_pause_circle_24);
                }
            }
        });
        imagexoaybai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false){
                    if (checkrandom == true){
                        checkrandom = false;
                        imagexoaybai.setImageResource(R.drawable.baseline_sync_problem_24);
                        imagerandom.setImageResource(R.drawable.shuffle);
                    }
                    imagexoaybai.setImageResource(R.drawable.baseline_sync_problem_24);
                    repeat = true;
                }else {
                    imagexoaybai.setImageResource(R.drawable.baseline_sync_24);
                    repeat = false;
                }
            }
        });
        imagerandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false){
                    if (repeat == true){
                        repeat = false;
                        imagerandom.setImageResource(R.drawable.random);
                        imagexoaybai.setImageResource(R.drawable.baseline_sync_24);
                    }
                    imagerandom.setImageResource(R.drawable.random);
                    checkrandom = true;
                }else {
                    imagerandom.setImageResource(R.drawable.shuffle);
                    checkrandom = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgbtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())){
                        imgbtnplaycircle.setImageResource(R.drawable.baseline_pause_circle_24);
                        position++;
                        if (repeat == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position -=1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size() -1 )){
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                            dianhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                            getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        CapNhatThoiGian();
                    }
                }
                imgbtnprevious.setClickable(false);
                imgbtnnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgbtnprevious.setClickable(true);
                        imgbtnnext.setClickable(true);
                    }
                },3000);
            }
        });
        imgbtnprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())){
                        imgbtnplaycircle.setImageResource(R.drawable.baseline_pause_circle_24);
                        position--;
                        if (position < 0){
                            position = mangbaihat.size() -1;
                        }
                        if (repeat == true){
                            position +=1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        dianhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        CapNhatThoiGian();
                    }
                }
                imgbtnprevious.setClickable(false);
                imgbtnnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgbtnprevious.setClickable(true);
                        imgbtnnext.setClickable(true);
                    }
                },3000);
            }
        });
    }
    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null)
        {
            if (intent.hasExtra("cakhuc")){
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                if (baiHat != null){
                    mangbaihat.add(baiHat);
                    getSupportActionBar().setTitle(baiHat.getTenbaihat());
                    prepareAndPlaySong(baiHat);
                }else {
                    resetData();
                }
            }
            if (intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> mangbaihatArraylist = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = mangbaihatArraylist;
                if (!mangbaihat.isEmpty()) {
                    getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
                    prepareAndPlaySong(mangbaihat.get(0));
                } else {
                    // Nếu danh sách bài hát rỗng, thực hiện reset dữ liệu
                    resetData();
                }
            }
        }
    }
    private void prepareAndPlaySong(BaiHat baiHat) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        new PlayMp3().execute(baiHat.getLinkbaihat());
        dianhac.PlayNhac(baiHat.getHinhbaihat());
        imgbtnplaycircle.setImageResource(R.drawable.baseline_pause_circle_24);
    }
    private void resetData() {
        // Thực hiện reset dữ liệu ở đây
        Toast.makeText(getApplicationContext(), "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
    }
    private void anhxa() {
        toolbar = findViewById(R.id.toolbarplaynhac);
        txttimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarplaynhac);
        imagerandom = findViewById(R.id.imagerandom);
        imgbtnprevious = findViewById(R.id.imgbtnprevious);
        imgbtnplaycircle = findViewById(R.id.imgbtnplaycircle);
        imgbtnnext = findViewById(R.id.imgbtnnext);
        imagexoaybai = findViewById(R.id.imagexoaybai);
        viewPager = findViewById(R.id.viewpagerplaynhac);
//        btnback5 = findViewById(R.id.btnback5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios1_24);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        fragmentPlayDanhsachbaihat = new Fragment_Play_Danhsachbaihat();
        dianhac = new Fragment_Dia_nhac();
        adapternhac = new ViewPagePlaylistnhac_Adapter(getSupportFragmentManager());
        adapternhac.AddFragment(fragmentPlayDanhsachbaihat);
        adapternhac.AddFragment(dianhac);
        viewPager.setAdapter(adapternhac);
        dianhac = (Fragment_Dia_nhac) adapternhac.getItem(1);

        if (!mangbaihat.isEmpty()) {
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgbtnplaycircle.setImageResource(R.drawable.baseline_pause_circle_24);
        }
    }
    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                }
            });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.start();
            TimeSong();
            CapNhatThoiGian();
        }
    }
    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void CapNhatThoiGian(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txttimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        },300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < (mangbaihat.size())) {
                        imgbtnplaycircle.setImageResource(R.drawable.baseline_pause_circle_24);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size() - 1)) {
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        dianhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                    }
                imgbtnprevious.setClickable(false);
                imgbtnnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgbtnprevious.setClickable(true);
                        imgbtnnext.setClickable(true);
                    }
                 }, 3000);
                next = false;
                handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
