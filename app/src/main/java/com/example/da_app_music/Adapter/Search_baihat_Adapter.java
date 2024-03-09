package com.example.da_app_music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_app_music.Activity.PlaynhacActivity;
import com.example.da_app_music.Model.BaiHat;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_baihat_Adapter extends RecyclerView.Adapter<Search_baihat_Adapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihat;
    private boolean[] likedStatus;

    public Search_baihat_Adapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
        likedStatus = new boolean[mangbaihat.size()]; // Khởi tạo mảng trạng thái thích
        Arrays.fill(likedStatus, false);// Mặc định tất cả các bài hát đều chưa được thích
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_search_baihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.txttenbaihat.setText(baiHat.getTenbaihat());
        holder.txttencasi.setText(baiHat.getCasi());
        Picasso.get().load(baiHat.getHinhbaihat()).into(holder.imagetimkiem);

    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttenbaihat,txttencasi;
        ImageView imagetimkiem;
        AppCompatImageButton like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenbaihat = itemView.findViewById(R.id.txttimkiemtenbaihat);
            txttencasi = itemView.findViewById(R.id.txttencasitimkiem);
            imagetimkiem = itemView.findViewById(R.id.imgtimkiembaihat);
            like = itemView.findViewById(R.id.btnliketrongtimkiem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlaynhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();// Lấy vị trí của bài hát trong danh sách
                    if (position != RecyclerView.NO_POSITION) {
                        if (!likedStatus[position]) { // Nếu bài hát chưa được thích
                            likedStatus[position] = true; // Đánh dấu bài hát đã được thích
                            like.setImageResource(R.drawable.baseline_timdat_24);
                            // Gửi yêu cầu cập nhật lượt thích
                            DATAsrevice datAsrevice = APIservice.getservice();
                            Call<String> callback = datAsrevice.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdbaihat());
                            callback.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    String ketqua = response.body();
                                    if (ketqua.equals("ok"))
                                    {
                                        Toast.makeText(context, "Đã thích bài hát", Toast.LENGTH_SHORT).show();
                                    }else
                                    {
                                        Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    imagetimkiem.setEnabled(false);
                                }
                            });
                        }else
                        {
                            likedStatus[position] = false;// Đánh dấu bài hát không được thích
                            like.setImageResource(R.drawable.baseline_timrong_border_24);
                            // Gửi yêu cầu cập nhật lượt thích (chức năng bỏ thích)
                            Toast.makeText(context, "Đã bỏ thích bài hát", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}
