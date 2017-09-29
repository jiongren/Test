package com.tree.jinbu.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.tree.jinbu.JinBuApplication;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-29
 */

public class ActivityIntentUtil {

    public static void gotoActivity(Context context, Class<?> targetClass){
        Intent intent = new Intent(context, targetClass);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    public static void gotoActivityNotFinish(Context context, Class<?> targetClass){
        Intent intent = new Intent(context, targetClass);
        context.startActivity(intent);
    }

    public static void gotoActivityWithBundle(Context context, Class<?> targetClass, Bundle bundle){
        Intent intent = new Intent(context, targetClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    public static void gotoActivityNotFinishWithBundle(Context context, Class<?> targetClass, Bundle bundle){
        Intent intent = new Intent(context, targetClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void fromActivityForResult(Activity activity, Class<?> targetClass, int requestCode){
        Intent intent = new Intent(activity, targetClass);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void fromFragmentForResult(Fragment fragment, Class<?> targetClass, int requestCode){
        Intent intent = new Intent(fragment.getActivity(), targetClass);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void fromActivityForResultWithBundle(Activity activity, Class<?> targetClass, int requestCode, Bundle bundle){
        Intent intent = new Intent(activity, targetClass);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void fromFragmentForResultWithBundle(Fragment fragment, Class<?> targetClass, int requestCode, Bundle bundle){
        Intent intent = new Intent(fragment.getActivity(), targetClass);
        intent.putExtras(bundle);
        fragment.startActivityForResult(intent,requestCode);
    }

    /**
     * 使用手机浏览器打开网页
     * @param context
     * @param url
     */
    public static void startViewUrl(Context context, String url){
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (Exception e){
            Toast.makeText(JinBuApplication.getContext(), "当前无可用浏览器", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 启动设置的应用详情页
     * @param context
     */
    public static void startAppSettings(Context context){
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_SETTINGS);
        context.startActivity(intent);
    }

}
