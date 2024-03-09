package com.example.da_app_music.Frament;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_app_music.Activity.DanhsachbaihatActivity;
import com.example.da_app_music.Activity.DanhsachcacPlaylistActivity;
import com.example.da_app_music.Adapter.PlaylistAdapter;
import com.example.da_app_music.Model.Playlist;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Frament_Playlist extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView titleplaylist,txtxemthemplaylist;
    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> mangplaylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        recyclerView = view.findViewById(R.id.rvviewplaylist);
        titleplaylist = view.findViewById(R.id.textviewtitlebannerbaihat);
        txtxemthemplaylist = view.findViewById(R.id.textviewviewmoreplaylist);
        GetData();
        txtxemthemplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachcacPlaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void GetData() {

        DATAsrevice datAsrevice = APIservice.getservice();
        Call<List<Playlist>> callback = datAsrevice.GetPlaylistCurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                mangplaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(), android.R.layout.simple_list_item_1,mangplaylist);
                recyclerView.setAdapter(playlistAdapter);
                playlistAdapter.setOnItemClickListener(new PlaylistAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        // Xử lý nhấp vào mục ở đây
                        Playlist playlist = mangplaylist.get(position);
                        Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                        intent.putExtra("itemplaylist", playlist);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
            }
        });
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                             RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
