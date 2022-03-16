package com.mkr.aameclibrary;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mkr.aameclibrary.Staff.StaffHomeActivity;
import com.mkr.aameclibrary.Student.StudentHomeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    PagerAdapter pagerAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    String title[] = new String[]{"Admin","Staff","Student"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefManager=new SharedPrefManager(MainActivity.this);
        viewPager2 =findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tabLayout);
        pagerAdapter=new PagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2,(tab, position) -> tab.setText(title[position])).attach();

    }
    @Override
    public void onStart() {
        super.onStart();
        if (sharedPrefManager.isStudentLoggedIn()){
            startActivity(new Intent(getApplicationContext(), StudentHomeActivity.class));
        }
        if (sharedPrefManager.isStaffLoggedIn()){
            startActivity(new Intent(getApplicationContext(), StaffHomeActivity.class));
        }
    }
}