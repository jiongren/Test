package com.tree.jinbu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.tree.jinbu.R;
import com.tree.jinbu.TitleBar;
import com.tree.jinbu.fragment.BaseFragment;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-06-22
 */
public abstract class BaseActivity extends AppCompatActivity {

    public TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        titleBar = (TitleBar)findViewById(R.id.tb_app_title);
        if (titleBar != null){
            setSupportActionBar(titleBar);
            initTitleBar();
        }
    }

    protected void initTitleBar(){}

    protected abstract int getLayoutId();

    public BaseFragment mCurrentFragment;
    public void switchFragment(int container, BaseFragment targetFragment) {
        if(mCurrentFragment == targetFragment) {
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (mCurrentFragment != null) {
            ft.hide(mCurrentFragment);
        }
        if(targetFragment != null) {
            if (targetFragment.isAdded()) {
                ft.show(targetFragment);
            } else {
                ft.add(container, targetFragment);
            }
        }
        mCurrentFragment = targetFragment;
        ft.show(targetFragment).commitAllowingStateLoss();
    }

}
