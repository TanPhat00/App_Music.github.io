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
import com.example.da_app_music.Adapter.PlaylistAdapter;
import com.example.da_app_music.Adapter.TheLoaiAdapter;
import com.example.da_app_music.Model.TheLoai;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_TheLoai extends Fragment {

    TextView txtxemthemtheloai;
    RecyclerView rvviewtheloai;
    TheLoaiAdapter theLoaiAdapter;
    ArrayList<TheLoai> data;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_the_loai, container, false);
        anhxa();
        GetData();
        return view;
    }

    private void GetData() {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<TheLoai>> callback = datAsrevice.GetDataTheLoai();
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
                rvviewtheloai.setLayoutManager(layoutManager);
                data = (ArrayList<TheLoai>) response.body();
                theLoaiAdapter = new TheLoaiAdapter(getActivity(), android.R.layout.simple_list_item_1,data);
                rvviewtheloai.setAdapter(theLoaiAdapter);
                theLoaiAdapter.setOnItemClickListener(new PlaylistAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        TheLoai theLoai = data.get(position);
                        Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                        intent.putExtra("idtheloai",theLoai);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
            }
        });
    }

    private void anhxa() {

        txtxemthemtheloai = view.findViewById(R.id.txtxemthemtheloai);
        rvviewtheloai = view.findViewById(R.id.rvtheloai);

    }
}