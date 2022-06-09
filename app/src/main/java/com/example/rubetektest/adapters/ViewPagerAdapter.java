package com.example.rubetektest.adapters;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.rubetektest.screens.Cameras.CamerasFragment;
import com.example.rubetektest.screens.Doors.DoorsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return CamerasFragment.newInstance();
        return DoorsFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
