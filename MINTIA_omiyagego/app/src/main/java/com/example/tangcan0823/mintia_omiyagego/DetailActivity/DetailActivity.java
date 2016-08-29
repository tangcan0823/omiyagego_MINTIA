package com.example.tangcan0823.mintia_omiyagego.DetailActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tangcan0823.mintia_omiyagego.CommentActivity;
import com.example.tangcan0823.mintia_omiyagego.DetailFragment;
import com.example.tangcan0823.mintia_omiyagego.DetailRoadFragment;
import com.example.tangcan0823.mintia_omiyagego.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tangcan0823 on 2016/08/26.
 */
public class DetailActivity extends AppCompatActivity {

    ViewPager mViewPager;
    private String goods_id = "food00_comment_object";
    public FloatingActionButton mFabButton;
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
        ImageView ivImage = (ImageView)findViewById(R.id.ivImage);
        ivImage.setImageResource(R.drawable.food_pic00);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mFabButton = (FloatingActionButton)findViewById(R.id.fab_normal);
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("GOODS_ID", goods_id);
                Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(getAsset("food_introData/food_intro00.txt")), "紹介");
        adapter.addFragment(DetailRoadFragment.newInstance("", "" + (String) getText(R.string.food_search_0)), "地図");
        adapter.addFragment(DetailFragment.newInstance(getAsset("food_infoData/food_info00.txt")), "詳細");
        mViewPager.setAdapter(adapter);
    }

    public String getAsset(String fileName) {
        AssetManager am = getResources().getAssets();
        InputStream is = null;
        try {
            is = am.open(fileName, AssetManager.ACCESS_BUFFER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scanner(is).useDelimiter("\\Z").next();
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
        }

}
