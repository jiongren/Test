package com.tree.jinbu.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.tree.jinbu.JinBuApplication;
import com.tree.jinbu.R;
import com.tree.jinbu.utils.ActivityIntentUtil;
import com.tree.jinbu.utils.ActivityPageManager;
import com.tree.jinbu.utils.EventBusUtil;
import com.tree.jinbu.utils.ToastUtil;
import com.tree.jinbu.viewinterface.BaseV;
import com.tree.jinbu.widget.TitleBar;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-06-22
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseV {


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
        ActivityPageManager.getInstance().addActivity(this);
        if (ActivityPageManager.getInstance().getAppStatus() == ActivityPageManager.APP_IS_KILLED){
            ActivityIntentUtil.gotoActivity(this, LaunchActivity.class);
            ActivityPageManager.getInstance().AppExit(this);
        }
    }

    protected void initTitleBar(){}

    protected abstract int getLayoutId();

    protected boolean isNeedEventBus(){
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedEventBus() && !EventBusUtil.isRegister(this)){
            EventBusUtil.register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (isNeedEventBus() && EventBusUtil.isRegister(this)){
            EventBusUtil.unRegister(this);
        }
        //LoadingBallHelper.hideDialogForLoading();
        ActivityPageManager.getInstance().finishActivity(this);
        super.onDestroy();
    }

    @Override
    public void showToast(int msg) {
        ToastUtil.showShortToastById(msg);
    }

    @Override
    public void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            showToast(msg, Toast.LENGTH_SHORT);
        }
    }

    public void showToast(String msg, int duration) {
        if (TextUtils.isEmpty(msg)){
            return;
        }
        ToastUtil.showToast(msg,duration);
    }

    @Override
    public void showProgress(int msgId) {
        String msg = JinBuApplication.getContext().getString(msgId);
        showProgress(msg);
    }

    @Override
    public void showProgress(int msgId, boolean backCancelable, boolean canceledOnTouchOutside) {
        String msg = JinBuApplication.getContext().getString(msgId);
        //LoadingBallHelper.showDialogForLoading(this, msg, backCancelable, canceledOnTouchOutside);
    }

    @Override
    public void showProgress(boolean backCancelable, boolean canceledOnTouchOutside) {
       // LoadingBallHelper.showDialogForLoading(this, "", backCancelable, canceledOnTouchOutside);
    }

    @Override
    public void showProgress(String msg) {
        //LoadingBallHelper.showDialogForLoading(this, msg);
    }

    @Override
    public void showProgress() {
        //LoadingBallHelper.showDialogForLoading(this);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return super.onCreateDialog(id);
    }

    @Override
    public void hideProgress() {
        //LoadingBallHelper.hideDialogForLoading();
    }

//    public BaseFragment mCurrentFragment;
//    public void switchFragment(int container, BaseFragment targetFragment) {
//        if(mCurrentFragment == targetFragment) {
//            return;
//        }
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        if (mCurrentFragment != null) {
//            ft.hide(mCurrentFragment);
//        }
//        if(targetFragment != null) {
//            if (targetFragment.isAdded()) {
//                ft.show(targetFragment);
//            } else {
//                ft.add(container, targetFragment);
//            }
//        }
//        mCurrentFragment = targetFragment;
//        ft.show(targetFragment).commitAllowingStateLoss();
//    }

}
