package com.example.da_app_music.Frament;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.da_app_music.Activity.DanhsachbaihatActivity;
import com.example.da_app_music.Activity.DanhsachcacChuDeActivity;
import com.example.da_app_music.Activity.DanhsachtheloaitheochudeActivity;
import com.example.da_app_music.Adapter.ChuDeAdapter;
import com.example.da_app_music.Adapter.PlaylistAdapter;
import com.example.da_app_music.Model.ChuDe;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe extends Fragment {

    View view;
    TextView txtxemthemchudetheloai;
    RecyclerView recyclerView;
    ChuDeAdapter chuDeAdapter;
    ArrayList<ChuDe> mangchude;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment__chude, container, false);

        txtxemthemchudetheloai = view.findViewById(R.id.txtviewxemthemchude);
        recyclerView = view.findViewById(R.id.rvchude);


        GetData();
        return view;
    }

    private void GetData() {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<ChuDe>> callback = datAsrevice.GetDataChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
                recyclerView.setLayoutManager(layoutManager);
                mangchude = (ArrayList<ChuDe>) response.body();
                chuDeAdapter = new ChuDeAdapter(getActivity(), android.R.layout.simple_list_item_1,mangchude);
                recyclerView.setAdapter(chuDeAdapter);
                txtxemthemchudetheloai.setOnClickListener(new View
                        .OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), DanhsachcacChuDeActivity.class);
                        startActivity(intent);
                    }
                });
                chuDeAdapter.setOnItemClickListener(new PlaylistAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                        intent.putExtra("chude",mangchude.get(position));
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });

    }
}