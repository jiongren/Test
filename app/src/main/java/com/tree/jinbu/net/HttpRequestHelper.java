package com.tree.jinbu.net;

import com.tree.jinbu.JinBuApplication;
import com.tree.jinbu.Response.BaseResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-28
 */

public class HttpRequestHelper {

    private static HttpApi api = JinBuApplication.getHttpClient().getApiService();

    public static Call<BaseResponse> getBase(String param){
        Map<String, String> map = new HashMap<>();
        map.put("api_code", ApiCode.API_CODE);
        map.put("param", param);
        return api.getBase(map);
    }

}
