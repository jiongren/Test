package com.tree.jinbu.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Process;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-29
 */

public class ActivityPageManager {

    public final static int APP_IS_KILLED = -1;//应用在后台被回收
    public final static int APP_IS_NORMAL = 1;//应用正常运行状态
    private static Stack<Activity> activityStack;
    private static ActivityPageManager activityPageManager;
    private int appStatus = APP_IS_KILLED;

    public static ActivityPageManager getInstance(){
        if (activityPageManager == null){
            synchronized (ActivityPageManager.class){
                if (activityPageManager == null){
                    activityPageManager = new ActivityPageManager();
                }
            }
        }
        return activityPageManager;
    }

    public int getAppStatus(){
        return appStatus;
    }

    public void setAppStatus(int appStatus){
        this.appStatus = appStatus;
    }

    public void addActivity(Activity activity){
        if (activityStack == null){
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity(){
        if (activityStack == null){
            return null;
        }else {
            try {
                return activityStack.lastElement();
            }catch (NoSuchElementException ignored){
            }
        }
        return null;
    }

    public void finishActivity(){
        try{
            Activity activity = activityStack.lastElement();
            this.finishActivity(activity);
        }catch (NoSuchElementException ignored){
        }
    }

    public void finishActivity(Activity activity){
        if (activity != null){
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void finishActivity(Class<?> cls){
        Iterator var = activityStack.iterator();
        while (var.hasNext()){
            Activity activity = (Activity)var.next();
            if (activity.getClass().equals(cls)){
                this.finishActivity(activity);
                break;
            }
        }
    }

    public Activity findActivityByClass(Class<?> cls){
        Iterator var = activityStack.iterator();
        while (var.hasNext()){
            Activity activity = (Activity)var.next();
            if (activity.getClass().equals(cls)){
                return activity;
            }
        }
        return null;
    }

    public int getActivitySize(){
        int size = 0;
        if (activityStack != null){
            size = activityStack.size();
        }
        return size;
    }

    public Stack<Activity> getActivityStack(){
        return activityStack;
    }

    public void finishAllActivity(){
        if (activityStack != null){
            int i = 0;
            for (int size = activityStack.size(); i < size; ++i){
                if (activityStack.get(i) != null){
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

    public void AppExit(Context context){
        try {
            this.finishAllActivity();
            Process.killProcess(Process.myPid());
        }catch (Exception ignored){}
    }


}
