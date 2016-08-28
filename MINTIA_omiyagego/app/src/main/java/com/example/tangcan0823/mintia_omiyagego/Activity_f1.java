package com.example.tangcan0823.mintia_omiyagego;

import android.support.v4.view.ViewPager;

/**
 * Created by tangcan0823 on 2016/08/29.
 */
public class Activity_f1 extends DetailActivity {
     @Override
      public void setupViewPager(ViewPager mViewPager) {
      MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
      adapter.addFragment(DetailFragment.newInstance(getAsset("food_infoData/food_info01.txt")), "詳細");
      adapter.addFragment(DetailFragment.newInstance(getAsset("food_introData/food_intro01.txt")), "紹介");
      adapter.addFragment(DetailFragment.newInstance(""), "レビュー");
      mViewPager.setAdapter(adapter);
  }

}
