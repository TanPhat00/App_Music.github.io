package com.example.da_app_music.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_app_music.Model.ChuDe;
import com.example.da_app_music.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChuDeAdapter extends RecyclerView.Adapter<ChuDeAdapter.ViewHolder> {

    private Activity activity;
    private List<ChuDe> mangChude;
    private PlaylistAdapter.OnItemClickListener mListener;



    public ChuDeAdapter(Activity activity, int simple_list_item_1, List<ChuDe> mangChude) {
        this.activity = activity;
        this.mangChude = mangChude;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    // Phương thức để thiết lập trình nghe
    public void setOnItemClickListener(PlaylistAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewChude;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewChude = itemView.findViewById(R.id.imageviewchude);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_chude, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chude = mangChude.get(position);
        if (chude != null && chude.getHinhChuDe() != null) {
            Picasso.get().load(chude.getHinhChuDe()).into(holder.imageViewChude);
        }
    }

    @Override
    public int getItemCount() {
        return mangChude.size();
    }
}
