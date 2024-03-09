package com.example.da_app_music.Frament;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.da_app_music.Model.BaiHat;
import com.example.da_app_music.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;


public class Fragment_Dia_nhac extends Fragment {
    View view;
    ImageView imgphatnhac,backgrounddianhac;
//    MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac, container, false);
        imgphatnhac = view.findViewById(R.id.imagephatnhac);
        backgrounddianhac = view.findViewById(R.id.backgrounddianhac);
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        return view;
    }


    public void PlayNhac( final String hinhanh) {
//        try {
//            mediaPlayer.setDataSource(getContext(), Uri.parse(linkNhac));
//            mediaPlayer.prepareAsync();
//            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.start();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(hinhanh).into(imgphatnhac);
                            Picasso.get().load(hinhanh).into(backgrounddianhac);
                        }
                    }, 1000);
                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//      }
    }
