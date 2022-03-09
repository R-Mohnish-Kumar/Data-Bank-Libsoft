package com.mkr.aameclibrary;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    String title[] = new String[]{"Admin","Staff","Student"};

    public PagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new AdminFragment();
            case 1:
                return new StaffFragment();
            case 2:
                return new StudentFragment();
        }
        return new AdminFragment();
    }


    @Override
    public int getItemCount() {
        return title.length;
    }
}
