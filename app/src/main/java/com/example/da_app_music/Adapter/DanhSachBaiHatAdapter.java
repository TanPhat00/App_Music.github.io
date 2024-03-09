package com.example.da_app_music.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_app_music.Activity.PlaynhacActivity;
import com.example.da_app_music.Model.BaiHat;
import com.example.da_app_music.R;
import com.example.da_app_music.Srevice.APIservice;
import com.example.da_app_music.Srevice.DATAsrevice;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder> {

    private Activity activity;
    private List<BaiHat> mangbaihat;
    private boolean[] likedStatus;

    public DanhSachBaiHatAdapter(Activity activity, int simple_list_item_1 , List<BaiHat> mangbaihat) {
        this.activity = activity;
        this.mangbaihat = mangbaihat;
        likedStatus = new boolean[mangbaihat.size()]; // Khởi tạo mảng trạng thái thích
        Arrays.fill(likedStatus, false);// Mặc định tất cả các bài hát đều chưa được thích
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_danh_sach_baihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        if(baiHat != null && baiHat.getHinhbaihat() != null){
            holder.txtindex.setText(position + 1 +"");
            holder.txttenbaihat.setText(baiHat.getTenbaihat());
            String tencasi = baiHat.getCasi();
            if (tencasi.length() > 20){
                tencasi = tencasi.substring(0,20) + "...";
            }
            holder.txttencasi.setText(tencasi);
            Picasso.get().load(baiHat.getHinhbaihat()).into(holder.imageViewhinhbaihatquangcao);

        }
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex,txttenbaihat,txttencasi;
        ImageView imageluotthich,imageViewhinhbaihatquangcao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.danhsachindex);
            txttenbaihat = itemView.findViewById(R.id.txttitletenbaihatquangcao);
            txttencasi = itemView.findViewById(R.id.txttencasicuabaihatquangcao);
            imageViewhinhbaihatquangcao = itemView.findViewById(R.id.imageviewdanhsachquangcao);
            imageluotthich = itemView.findViewById(R.id.luotthich);
            imageluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();// Lấy vị trí của bài hát trong danh sách
                    if (position != RecyclerView.NO_POSITION) {
                        if (!likedStatus[position]) { // Nếu bài hát chưa được thích
                            likedStatus[position] = true; // Đánh dấu bài hát đã được thích
                            imageluotthich.setImageResource(R.drawable.baseline_timdat_24);
                            // Gửi yêu cầu cập nhật lượt thích
                            DATAsrevice datAsrevice = APIservice.getservice();
                            Call<String> callback = datAsrevice.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdbaihat());
                            callback.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    String ketqua = response.body();
                                    if (ketqua.equals("ok"))
                                    {
                                        Toast.makeText(activity, "Da thich", Toast.LENGTH_SHORT).show();
                                    }else
                                    {
                                        Toast.makeText(activity, "Loi!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    imageViewhinhbaihatquangcao.setEnabled(false);
                                }
                            });
                           }else
                    {
                        likedStatus[position] = false;// Đánh dấu bài hát không được thích
                        imageluotthich.setImageResource(R.drawable.baseline_timrong_border_24);
                        // Gửi yêu cầu cập nhật lượt thích (chức năng bỏ thích)
                        Toast.makeText(activity, "Da bo thich", Toast.LENGTH_SHORT).show();
                    }
                }
            }
          });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, PlaynhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    activity.startActivity(intent);
                }
            });
        }
    }


}

