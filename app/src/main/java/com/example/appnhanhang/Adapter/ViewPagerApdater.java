package com.example.appnhanhang.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appnhanhang.FragmenActivity.Fragment_DaOder;
import com.example.appnhanhang.FragmenActivity.Fragment_DangOrder;

public class ViewPagerApdater  extends FragmentStatePagerAdapter {
    public ViewPagerApdater(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0)
        {
            return new  Fragment_DangOrder();
        }
        else
        {
            return new Fragment_DaOder();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String tile = "";
        if (position == 1)
        {
            tile = "Đã order";
        }
        else
            tile = "Đang order";
        return tile;
    }
}
