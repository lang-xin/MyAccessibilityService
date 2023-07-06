package com.example.inputdemo;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

abstract public class AccessibilityUtil {
    //开关
    public static boolean isService = false;

    //判断服务是否开启
    public static boolean isService(Context context, Class service) {
        int enable = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED, 0);
        if (enable != 1) return false;
        String services = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
        if (!TextUtils.isEmpty(services)) {
            TextUtils.SimpleStringSplitter split = new TextUtils.SimpleStringSplitter(':');
            split.setString(services);
            while (split.hasNext()) {
                if (split.next().equalsIgnoreCase(context.getPackageName() + "/" + service.getName()))
                    return true;
            }
        }
        return false;
    }

    //通过文字点击指定项
    public static boolean clickText(AccessibilityService service, String string, int location) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) return false;
        List<AccessibilityNodeInfo> nodeInfoByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText(string);
        List<AccessibilityNodeInfo> nodeInfoList = new ArrayList<>();
        if (nodeInfoByText != null && !nodeInfoByText.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoByText) {
                if (nodeInfo != null && (nodeInfo.getText().equals(string) || nodeInfo.getContentDescription().equals(string))) {
                    nodeInfoList.add(nodeInfo);
                }
            }
        }
        if (nodeInfoList.isEmpty()) return false;
        if (nodeInfoList.size() > location) {
            return click(nodeInfoList.get(location));
        } else {
            return click(nodeInfoList.get(0));
        }
    }

    //通过文字点击第一项
    public static boolean clickText(AccessibilityService service, String string) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) return false;
        List<AccessibilityNodeInfo> nodeInfoByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText(string);
        if (nodeInfoByText != null && !nodeInfoByText.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoByText) {
                if (nodeInfo != null && (nodeInfo.getText().equals(string) || nodeInfo.getContentDescription().equals(string))) {
                    return click(nodeInfo);
                }
            }
        }
        return false;
    }

    //通过文字长按指定项
    public static boolean longClickText(AccessibilityService service, String string, int location) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) return false;
        List<AccessibilityNodeInfo> nodeInfoByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText(string);
        List<AccessibilityNodeInfo> nodeInfoList = new ArrayList<>();
        if (nodeInfoByText != null && !nodeInfoByText.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoByText) {
                if (nodeInfo != null && (nodeInfo.getText().equals(string) || nodeInfo.getContentDescription().equals(string))) {
                    nodeInfoList.add(nodeInfo);
                }
            }
        }
        if (nodeInfoList.isEmpty()) return false;
        if (nodeInfoList.size() > location) {
            return longClick(nodeInfoList.get(location));
        } else {
            return longClick(nodeInfoList.get(0));
        }
    }

    //通过文字长按第一项
    public static boolean longClickText(AccessibilityService service, String string) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) return false;
        List<AccessibilityNodeInfo> nodeInfoByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText(string);
        if (nodeInfoByText != null && !nodeInfoByText.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoByText) {
                if (nodeInfo != null && (nodeInfo.getText().equals(string) || nodeInfo.getContentDescription().equals(string))) {
                    return longClick(nodeInfo);
                }
            }
        }
        return false;
    }

    //通过ID点击指定项
    public static boolean clickId(AccessibilityService service, String id, int location) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) return false;
        List<AccessibilityNodeInfo> infoByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        List<AccessibilityNodeInfo> nodeInfoList = new ArrayList<>();
        if (infoByViewId != null && !infoByViewId.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : infoByViewId) {
                if (nodeInfo != null) {
                    nodeInfoList.add(nodeInfo);
                }
            }
        }
        if (nodeInfoList.isEmpty()) return false;
        if (nodeInfoList.size() > location) {
            return click(nodeInfoList.get(location));
        } else {
            return click(nodeInfoList.get(0));
        }
    }

    //通过ID点击第一项
    public static boolean clickId(AccessibilityService service, String id) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) return false;
        List<AccessibilityNodeInfo> infoByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (infoByViewId != null && !infoByViewId.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : infoByViewId) {
                if (nodeInfo != null) {
                    return click(nodeInfo);
                }
            }
        }
        return false;
    }

    //通过ID长按指定项
    public static boolean longClickId(AccessibilityService service, String id, int location) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) return false;
        List<AccessibilityNodeInfo> infoByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        List<AccessibilityNodeInfo> nodeInfoList = new ArrayList<>();
        if (infoByViewId != null && !infoByViewId.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : infoByViewId) {
                if (nodeInfo != null) {
                    nodeInfoList.add(nodeInfo);
                }
            }
        }
        if (nodeInfoList.isEmpty()) return false;
        if (nodeInfoList.size() > location) {
            return longClick(nodeInfoList.get(location));
        } else {
            return longClick(nodeInfoList.get(0));
        }
    }

    //通过ID长按第一项
    public static boolean longClickid(AccessibilityService service, String id) {
        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) return false;
        List<AccessibilityNodeInfo> infoByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (infoByViewId != null && !infoByViewId.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : infoByViewId) {
                if (nodeInfo != null) {
                    return longClick(nodeInfo);
                }
            }
        }
        return false;
    }

    //通过坐标点击
    public static boolean coordinatesClick(AccessibilityService service, int x, int y) {
        Path path = new Path();
        path.moveTo(x, y);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        GestureDescription gestureDescription = builder.addStroke(new GestureDescription.StrokeDescription(path, 0, 200)).build();
        return service.dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }
        }, null);
    }

    //通过坐标长按
    public static boolean coordinatesLongClick(AccessibilityService service, int x, int y) {
        Path path = new Path();
        path.moveTo(x, y);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        GestureDescription gestureDescription = builder.addStroke(new GestureDescription.StrokeDescription(path, 0, 1000)).build();
        return service.dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }
        }, null);
    }

    //自定义滑动
    public static boolean coordinateSliding(AccessibilityService service, int x0, int y0, int x1, int y1, int ms) {
        Path path = new Path();
        path.moveTo(x0, y0);
        path.lineTo(x1, y1);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        GestureDescription gestureDescription = builder.addStroke(new GestureDescription.StrokeDescription(path, 0, ms)).build();
        return service.dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }
        }, null);
    }

    //点击
    private static boolean click(AccessibilityNodeInfo node) {
        if (node == null) return false;
        if (node.isClickable()) {
            return node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        } else {
            click(node.getParent());
        }
        return false;
    }

    //长按
    private static boolean longClick(AccessibilityNodeInfo node) {
        if (node == null) return false;
        if (node.isLongClickable()) {
            return node.performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);
        } else {
            longClick(node.getParent());
        }
        return false;
    }

    //输入
    @SuppressLint("ObsoleteSdkInt")
    public static boolean InputText(AccessibilityService service, AccessibilityNodeInfo nodeInfo, String text) {
        if (service == null) return false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle arguments = new Bundle();
            arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
            return nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            ClipboardManager clipboard = (ClipboardManager) service.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", text);
            if (clipboard != null) {
                clipboard.setPrimaryClip(clip);
            }
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_PASTE);
        }
        return false;
    }

    //返回
    @SuppressLint("ObsoleteSdkInt")
    public static boolean Back(AccessibilityService service) {
        if (service == null) return false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
        } else {
            return false;
        }
    }

    //主页
    @SuppressLint("ObsoleteSdkInt")
    public static boolean Home(AccessibilityService service) {
        if (service == null) return false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME);
        } else {
            return false;
        }
    }

    //上滑
    public static boolean ScrollForward(AccessibilityNodeInfo node) {
        if (node == null || !node.isScrollable()) return false;
        return node.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
    }

    //下滑
    public static boolean ScrollBackward(AccessibilityNodeInfo node) {
        if (node == null || !node.isScrollable()) return false;
        return node.performAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);
    }

    //暂停
    public static void Sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            System.exit(0);
        }
    }
}
