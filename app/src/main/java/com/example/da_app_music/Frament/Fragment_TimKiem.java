package com.example.da_app_music.Frament;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_app_music.Adapter.Search_baihat_Adapter;
import com.example.da_app_music.Model.BaiHat;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TimKiem extends Fragment {
    View view;

    Toolbar toolbar;
    RecyclerView rvtimkiembaihat;
    TextView txtkhongcodulieu;
    Search_baihat_Adapter search_baihat_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timkiem,container,false);
        anhxa();

        return view;
    }



    private void anhxa() {
        toolbar = view.findViewById(R.id.toolbartiimkiem);
        rvtimkiembaihat = view.findViewById(R.id.rvtimkiembaihat);
        txtkhongcodulieu = view.findViewById(R.id.txtnovalue);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.searchview,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        if (menuItem != null){
            SearchView searchView = (SearchView) menuItem.getActionView();
            if (searchView != null){
                EditText searchedittext = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
                if (searchedittext != null){
                    searchedittext.setTextColor(Color.WHITE);

                }
            }
            if (searchView != null){
                searchView.setMaxWidth(Integer.MAX_VALUE);
                ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
                searchIcon.setColorFilter(Color.WHITE);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        SearchTuKhoaBaiHat(query);
                        return true;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        SearchTuKhoaBaiHat(newText);
                        return true;
                    }
                });
            }
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void SearchTuKhoaBaiHat(String query) {
        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<BaiHat>> callback = datAsrevice.GetDataSearchBaiHat(query);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangbaihat = (ArrayList<BaiHat>) response.body();
                if (mangbaihat.size() > 0){
                    search_baihat_adapter = new Search_baihat_Adapter(getActivity(),mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
                    rvtimkiembaihat.setLayoutManager(linearLayoutManager);
                    rvtimkiembaihat.setAdapter(search_baihat_adapter);
                    txtkhongcodulieu.setVisibility(View.GONE);
                    rvtimkiembaihat.setVisibility(View.VISIBLE);
                }else {
                    rvtimkiembaihat.setVisibility(View.GONE);
                    txtkhongcodulieu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
