package com.example.work_exchange;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoginAdapter extends FragmentPagerAdapter {

    ArrayList<String> arrayList=new ArrayList<>();
    List<Fragment> fragmentList=new ArrayList<>();

    public void addFragment(Fragment fragment,String title){
        arrayList.add(title);
        fragmentList.add(fragment);
    }

    public LoginAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }

/*
    private Context context;
    int totalTabs;

    public LoginAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LoginTabFragment loginTabFragment=new LoginTabFragment();
                return loginTabFragment;
            case 1:
                SignupTabFragment signupTabFragment=new SignupTabFragment();
                return signupTabFragment;
            default:
                return null;
        }
    }*/
}
