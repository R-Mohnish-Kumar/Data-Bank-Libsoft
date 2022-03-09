package com.mkr.aameclibrary;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    PagerAdapter pagerAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    String title[] = new String[]{"Admin","Staff","Student"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 =findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tabLayout);
        pagerAdapter=new PagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2,(tab, position) -> tab.setText(title[position])).attach();

    }
}