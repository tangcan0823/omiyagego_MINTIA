package com.example.tangcan0823.mintia_omiyagego;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tangcan0823.mintia_omiyagego.DetailActivity.Activity_f1;
import com.example.tangcan0823.mintia_omiyagego.DetailActivity.Activity_f2;
import com.example.tangcan0823.mintia_omiyagego.DetailActivity.Activity_f3;
import com.example.tangcan0823.mintia_omiyagego.DetailActivity.Activity_f4;
import com.example.tangcan0823.mintia_omiyagego.DetailActivity.Activity_f5;
import com.example.tangcan0823.mintia_omiyagego.DetailActivity.Activity_f6;
import com.example.tangcan0823.mintia_omiyagego.DetailActivity.DetailActivity;

public class MainActivity extends AppCompatActivity implements BackHandledFragment.BackHandlerInterface {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private BackHandledFragment selectedFragment;
    private NavigationView mNavigationView;

    ImageView imageView;
    ImageButton imageButton;
    LinearLayout revealView, layoutButtons;
    Animation alphaAnimation;
    float pixelDensity;
    boolean flag = true;

    private static final int ANIM_DURATION_TOOLBAR = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);

        //profile Image
        setUpProfileImage();

        switchToMain();


    }


    public void goDetail(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.okashi_0:
                intent = new Intent(this, DetailActivity.class);
                startActivity(intent);
                break;
            case R.id.okashi_1:
                intent = new Intent(this, Activity_f1.class);
                startActivity(intent);
                break;
            case R.id.okashi_2:
                intent = new Intent(this, Activity_f2.class);
                startActivity(intent);
                break;
            case R.id.okashi_3:
                intent = new Intent(this, Activity_f3.class);
                startActivity(intent);
                break;
            case R.id.okashi_4:
                intent = new Intent(this, Activity_f4.class);
                startActivity(intent);
                break;
            case R.id.okashi_5:
                intent = new Intent(this, Activity_f5.class);
                startActivity(intent);
                break;
            case R.id.okashi_6:
                intent = new Intent(this, Activity_f6.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void switchToMain() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MainFragment()).commit();
        mToolbar.setTitle(R.string.app_name);
    }

    private void switchToOkashi() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new FoodFragment()).commit();
        mToolbar.setTitle(R.string.navigation_okashi);
    }

    private void switchToOsake() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new OsakeFragment()).commit();
        mToolbar.setTitle(R.string.navigation_osake);
    }

    private void switchToKougehin() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new KougehinFragment()).commit();
        mToolbar.setTitle(R.string.navigation_kougehin);
    }


    private void switchToAbout() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new FoodFragment()).commit();
        mToolbar.setTitle(R.string.navigation_about);
    }


    private void setUpProfileImage() {
        View headerView = mNavigationView.inflateHeaderView(R.layout.navigation_header);
        View profileView = headerView.findViewById(R.id.profile_image);
        if (profileView != null) {
            profileView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchToMain();
                    mDrawerLayout.closeDrawers();
                    mNavigationView.getMenu().getItem(1).setChecked(true);
                }
            });
        }

    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.navigation_item_okashi:
                                switchToOkashi();
                                break;
                            case R.id.navigation_item_osake:
                                switchToOsake();
                                break;
                            case R.id.navigation_item_kougehin:
                                switchToKougehin();
                                break;
                            case R.id.navigation_item_about:
                                switchToAbout();
                                break;

                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void setSelectedFragment(BackHandledFragment backHandledFragment) {
        this.selectedFragment = backHandledFragment;
    }


    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (selectedFragment == null || !selectedFragment.onBackPressed()) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                doExitApp();
            }
        }

    }




    public void launchTwitter(View view) {


        pixelDensity = getResources().getDisplayMetrics().density;
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);

        int x = imageView.getRight();
        int y = imageView.getBottom();
        x -= ((28 * pixelDensity) + (16 * pixelDensity));

        int hypotenuse = (int) Math.hypot(imageView.getWidth(), imageView.getHeight());

        if (flag) {

            imageButton.setBackgroundResource(R.drawable.rounded_cancel_button);
            imageButton.setImageResource(R.mipmap.image_cancel);

            FrameLayout.LayoutParams parameters = (FrameLayout.LayoutParams)
                    revealView.getLayoutParams();
            parameters.height = imageView.getHeight();
            revealView.setLayoutParams(parameters);

            Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, 0, hypotenuse);
            anim.setDuration(700);

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    layoutButtons.setVisibility(View.VISIBLE);
                    layoutButtons.startAnimation(alphaAnimation);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            revealView.setVisibility(View.VISIBLE);
            anim.start();

            flag = false;
        } else {

            imageButton.setBackgroundResource(R.drawable.rounded_button);
            imageButton.setImageResource(R.mipmap.twitter_logo);

            Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, hypotenuse, 0);
            anim.setDuration(400);

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    revealView.setVisibility(View.GONE);
                    layoutButtons.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            anim.start();
            flag = true;
        }
    }

    public void Animation(View view){

        switch (view.getId()){
            case R.id.launchTwitterAnimation_f0:
                imageView = (ImageView) findViewById(R.id.imageView_f0);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_f0);
                revealView = (LinearLayout) findViewById(R.id.linearView_f0);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_f0);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_f1:
                imageView = (ImageView) findViewById(R.id.imageView_f1);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_f1);
                revealView = (LinearLayout) findViewById(R.id.linearView_f1);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_f1);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_f2:
                imageView = (ImageView) findViewById(R.id.imageView_f2);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_f2);
                revealView = (LinearLayout) findViewById(R.id.linearView_f2);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_f2);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_f3:
                imageView = (ImageView) findViewById(R.id.imageView_f3);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_f3);
                revealView = (LinearLayout) findViewById(R.id.linearView_f3);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_f3);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_f4:
                imageView = (ImageView) findViewById(R.id.imageView_f4);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_f4);
                revealView = (LinearLayout) findViewById(R.id.linearView_f4);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_f4);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_f5:
                imageView = (ImageView) findViewById(R.id.imageView_f5);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_f5);
                revealView = (LinearLayout) findViewById(R.id.linearView_f5);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_f5);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_f6:
                imageView = (ImageView) findViewById(R.id.imageView_f6);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_f6);
                revealView = (LinearLayout) findViewById(R.id.linearView_f6);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_f6);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_o0:
                imageView = (ImageView) findViewById(R.id.imageView_o0);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_o0);
                revealView = (LinearLayout) findViewById(R.id.linearView_o0);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_o0);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_o1:
                imageView = (ImageView) findViewById(R.id.imageView_o1);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_o1);
                revealView = (LinearLayout) findViewById(R.id.linearView_o1);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_o1);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_o2:
                imageView = (ImageView) findViewById(R.id.imageView_o2);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_o2);
                revealView = (LinearLayout) findViewById(R.id.linearView_o2);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_o2);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_o3:
                imageView = (ImageView) findViewById(R.id.imageView_o3);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_o3);
                revealView = (LinearLayout) findViewById(R.id.linearView_o3);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_o3);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_o4:
                imageView = (ImageView) findViewById(R.id.imageView_o4);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_o4);
                revealView = (LinearLayout) findViewById(R.id.linearView_o4);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_o4);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_o5:
                imageView = (ImageView) findViewById(R.id.imageView_o5);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_o5);
                revealView = (LinearLayout) findViewById(R.id.linearView_o5);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_o5);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_o6:
                imageView = (ImageView) findViewById(R.id.imageView_o6);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_o6);
                revealView = (LinearLayout) findViewById(R.id.linearView_o6);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_o6);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_o7:
                imageView = (ImageView) findViewById(R.id.imageView_o7);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_o7);
                revealView = (LinearLayout) findViewById(R.id.linearView_o7);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_o7);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_k0:
                imageView = (ImageView) findViewById(R.id.imageView_k0);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_k0);
                revealView = (LinearLayout) findViewById(R.id.linearView_k0);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_k0);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_k1:
                imageView = (ImageView) findViewById(R.id.imageView_k1);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_k1);
                revealView = (LinearLayout) findViewById(R.id.linearView_k1);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_k1);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_k2:
                imageView = (ImageView) findViewById(R.id.imageView_k2);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_k2);
                revealView = (LinearLayout) findViewById(R.id.linearView_k2);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_k2);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_k3:
                imageView = (ImageView) findViewById(R.id.imageView_k3);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_k3);
                revealView = (LinearLayout) findViewById(R.id.linearView_k3);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_k3);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_k4:
                imageView = (ImageView) findViewById(R.id.imageView_k4);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_k4);
                revealView = (LinearLayout) findViewById(R.id.linearView_k4);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_k4);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_k5:
                imageView = (ImageView) findViewById(R.id.imageView_k5);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_k5);
                revealView = (LinearLayout) findViewById(R.id.linearView_k5);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_k5);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_k6:
                imageView = (ImageView) findViewById(R.id.imageView_k6);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_k6);
                revealView = (LinearLayout) findViewById(R.id.linearView_k6);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_k6);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_k7:
                imageView = (ImageView) findViewById(R.id.imageView_k7);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_k7);
                revealView = (LinearLayout) findViewById(R.id.linearView_k7);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_k7);
                launchTwitter(view);
                break;
            case R.id.launchTwitterAnimation_k8:
                imageView = (ImageView) findViewById(R.id.imageView_k8);
                imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation_k8);
                revealView = (LinearLayout) findViewById(R.id.linearView_k8);
                layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons_k8);
                launchTwitter(view);
                break;

            default:
                break;

        }

    }
}