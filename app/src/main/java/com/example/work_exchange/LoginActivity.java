package com.example.work_exchange;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton google,fb,twitter;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallpaper);

        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewPager);
        google=findViewById(R.id.fab_google);
        fb=findViewById(R.id.fab_facebook);
        twitter=findViewById(R.id.fab_twitter);

        fb.setTranslationY(300);
        google.setTranslationY(300);
        twitter.setTranslationY(300);
        tabLayout.setTranslationY(300);

        fb.setAlpha(v);
        google.setAlpha(v);
        twitter.setAlpha(v);
        tabLayout.setAlpha(v);

        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

        ArrayList<String> arrayList=new ArrayList<>();
        //arrayList.add("登入");
        //arrayList.add("註冊");
        prepareViewPager(viewPager,arrayList);
        tabLayout.setupWithViewPager(viewPager);



/*
        tabLayout.addTab(tabLayout.newTab().setText("登入"));
        tabLayout.addTab(tabLayout.newTab().setText("註冊"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter=new LoginAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        fb.setTranslationY(300);
        google.setTranslationY(300);
        twitter.setTranslationY(300);
        tabLayout.setTranslationY(300);

        fb.setAlpha(v);
        google.setAlpha(v);
        twitter.setAlpha(v);
        tabLayout.setAlpha(v);

        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();*/
    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        LoginAdapter adapter=new LoginAdapter(getSupportFragmentManager());
        LoginTabFragment loginTabFragment=new LoginTabFragment();
        SignupTabFragment signupTabFragment=new SignupTabFragment();
        adapter.addFragment(loginTabFragment,"登入");
        adapter.addFragment(signupTabFragment,"註冊");
        viewPager.setAdapter(adapter);
    }
}
