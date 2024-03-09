package com.example.da_app_music.Frament;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.da_app_music.Activity.DanhsachbaihatActivity;
import com.example.da_app_music.Activity.DanhsachtatcaalbumActivity;
import com.example.da_app_music.Adapter.AlbumhostAdapter;
import com.example.da_app_music.Model.Album;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_AlbumHost extends Fragment {
View view;
RecyclerView recyclerViewalbum;
TextView txtalbum,txtxemthemalbum;

AlbumhostAdapter albumadapter;
ArrayList<Album> mangalbum;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     view = inflater.inflate(R.layout.fragment__album_host, container, false);

     recyclerViewalbum = view.findViewById(R.id.rvalbum);
     txtalbum = view.findViewById(R.id.txttitlealbum);
     txtxemthemalbum = view.findViewById(R.id.idxemthemalbum);
     txtxemthemalbum.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(getActivity(), DanhsachtatcaalbumActivity.class);
             startActivity(intent);
         }
     });
     Data();
        return view;
    }

    private void Data() {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<Album>> callback = datAsrevice.GetDataAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                LinearLayoutManager layoutalbum = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
                recyclerViewalbum.setLayoutManager(layoutalbum);
                mangalbum = (ArrayList<Album>) response.body();
                albumadapter = new AlbumhostAdapter(getActivity(), android.R.layout.simple_list_item_1,mangalbum);
                recyclerViewalbum.setAdapter(albumadapter);
                albumadapter.setOnItemClickListener(new AlbumhostAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Album album = mangalbum.get(position);
                        Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                        intent.putExtra("album",album);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}