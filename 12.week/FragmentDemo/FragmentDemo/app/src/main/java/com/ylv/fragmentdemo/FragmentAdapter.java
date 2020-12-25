package com.ylv.fragmentdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter {
    ArrayList<String> basliklar;

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
        basliklar = new ArrayList<>();
        basliklar.add("SOHBETLER");
        basliklar.add("DURUM");
        basliklar.add("ARAMALAR");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0: {
               return Sohbetler.newInstance(basliklar.get(position));
            }
            case 1: {
               return Durum.newInstance(basliklar.get(position));
            }
            case 2: {
                return Aramalar.newInstance(basliklar.get(position));
            }
            default: {
                return Sohbetler.newInstance(basliklar.get(position));
            }

        }
    }

    @Override
    public int getCount() {
        return basliklar.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return basliklar.get(position);
    }
}
