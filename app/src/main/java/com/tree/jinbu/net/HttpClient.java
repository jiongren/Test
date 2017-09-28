package com.tree.jinbu.net;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-28
 */

public class HttpClient {

    public static HttpClient httpClient;
    public static OkHttpClient okHttpClient;
    public static Retrofit retrofit;
    private Object apiService;

    public static HttpClient getInstance(){
        if (httpClient == null){
            synchronized (HttpClient.class){
                if (httpClient == null){
                    httpClient = new HttpClient();
                }
            }
        }
        return httpClient;
    }

    public HttpClient(){
        initOkHttp();
        initRetrofit();
    }

    private OkHttpClient initOkHttp(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpInterceptor());
        okHttpClient = builder.build();
        return okHttpClient;
    }

    private Retrofit initRetrofit(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(HttpConstant.http_ip)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory( RxJavaCallAdapterFactory.create())
        .client(okHttpClient);
        retrofit = builder.build();
        apiService = retrofit.create(HttpApi.class);
        return retrofit;
    }

//    private void initRxJava(){
//                /*rx处理*/
//        ProgressSubscriber subscriber = new ProgressSubscriber(basePar);
//        Observable observable = basePar.getObservable(retrofit)
//                /*失败后的retry配置*/
//                .retryWhen(new RetryWhenNetworkException())
//                /*生命周期管理*/
//                .compose(basePar.getRxAppCompatActivity().bindToLifecycle())
//                /*http请求线程*/
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                /*回调线程*/
//                .observeOn(AndroidSchedulers.mainThread())
//                /*结果判断*/
//                .map(basePar);
//
//        /*数据回调*/
//        observable.subscribe(subscriber);
//    }

    public HttpApi getApiService() {
        return (HttpApi) apiService;
    }

    private void certificateVerfy(OkHttpClient.Builder httpBuilder) {
        //在stg或者product环境的https 主机域名含pinganwj
        if (false) {
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null);
                String certificateAlias = Integer.toString(0);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(new Buffer().writeUtf8(HttpConstant.CERT).inputStream()));
                SSLContext sslContext = SSLContext.getInstance("TLS");
                final TrustManagerFactory trustManagerFactory =
                        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
                httpBuilder.sslSocketFactory(sslContext.getSocketFactory());
                httpBuilder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                        return hv.verify(HttpConstant.HOST_NAME, sslSession);
                    }
                });
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
