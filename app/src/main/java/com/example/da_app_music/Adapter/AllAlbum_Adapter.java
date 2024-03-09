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
import com.example.da_app_music.Model.Album;
import com.example.da_app_music.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllAlbum_Adapter extends RecyclerView.Adapter<AllAlbum_Adapter.ViewHolder>{
    Activity activity;
    List<Album> mangalbum;

    public AllAlbum_Adapter(Activity activity, List<Album> mangalbum) {
        this.activity = activity;
        this.mangalbum = mangalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_all_album,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = mangalbum.get(position);
        if (album != null && album.getHinhAlbum() != null){
            Picasso.get().load(album.getHinhAlbum()).into(holder.imageallalbum);
            holder.tenallalbum.setText(album.getTenAlbum());
        }
    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageallalbum;
        TextView tenallalbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageallalbum = itemView.findViewById(R.id.imageviewdanhsachcacalbum);
            tenallalbum = itemView.findViewById(R.id.textviewtendanhsachalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, DanhsachbaihatActivity.class);
                    intent.putExtra("album",mangalbum.get(getPosition()));
                    activity.startActivity(intent);
                }
            });
        }
    }

}
