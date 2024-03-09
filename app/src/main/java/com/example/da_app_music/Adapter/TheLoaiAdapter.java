package com.example.da_app_music.Adapter;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_app_music.Model.TheLoai;
import com.example.da_app_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {

private Activity activity;
private List<TheLoai> data;
    private PlaylistAdapter.OnItemClickListener mListener;

    public TheLoaiAdapter(Activity activity,int simple_list_item_1 , List<TheLoai> data) {
        this.activity = activity;
        this.data = data;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    // Phương thức để thiết lập trình nghe
    public void setOnItemClickListener(PlaylistAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageviewTheloai;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageviewTheloai = itemView.findViewById(R.id.imageviewchude);
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_chude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = data.get(position);
        if (theLoai != null && theLoai.getHinhTheloai() != null){
            Picasso.get().load(theLoai.getHinhTheloai()).into(holder.imageviewTheloai);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
