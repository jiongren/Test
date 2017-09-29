package com.tree.jinbu.utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-29
 */

public class EventBusUtil {

    private static EventBus eventBus;
    private static Set<Object> subscribers;
    private static Set<Object> noSubscribers;
    private static EventBusBuilder builder;

    /**
     * 初始化EventBus的静态全局实例，应在Application的onCreate方法中调用
     */
    public static void openEventBus(){
        if (eventBus == null){
            if (builder == null){
                eventBus = EventBus.getDefault();
            }else {
                eventBus = builder.build();
            }
        }
        subscribers = new HashSet<Object>();
        noSubscribers = new HashSet<Object>();
    }

    /**
     * 获取EventBusBuilder实例，采用构建者模式设置一些配置信息
     */
    public static EventBusBuilder builder(){
        if (builder == null){
            builder = EventBus.builder();
        }
        return builder;
    }

    /**
     * 在EventBus上注册订阅组件，应在Activity的onCreate方法中调用，不可以在onResume方法中调用，因为同一个组件不能重复注册
     *
     * @param subscriber 订阅组件
     */
    public static void register(Object subscriber){
        eventBus.register(subscriber);
        subscribers.add(subscriber);
    }

    /**
     * 取消订阅组件在EventBus的注册
     *
     * @param subscriber 订阅组件
     */
    public static void unRegister(Object subscriber){
        eventBus.unregister(subscriber);
        subscribers.remove(subscriber);
    }

    /**
     * 订阅者是否注册成功
     *
     * @param subscriber 订阅者
     * @return 是否注册成功
     */
    public static boolean isRegister(Object subscriber){
        return subscribers.contains(subscriber);
    }

    /**
     * 发布事件出去
     *
     * @param event 发布出去的事件
     */
    public static void post(Object event){
        eventBus.post(event);
    }

    /**
     * 只向声明的组件发布事件
     *
     * @param event           订阅事件
     * @param postSubscribers 订阅组件的可变参数列表
     */
    public static void postOnlyTo(Object event, Object... postSubscribers){
        for (Object postSubscriber : postSubscribers){
            for (Object subscriber: subscribers){
                if (!(subscriber.getClass().getSimpleName().equals(postSubscriber.getClass().getSimpleName()))){
                    unRegister(postSubscriber);
                }
            }
            if (noSubscribers.contains(postSubscriber)){
                register(postSubscriber);
                noSubscribers.remove(postSubscriber);
            }
        }
        post(event);
    }

    /**
     * 不向声明的组件发布事件
     *
     * @param event              订阅事件
     * @param notPostSubscribers 不接收事件的组件的可变参数列表
     */
    public static void postNoTo(Object event, Object... notPostSubscribers){
        for (Object notPostSubscriber : notPostSubscribers){
            noSubscribers.add(notPostSubscriber);
            unRegister(notPostSubscriber);
        }
        eventBus.post(event);
    }

    /**
     * 获取订阅的Sticky事件对象实例
     *
     * @param eventType 订阅事件
     * @return 订阅的Sticky事件对象实例
     */
    public static Object getStickyEvent(Class<?> eventType){
        return eventBus.getStickyEvent(eventType);
    }

    /**
     * 发布Sticky事件
     *
     * @param event      订阅事件
     * @param subscriber 订阅组件
     */
    public static void postSticky(Object event, Object subscriber) {
        if (noSubscribers.contains(subscriber)) {
            register(subscriber);
            noSubscribers.remove(subscriber);
        }
        eventBus.postSticky(event);
    }

    /**
     * 删除订阅的Sticky事件
     *
     * @param eventType 订阅事件可变参数列表
     * @return 被删除的Sticky事件
     */
    public static Object removeStickyEvent(Class<?> eventType) {
        return eventBus.removeStickyEvent(eventType);
    }

    /**
     * 删除订阅的Sticky事件
     *
     * @param event 订阅事件
     * @return 是否删除成功
     */
    public static boolean removeStickyEvent(Object event) {
        return eventBus.removeStickyEvent(event);
    }

    /**
     * 发布通知事件，所谓的通知事件，就是不做任何处理，单纯只是通知
     *
     * @param subscribers 订阅者
     */
    public static void postNotification(Object... subscribers) {
        postOnlyTo(new NotificationEvent(), subscribers);
    }

    /**
     * 通知事件，使用到通知事件的场景像是某个通知过来了，但不携带任何数据，只是通知相应的组件更新信息
     */
    public static class NotificationEvent {
        private NotificationEvent() {
        }
    }

}
