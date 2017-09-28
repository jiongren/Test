package com.tree.jinbu.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-28
 */

public class HttpInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().build();
//        HttpUrl.Builder builder = request.url().newBuilder()
//                .scheme(request.url().scheme())
//                .host(request.url().host())
//                .addQueryParameter("param", "");
//        Request newRequest = request.newBuilder()
//                .method(request.method(), request.body())
//                .url(builder.build()).build();
        //return chain.proceed(newRequest);//需要拦截加参数的话就用新的request
        return chain.proceed(request);
    }
}
