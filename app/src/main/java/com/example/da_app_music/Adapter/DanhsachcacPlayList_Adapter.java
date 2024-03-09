package com.example.da_app_music.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_app_music.Activity.DanhsachbaihatActivity;
import com.example.da_app_music.Model.Playlist;
import com.example.da_app_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DanhsachcacPlayList_Adapter extends RecyclerView.Adapter<DanhsachcacPlayList_Adapter.ViewHolder> {

   private Activity activity;
   private List<Playlist> mangplaylist;

    public DanhsachcacPlayList_Adapter(Activity activity, List<Playlist> mangplaylist) {
        this.activity = activity;
        this.mangplaylist = mangplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_cacdanhsachplaylist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = mangplaylist.get(position);
        Picasso.get().load(playlist.getHinhPlaylist()).into(holder.imageViewdanhsachplaylist);
        holder.txttenplaylist.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewdanhsachplaylist;
        TextView txttenplaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewdanhsachplaylist = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            txttenplaylist = itemView.findViewById(R.id.textviewtendanhsachplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",mangplaylist.get(getPosition()));
                    activity.startActivity(intent);
                }
            });
        }
    }
}
