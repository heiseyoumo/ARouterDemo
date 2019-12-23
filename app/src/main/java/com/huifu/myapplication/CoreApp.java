package com.huifu.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;

import androidx.multidex.MultiDexApplication;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@SuppressLint("Registered")
public abstract class CoreApp extends MultiDexApplication {
    protected static CoreApp mInstance = null;
    private String mAppToken;
    private String mAppId;
    private String mAppSignToken;
    private boolean mIsDebug;
    private List<WeakReference<Activity>> mActivities;

    public void initialize(String appId,
                           String appToken,
                           String nativeScheme,
                           String appSignToken,
                           boolean isDebug) {
        mAppToken = appToken;
        mAppId = appId;
        mAppSignToken = appSignToken;
        mIsDebug = isDebug;
        mActivities = new CopyOnWriteArrayList<>();
        CoreRouter.getInstance(this).setNativeScheme(nativeScheme);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    protected abstract Typeface initIconFontFamily();

    protected abstract String initBackIcon();

    public String getAppToken() {
        return mAppToken;
    }

    public String getAppId() {
        return mAppId;
    }

    public String getAppSignToken() {
        return mAppSignToken;
    }

    public static CoreApp getInstance() {
        return mInstance;
    }

    public boolean isDebug() {
        return mIsDebug;
    }

    public void addActivity(Activity activity) {
        if (activity != null) {
            mActivities.add(new WeakReference<>(activity));
        }
    }

    public void removeActivity(Activity removeActivity) {
        for (WeakReference<Activity> activityWeakReference : mActivities) {
            if (activityWeakReference != null) {
                Activity activity = activityWeakReference.get();
                if (activity != null && removeActivity != null) {
                    if (activity == removeActivity) {
                        mActivities.remove(activityWeakReference);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 获取启动的Activity数量
     *
     * @return
     */
    public int activityCount() {
        return mActivities.size();
    }

    /**
     * 获取已启动的Activity列表
     *
     * @return
     */
    public List<WeakReference<Activity>> getActivityList() {
        return mActivities;
    }

    /**
     * 关闭除了目标页的所有页面
     *
     * @param aimActivity
     */
    public void finishAllExcept(Activity aimActivity) {
        Iterator<WeakReference<Activity>> iterator = mActivities.iterator();
        while (iterator.hasNext()) {
            WeakReference<Activity> activityContainer = iterator.next();
            if (null != activityContainer) {
                Activity activity = activityContainer.get();
                if (activity != null) {
                    if (!aimActivity.equals(activity)) {
                        activity.finish();
                    }
                }
            }
        }
    }

    public void finishAllActivities() {
        Iterator<WeakReference<Activity>> iterator = mActivities.iterator();
        while (iterator.hasNext()) {
            WeakReference<Activity> activityContainer = iterator.next();
            if (activityContainer != null) {
                Activity activity = activityContainer.get();
                if (activity != null) {
                    activity.finish();
                }
            }
        }
    }

    public Activity currentActivity() {
        int size = mActivities.size();
        if (size > 0 && null != mActivities.get(size - 1)) {
            return mActivities.get(size - 1).get();
        }
        return null;
    }

    public Activity getActivityByIndex(int index) {
        int size = mActivities.size();
        if (size > 0 && index < size && null != mActivities.get(index)) {
            return mActivities.get(index).get();
        }
        return null;
    }

}
