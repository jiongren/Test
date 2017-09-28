package com.tree.jinbu.net;

import com.tree.jinbu.Response.BaseResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-28
 */

public interface HttpApi {

    @POST("")
    Call<BaseResponse> postBase(@QueryMap Map<String, String> paramMap);

    @GET("")
    Call<BaseResponse> getBase(@QueryMap Map<String, String> paramMap);


}
