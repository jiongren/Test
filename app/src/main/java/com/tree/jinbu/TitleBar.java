package com.tree.jinbu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-06-22
 */
public class TitleBar extends Toolbar {

    private TextView tvLeft, tvRight, tvTitle;

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

    private void initDefaultTitleBar(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_title_bar, this);
        tvLeft = (TextView)findViewById(R.id.tv_left);
        tvRight = (TextView)findViewById(R.id.tv_right);
        tvTitle = (TextView)findViewById(R.id.tv_title);
    }

    public void setLeftListener(OnClickListener listener){
        tvLeft.setOnClickListener(listener);
    }

    public void setRightListener(OnClickListener listener){
        tvRight.setOnClickListener(listener);
    }


}
