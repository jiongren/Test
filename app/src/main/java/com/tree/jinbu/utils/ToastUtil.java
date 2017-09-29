package com.tree.jinbu.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.tree.jinbu.JinBuApplication;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-29
 */

public class ToastUtil {
    private static Toast mShortToast;
    private static Toast mLongToast;

    public static void showShortToast(String msg) {
        if (mShortToast == null) {
            mShortToast = Toast.makeText(JinBuApplication.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mShortToast.setText(msg);
        }
        mShortToast.show();
    }

    public static void showShortToastById(int msgId) {
        String msgString = JinBuApplication.getContext().getString(msgId);
        if (TextUtils.isEmpty(msgString)) {
            return;
        }
        showShortToast(msgString);
    }

    public static void showLongToast(String msg) {
        if (mLongToast == null) {
            mLongToast = Toast.makeText(JinBuApplication.getContext(), msg, Toast.LENGTH_LONG);
        } else {
            mLongToast.setText(msg);
        }
        mLongToast.show();
    }

    public static void showToast(String msg, int duration) {
        if (duration == Toast.LENGTH_SHORT) {
            showShortToast(msg);
        } else {
            showLongToast(msg);
        }
    }

    public static void cancelShortToast() {
        if(mShortToast != null) {
            mShortToast.cancel();
        }
    }

    public static void cancelLongToast() {
        if(mLongToast != null) {
            mLongToast.cancel();
        }
    }
}
