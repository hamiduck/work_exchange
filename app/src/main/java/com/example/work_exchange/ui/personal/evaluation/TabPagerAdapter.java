package com.example.work_exchange.ui.personal.evaluation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter
{
   int tabcount;

    public TabPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
       switch (position)
       {
           case 0 : return new ShowEvaluationFragment();
           case 1 : return new AddEvaluationFragment();

           default: return null;
       }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
