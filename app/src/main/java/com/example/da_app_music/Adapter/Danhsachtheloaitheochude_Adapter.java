package com.example.da_app_music.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_app_music.Activity.DanhsachbaihatActivity;
import com.example.da_app_music.Model.TheLoai;
import com.example.da_app_music.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Danhsachtheloaitheochude_Adapter extends RecyclerView.Adapter<Danhsachtheloaitheochude_Adapter.ViewHolder> {
    private Context context;
    private List<TheLoai> data;


    public Danhsachtheloaitheochude_Adapter(Context context, List<TheLoai> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloai_theo_chude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = data.get(position);
            Picasso.get().load(theLoai.getHinhTheloai()).into(holder.imgtheloaitheochude);
            holder.txttentheloai.setText(theLoai.getTenTheLoai());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgtheloaitheochude;
        TextView txttentheloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgtheloaitheochude = itemView.findViewById(R.id.imgtheloaitheochude);
            txttentheloai = itemView.findViewById(R.id.txtviewtentheloai);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idtheloai",data.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
