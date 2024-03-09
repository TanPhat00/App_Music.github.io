package com.example.da_app_music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.da_app_music.Activity.DanhsachbaihatActivity;
import com.example.da_app_music.Model.Quangcao;
import com.example.da_app_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Quangcao> arrayListbanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);;
        View view = inflater.inflate(R.layout.dong_banner,container,false);

        ImageView imagebackgroundbanner = view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imagesongbanner = view.findViewById(R.id.imageviewbanner);
        TextView titleviewbanner = view.findViewById(R.id.textviewtitlebannerbaihat);
        TextView noidung = view.findViewById(R.id.noidung);

        Quangcao quangcao = arrayListbanner.get(position);


        Picasso.get().load(quangcao.getHinhanh()).into(imagebackgroundbanner);
        Picasso.get().load(quangcao.getHinhBaiHat()).into(imagesongbanner);
        titleviewbanner.setText(quangcao.getTenBaiHat());
        noidung.setText(quangcao.getNoidung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner",quangcao);
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
