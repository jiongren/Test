package com.tree.jinbu.net;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.tree.jinbu.JinBuApplication;
import com.tree.jinbu.Response.IResponse;
import com.tree.jinbu.activity.LaunchActivity;
import com.tree.jinbu.utils.ActivityPageManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-29
 */

public abstract class AbsHttpCallListener<T extends IResponse> implements Callback<T> {
    protected T baseResponse;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (setAutoHideProgress()) {
            //LoadingBallHelper.hideDialogForLoading();
        }
        if (response == null || !response.isSuccessful() || response.body() == null) {
            //onError(HttpErrorCode.SYSTEM_ERROR, WanjiaApplication.getContext().getString(R.string.toast_server_is_abnormal), true);//无任何信息返回，报服务器异常的提示
            return;
        }
        baseResponse = response.body();
       // WJLog.i("Okhttp", "response:" + baseResponse.toString());
        if (isSessionErr()) {  //被顶号后的操作
            String errorMsg = TextUtils.isEmpty(getErrorMsg()) ?
                    "" : getErrorMsg();
            Toast.makeText(JinBuApplication.getContext(), errorMsg, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(new Intent(JinBuApplication.getContext(), LaunchActivity.class));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ActivityPageManager.getInstance().finishAllActivity(); //清除所有Activity
           // ToolUtils.removeUserInfo();
            JinBuApplication.getContext().startActivity(intent);
            return;
        }
        if (isRequstSucess()) {
            onSuccess(baseResponse);
        } else {
            String error = getErrorMsg();  //如果errorMsg为空，按“服务器异常”错误来处理
//            onError(getErrorCode(), StringUtils.isEmpty(error) ?
//                    JinBuApplication.getContext().getString(R.string.get_server_data_failed) : error, true);
        }
    }

    /**
     * 在onfailure也执行onError方法，但其isServerError为false，作为客户端的错误，
     * 如果服务端错误和客户端错误需要不同的处理方式，则在onError可以根据isServerError来判断分别做处理
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (setAutoHideProgress()) {
            //LoadingBallHelper.hideDialogForLoading();
        }
        if (call.isCanceled()) {
            return;
        }
       // onError(HttpErrorCode.REQUEST_FAILED, WanjiaApplication.getContext().getString(R.string.toast_network_request_failed), false);
    }

    /**
     * 成功的回调
     *
     * @param response
     */
    protected abstract void onSuccess(T response);

    /**
     * 数据错误的统一回调，isServerError=true为服务端错误，isServerError=false为客户端错误
     *
     * @param errorCode
     * @param errorMessage
     * @param isServerError
     */
    protected abstract void onError(String errorCode, String errorMessage, boolean isServerError);

    /**
     * 设置是否执行隐藏progress方法，默认是执行
     *
     * @return
     */
    public boolean setAutoHideProgress() {
        return true;
    }

    /**
     * 是否登录态异常,包括登录超时、登录被踢
     * 对于知鸟和万家app错误码不一致
     *
     * @return true: 登录超时、登录被踢，
     */
    protected abstract boolean isSessionErr();

    /**
     * @return 接口是否请求成功
     */
    protected abstract boolean isRequstSucess();


    /**
     * @return 接口错误信息
     */
    protected abstract String getErrorMsg();

    /**
     * @return 接口错误code
     */
    protected abstract String getErrorCode();
}
