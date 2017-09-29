package com.tree.jinbu.net;

import com.tree.jinbu.Response.BaseResponse;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-29
 */

public abstract class HttpCallListener<T extends BaseResponse> extends AbsHttpCallListener<T> {

    @Override
    protected String getErrorMsg() {
        //return baseResponse.getErrorMsg();
        return "";
    }

    @Override
    protected boolean isSessionErr() {
//        return baseResponse.getErrorCode() != null &&
//                (HttpErrorCode.OFF_LINE_ERROR_CODE.equals(baseResponse.getErrorCode()) ||
//                        HttpErrorCode.TICKET_OUT_OF_DATE.equals(baseResponse.getErrorCode()));
        return false;
    }

    @Override
    protected boolean isRequstSucess() {
       // return baseResponse.isSuccess();
        return true;
    }

    @Override
    protected String getErrorCode() {
        //return baseResponse.getErrorCode();
        return "";
    }
}
