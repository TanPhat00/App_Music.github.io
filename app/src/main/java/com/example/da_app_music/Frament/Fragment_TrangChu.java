package com.example.da_app_music.Frament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.da_app_music.R;

public class Fragment_TrangChu extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trangchu,container,false);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();

        Fragment_Banner fragmentBanner = new Fragment_Banner();
        fragmentTransaction.replace(R.id.containerFragmentBanner,fragmentBanner);


        Frament_Playlist framentPlaylist = new Frament_Playlist();
        fragmentTransaction.replace(R.id.containerFragmentPlaylist,framentPlaylist);


        Fragment_ChuDe chudeFragment = new Fragment_ChuDe();
        fragmentTransaction.replace(R.id.containerFragmentChude,chudeFragment);

        Fragment_TheLoai theloaiFragment = new Fragment_TheLoai();
        fragmentTransaction.replace(R.id.containerFragmentTheloai,theloaiFragment);

        Fragment_AlbumHost fragmentAlbumHost = new Fragment_AlbumHost();
        fragmentTransaction.replace(R.id.containerFragmentAlbum,fragmentAlbumHost);

        Fragment_Bai_Hat_Host fragment_bai_hat_host = new Fragment_Bai_Hat_Host();
        fragmentTransaction.replace(R.id.containerFragmentbaihat_host,fragment_bai_hat_host);


        fragmentTransaction.commit();

        return view;
    }

}
