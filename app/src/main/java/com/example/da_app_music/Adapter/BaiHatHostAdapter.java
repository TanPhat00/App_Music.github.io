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
import androidx.appcompat.widget.AppCompatImageButton;
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

public class BaiHatHostAdapter extends RecyclerView.Adapter<BaiHatHostAdapter.BaiHatHostHolder> {

    private Activity activity;
    private List<BaiHat> data;
    private boolean[] likedStatus;

    public BaiHatHostAdapter(Activity activity, int simple_list_item_1, List<BaiHat> data) {
        this.activity = activity;
        this.data = data;
        likedStatus = new boolean[data.size()]; // Khởi tạo mảng trạng thái thích
        Arrays.fill(likedStatus, false);// Mặc định tất cả các bài hát đều chưa được thích
    }

    @NonNull
    @Override
    public BaiHatHostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View views = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_baihat_noibat,parent,false);
        return new BaiHatHostHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull BaiHatHostHolder holder, int position) {

        BaiHat baiHat = data.get(position);

        if (baiHat != null && baiHat.getHinhbaihat() != null){
            Picasso.get().load(baiHat.getHinhbaihat()).into(holder.imgbaihathost);
            holder.txttenbaihat.setText(baiHat.getTenbaihat());
            holder.txtstencasi.setText(baiHat.getCasi());
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class BaiHatHostHolder extends RecyclerView.ViewHolder{

        ImageView imgbaihathost;
        TextView  txttenbaihat,txtstencasi;
        AppCompatImageButton imgluotthich;

        public BaiHatHostHolder(@NonNull View itemView) {
            super(itemView);
            imgbaihathost = itemView.findViewById(R.id.imageviewbaihathost);
            txttenbaihat = itemView.findViewById(R.id.txttitletenbaihathost);
            txtstencasi = itemView.findViewById(R.id.txttencasicuabaihathost);
            imgluotthich = itemView.findViewById(R.id.imgluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, PlaynhacActivity.class);
                    intent.putExtra("cakhuc",data.get(getPosition()));
                    activity.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();// Lấy vị trí của bài hát trong danh sách
                    if (position != RecyclerView.NO_POSITION) {
                        if (!likedStatus[position]) { // Nếu bài hát chưa được thích
                            likedStatus[position] = true; // Đánh dấu bài hát đã được thích
                            imgluotthich.setImageResource(R.drawable.baseline_timdat_24);
                            // Gửi yêu cầu cập nhật lượt thích
                    DATAsrevice datAsrevice = APIservice.getservice();
                    Call<String> callback = datAsrevice.UpdateLuotThich("1",data.get(getPosition()).getIdbaihat());
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
                            imgbaihathost.setEnabled(false);
                        }
                    });
                }else
                {
                    likedStatus[position] = false;// Đánh dấu bài hát không được thích
                    imgluotthich.setImageResource(R.drawable.baseline_timrong_border_24);
                    // Gửi yêu cầu cập nhật lượt thích (chức năng bỏ thích)
                    Toast.makeText(activity, "Da bo thich", Toast.LENGTH_SHORT).show();
                }
                }
                    }
            });
        }
    }

}
