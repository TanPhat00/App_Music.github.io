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

public class AlbumhostAdapter extends RecyclerView.Adapter<AlbumhostAdapter.AlbumHolder> {
   private Activity activity;
   private List<Album> mangalbum;
    private OnItemClickListener mListener;

    public AlbumhostAdapter(Activity activity,int simple_list_item_1 , List<Album> mangalbum) {
        this.activity = activity;
        this.mangalbum = mangalbum;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    // Phương thức để thiết lập trình nghe
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    class AlbumHolder extends RecyclerView.ViewHolder{
        ImageView imageviewalbum;
        TextView txttenalbum,tencasi;
        public AlbumHolder(@NonNull View itemView) {
            super(itemView);
            imageviewalbum = itemView.findViewById(R.id.imageviewalbums);
            txttenalbum = itemView.findViewById(R.id.txttitletenalbum);
            tencasi = itemView.findViewById(R.id.txttencasi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_album,parent,false);
        return new AlbumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {

        Album album = mangalbum.get(position);
        if (album != null && album.getHinhAlbum() != null){
            Picasso.get().load(album.getHinhAlbum()).into(holder.imageviewalbum);
            String tenalbum = album.getTenAlbum();
            if (tenalbum.length() > 15){
                tenalbum = tenalbum.substring(0, 15) + "...";
            }
            String tencasi = album.getTenCaSi();
            if (tencasi.length() > 15){
                tencasi = tencasi.substring(0,15) + "...";
            }
            holder.txttenalbum.setText(tenalbum);
            holder.tencasi.setText(tencasi);
        }

    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }


}
