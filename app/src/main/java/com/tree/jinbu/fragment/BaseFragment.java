package com.tree.jinbu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tree.jinbu.R;
import com.tree.jinbu.TitleBar;

import butterknife.ButterKnife;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-06-28
 */
public abstract class BaseFragment extends Fragment {

    TitleBar titleBar;

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

}
