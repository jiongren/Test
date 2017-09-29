package com.tree.jinbu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tree.jinbu.JinBuApplication;
import com.tree.jinbu.R;
import com.tree.jinbu.utils.EventBusUtil;
import com.tree.jinbu.utils.ToastUtil;
import com.tree.jinbu.viewinterface.BaseV;
import com.tree.jinbu.widget.TitleBar;

import butterknife.ButterKnife;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-06-28
 */
public abstract class BaseFragment extends Fragment implements BaseV  {

    TitleBar titleBar;
    protected boolean hasLoadOnce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contextView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, contextView);
        return contextView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        titleBar = (TitleBar)view.findViewById(R.id.tb_app_title);
        if (titleBar != null){
            initTitleBar();
        }
    }

    protected abstract int getLayoutId();

    protected void initTitleBar(){}

    @Override
    public void showToast(int msg) {
        String msgString = JinBuApplication.getContext().getString(msg);
        if (TextUtils.isEmpty(msgString)){
            return;
        }
        ToastUtil.showShortToast(msgString);
    }

    @Override
    public void showToast(String msg) {
        if (TextUtils.isEmpty(msg)){
            return;
        }
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void showProgress(int msgId) {
        String msg = JinBuApplication.getContext().getString(msgId);
        showProgress(msg);
    }

    @Override
    public void showProgress(int msgId, boolean cancelable, boolean canceledOnTouchOutside) {
        String msg = JinBuApplication.getContext().getString(msgId);
        //LoadingBallHelper.showDialogForLoading(getActivity(), msg, cancelable, canceledOnTouchOutside);
    }

    @Override
    public void showProgress(boolean cancelable, boolean canceledOnTouchOutside) {
        //LoadingBallHelper.showDialogForLoading(getActivity(), "", cancelable, canceledOnTouchOutside);
    }

    @Override
    public void showProgress(String msg) {
        //LoadingBallHelper.showDialogForLoading(getActivity(), msg);
    }

    @Override
    public void showProgress() {
        //LoadingBallHelper.showDialogForLoading(getActivity());
    }

    @Override
    public void hideProgress() {
        //LoadingBallHelper.hideDialogForLoading();
    }

    public BaseFragment mCurrentFragment;

    public void switchFragment(int container, BaseFragment targetFragment) {
        if(mCurrentFragment == targetFragment || targetFragment == null) {
            return;
        }
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (mCurrentFragment != null) {
            ft.hide(mCurrentFragment);
        }
        if(!targetFragment.isAdded()) {
            ft.add(container, targetFragment);
        }
        mCurrentFragment = targetFragment;
        ft.show(targetFragment).commitAllowingStateLoss();
    }

    protected void finishActivity(){
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public void setHasLoadOnce(boolean b) {
        hasLoadOnce = b;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(!hasLoadOnce && isVisible() && isVisibleToUser) {
            hasLoadOnce = true;
            doLazyRequest();//ViewPager的第2,3..个Fragment会走这里
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if(getUserVisibleHint()) {//
            hasLoadOnce = true;
            doLazyRequest();//ViewPager的第1个Fragment会走这里
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isNeedEventBus() && !EventBusUtil.isRegister(this)) {
            EventBusUtil.register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //LoadingBallHelper.hideDialogForLoading();
        if(isNeedEventBus() && EventBusUtil.isRegister(this)) {
            EventBusUtil.unRegister(this);
        }
    }

    /**
     * EventBus的使用开关, 默认关闭; 需要时重写返回true即可
     * @return boolean
     */
    protected boolean isNeedEventBus() {
        return false;
    }

    /**
     * 获取上报的页面名称，默认null为不上传；上传时重写即可
     */
    protected String getUploadActionPageName() {
        return null;
    }

    /**
     * 懒加载进行网络请求（在ViewPager+Fragment模式时，进来就进行网络请求时需要重写，否则Fragment会全部一次性进行网络请求，性能降低）
     */
    protected void doLazyRequest() {

    }

    // 手动调用FragmentTransaction的show或hide方法时会触发此回调
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
