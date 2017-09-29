package com.tree.jinbu.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tree.jinbu.R;

import butterknife.Bind;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-06-22
 */
public class TitleBar extends Toolbar {

    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaultTitleBar(context);
    }

    private void initDefaultTitleBar(final Context context){
        LayoutInflater.from(context).inflate(R.layout.view_title_bar, this);
        setViewTitle("");
        setRightBtnVisibility(View.GONE);
        setLeftBtnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof Activity){
                    ((Activity)context).finish();
                    ((Activity)context).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }
            }
        });
    }

    public void setLeftListener(OnClickListener listener){
        tvLeft.setOnClickListener(listener);
    }

    public void setRightListener(OnClickListener listener){
        tvRight.setOnClickListener(listener);
    }

    public void setViewTitle(Spanned spanned){
        tvTitle.setText(spanned);
    }

    public void setViewTitle(String title){
        tvTitle.setText(title);
    }

    public void setViewTitle(@StringRes int strResId){
        tvTitle.setText(strResId);
    }

    public void setLeftBtnText(String text){
        tvLeft.setText(text);
    }

    public void setLeftBtnText(@StringRes int strResId){
        tvLeft.setText(strResId);
    }

    public void setRightText(String text){
        tvRight.setText(text);
    }

    public void setRightText(@StringRes int strResId){
        tvRight.setText(strResId);
    }

    public void setLeftBtnDrawable(@DrawableRes int drawableId){
        tvLeft.setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0);
    }

    public void setRightBtnDrawable(@DrawableRes int drawableId){
        tvRight.setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0);
    }

    public void setLeftBtnTextColor(@ColorRes int colorResId){
        tvLeft.setTextColor(ContextCompat.getColor(getContext(), colorResId));
    }

    public void setRightBtnTextColor(@ColorRes int colorResId){
        tvRight.setTextColor(ContextCompat.getColor(getContext(), colorResId));
    }

    public void setLeftBtnVisibility(int visibility){
        tvLeft.setVisibility(visibility);
    }

    public void setRightBtnVisibility(int visibility){
        tvRight.setVisibility(visibility);
    }

    public void setLeftBtnClickListener(OnClickListener listener){
        tvLeft.setOnClickListener(listener);
    }

    public void setRightBtnClickListener(OnClickListener listener){
        tvRight.setOnClickListener(listener);
    }
}
