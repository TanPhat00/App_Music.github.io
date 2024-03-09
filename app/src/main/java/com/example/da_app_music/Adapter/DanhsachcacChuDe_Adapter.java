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

import com.example.da_app_music.Activity.DanhsachtheloaitheochudeActivity;
import com.example.da_app_music.Model.ChuDe;
import com.example.da_app_music.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DanhsachcacChuDe_Adapter extends RecyclerView.Adapter<DanhsachcacChuDe_Adapter.ViewHolder> {
   private Activity activity;
   private List<ChuDe> mangchude;

    public DanhsachcacChuDe_Adapter(Activity activity, List<ChuDe> mangchude) {
        this.activity = activity;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_danhsachcac_chude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chude = mangchude.get(position);
        Picasso.get().load(chude.getHinhChuDe()).into(holder.imageviewchude);
        holder.txttenchude.setText(chude.getTenChuDe());
    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageviewchude;
        TextView txttenchude;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageviewchude = itemView.findViewById(R.id.imageviewdanhsachcacchude);
            txttenchude = itemView.findViewById(R.id.textviewtendanhsachchude);
            imageviewchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, DanhsachtheloaitheochudeActivity.class);
                    intent.putExtra("chude" , mangchude.get(getPosition()));
                    activity.startActivity(intent);
                }
            });
        }
    }
}
