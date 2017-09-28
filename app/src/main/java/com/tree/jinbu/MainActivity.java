package com.tree.jinbu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.tree.jinbu.activity.BaseActivity;
import com.tree.jinbu.fragment.BaseFragment;
import com.tree.jinbu.fragment.TodayFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.dl_layout)
    DrawerLayout dlLayout;

    BaseFragment todayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        titleBar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlLayout.openDrawer(Gravity.LEFT, true);
            }
        });
    }

    private void initView(){
        navView.setNavigationItemSelectedListener(this);
        todayFragment = new TodayFragment();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            switchFragment(R.id.fl_fragment, todayFragment);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        dlLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (dlLayout.isDrawerOpen(GravityCompat.START)) {
            dlLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
