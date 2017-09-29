package com.tree.jinbu;

import android.app.Application;
import android.content.Context;

import com.tree.jinbu.net.HttpClient;
import com.tree.jinbu.utils.EventBusUtil;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-06-22
 */
public class JinBuApplication extends Application {

    private static HttpClient httpClient;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        httpClient = HttpClient.getInstance();
        EventBusUtil.openEventBus();
    }

    public static HttpClient getHttpClient(){
        return httpClient;
    }

    public static Context getContext() {
        return mContext;
    }
}
