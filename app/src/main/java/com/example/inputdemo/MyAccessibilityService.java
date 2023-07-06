package com.example.inputdemo;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class MyAccessibilityService extends AccessibilityService {

    private static final String TAG = MyAccessibilityService.class.getName();

    Handler handler = new Handler();

    /**
     * 当启动服务的时候就会被调用,系统成功绑定该服务时被触发，也就是当你在设置中开启相应的服务，
     * 系统成功的绑定了该服务时会触发，通常我们可以在这里做一些初始化操作
     */
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.i(TAG, "onServiceConnected: ");

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //通过文本找到对应节点集合.
//                AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
//                //通过空间id找到对应的节点集合.
//                List<AccessibilityNodeInfo> textIdList = nodeInfo.findAccessibilityNodeInfosByViewId("cn.wps.moffice_eng:id/et_edit_edittext");
//                Log.i(TAG, " onServiceConnected run: " + textIdList.size());
//                if (textIdList.size() > 0) {
//                    //输入数据
//                    AccessibilityUtil.InputText(MyAccessibilityService.this, textIdList.get(0), "我是测试数据！  "+System.currentTimeMillis());
//                    //点击回车
//                    AccessibilityUtil.clickId(MyAccessibilityService.this, "cn.wps.moffice_eng:id/et_edit_btn_enter");
//                }
//                handler.postDelayed(this, 3000);
//            }
//        }, 5000);

    }

//    public void setAccessibilityServiceInfo() {
//        String[] packageNames = {"com.tencent.mm"};
//        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
//        //相应时间的类型,(长安,点击,滑动)
//        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
//        //反馈给用户的类型,这里是语音
//        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
//
//        //过滤的包名
//        accessibilityServiceInfo.packageNames = packageNames;
//        setServiceInfo(accessibilityServiceInfo);
//    }


    /**
     * 通过系统监听窗口变化的回调,sendAccessibiliyEvent()不断的发送AccessibilityEvent到此处
     *
     * @param event
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();

        //根据时间回调类型进行处理.
        Log.i(TAG, "onAccessibilityEvent: " + eventType);
        Log.i(TAG, "onAccessibilityEvent: " + Thread.currentThread().getName());


    }

    /**
     * 中断服务时的回调.
     */
    @Override
    public void onInterrupt() {

    }

    /**
     * 查找拥有特定焦点类型的控件
     *
     * @param focus
     * @return
     */
    @Override
    public AccessibilityNodeInfo findFocus(int focus) {
        Log.i(TAG, "findFocus: " + focus);
        return super.findFocus(focus);
    }

    /**
     * 如果配置能够获取窗口内容,则会返回当前活动窗口的根结点
     *
     * @return
     */
    @Override
    public AccessibilityNodeInfo getRootInActiveWindow() {
        return super.getRootInActiveWindow();
    }


    /**
     * 获取系统服务
     *
     * @param name
     * @return
     */
    @Override
    public Object getSystemService(String name) {
        return super.getSystemService(name);
    }

    /**
     * 如果允许服务监听按键操作，该方法是按键事件的回调，需要注意，这个过程发生了系统处理按键事件之前
     *
     * @param event
     * @return
     */
    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        Log.i(TAG, "onKeyEvent: " + event.toString());
        return super.onKeyEvent(event);
    }
}