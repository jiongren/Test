package com.tree.jinbu;

import android.app.Application;

import com.tree.jinbu.net.HttpClient;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-06-22
 */
public class JinBuApplication extends Application {

    private static HttpClient httpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        httpClient = HttpClient.getInstance();
    }

    public static HttpClient getHttpClient(){
        return httpClient;
    }
}
