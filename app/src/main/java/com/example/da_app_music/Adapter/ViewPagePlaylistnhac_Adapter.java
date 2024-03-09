package com.example.da_app_music.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ViewPagePlaylistnhac_Adapter extends FragmentStatePagerAdapter {

    public final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    public ViewPagePlaylistnhac_Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void AddFragment(Fragment fragment){
        fragmentArrayList.add(fragment);
    }
}
