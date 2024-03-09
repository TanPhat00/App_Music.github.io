package com.example.da_app_music.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_app_music.Model.BaiHat;
import com.example.da_app_music.R;

import java.util.ArrayList;
import java.util.List;

public class Playnhac_Adapter extends RecyclerView.Adapter<Playnhac_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<BaiHat> mangbaihat;

    public Playnhac_Adapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_playbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baihat = mangbaihat.get(position);
        holder.txttencasi.setText(baihat.getCasi());
        holder.txttenbaihat.setText(baihat.getTenbaihat());
        holder.txtindex.setText(position + 1 +"");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtindex,txttenbaihat,txttencasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.txtplaynhacindex);
            txttenbaihat = itemView.findViewById(R.id.txtplaynhactenbaihat);
            txttencasi = itemView.findViewById(R.id.txttencasi);
        }
    }

}
