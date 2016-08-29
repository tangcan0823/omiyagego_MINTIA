package com.example.tangcan0823.mintia_omiyagego.DetailActivity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.tangcan0823.mintia_omiyagego.DetailFragment;
import com.example.tangcan0823.mintia_omiyagego.R;

/**
 * Created by tangcan0823 on 2016/08/29.
 */
public class Activity_k5 extends DetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        ivImage.setImageResource(R.drawable.food_pic06);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(getAsset("food_introData/food_intro06.txt")), "紹介");
        adapter.addFragment(DetailFragment.newInstance(""), "経路");
        adapter.addFragment(DetailFragment.newInstance(getAsset("food_infoData/food_info06.txt")), "詳細");
        mViewPager.setAdapter(adapter);
    }

}