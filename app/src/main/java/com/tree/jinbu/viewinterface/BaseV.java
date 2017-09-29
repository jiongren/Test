package com.tree.jinbu.viewinterface;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-29
 */

public interface BaseV {
    void showToast(String msg);
    void showToast(int msgId);
    void showProgress();
    void showProgress(int msgId);
    void showProgress(String msg);
    void showProgress(int msgId, boolean cancelable, boolean canceledOnTouchOutside);
    void showProgress(boolean cancelable, boolean canceledOnTouchOutside);
    void hideProgress();
}
