package com.example.da_app_music.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_app_music.Model.Playlist;
import com.example.da_app_music.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

   private Activity activity;
   private List<Playlist> data;
    private OnItemClickListener mListener;

    public PlaylistAdapter(Activity activity,int simple_list_item_1, List<Playlist> data) {
        this.activity = activity;
        this.data = data;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    // Phương thức để thiết lập trình nghe
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenplaylist;
        ImageView imagebackgroundplaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenplaylist = itemView.findViewById(R.id.textviewtenplaylist);
            imagebackgroundplaylist = itemView.findViewById(R.id.imageviewbackgroundplaylist);

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
    public PlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.ViewHolder holder, int position) {
        Playlist playlist = data.get(position);
        if (playlist != null && playlist.getHinhPlaylist() != null){
            Picasso.get().load(playlist.getHinhPlaylist()).into(holder.imagebackgroundplaylist);
            holder.txttenplaylist.setText(playlist.getTen());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
