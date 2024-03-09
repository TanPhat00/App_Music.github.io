package com.example.da_app_music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import com.example.da_app_music.Adapter.MainViewPagerAdapter;
import com.example.da_app_music.Frament.Fragment_ThuVien;
import com.example.da_app_music.Frament.Fragment_TimKiem;
import com.example.da_app_music.Frament.Fragment_TrangChu;
import com.example.da_app_music.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    MainViewPagerAdapter mainViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        innit();
    }

    private void innit() {
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_TrangChu(),"Home");
        mainViewPagerAdapter.addFragment(new Fragment_TimKiem(),"Search");
        mainViewPagerAdapter.addFragment(new Fragment_ThuVien(), "Library");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab homeTab = tabLayout.getTabAt(0);
        if (homeTab != null) {
            homeTab.select();
        }
        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.baseline_search_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.library);

        ColorFilter selectedFilter = new PorterDuffColorFilter(
                ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        ColorFilter unselectedFilter = new PorterDuffColorFilter(
                ContextCompat.getColor(this, R.color.xam), PorterDuff.Mode.SRC_IN);

        // Thiết lập màu mặc định cho icon
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null && tab.getIcon() != null) {
                tab.getIcon().setColorFilter(unselectedFilter);
            }
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Khi tab được chọn, thay đổi màu của icon thành trắng
                if (tab.getIcon() != null) {
                    tab.getIcon().setColorFilter(selectedFilter);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Khi tab không được chọn, thay đổi màu của icon về màu xám
                if (tab.getIcon() != null) {
                    tab.getIcon().setColorFilter(unselectedFilter);
                }
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void anhxa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}