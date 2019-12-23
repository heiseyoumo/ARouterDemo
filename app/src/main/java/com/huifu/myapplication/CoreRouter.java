package com.huifu.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Set;

import io.reactivex.functions.Consumer;

public class CoreRouter {
    public static String SCHEME_NATIVE = "";// 加载纯原生开发页面
    public static final String PAGE_URL = "_url";
    public static final String PAGE_REQUEST = "request";
    public static final String PAGE_RESPONSE = "response";
    private static CoreRouter mInstance;

    private CoreRouter() {
    }

    public static CoreRouter getInstance(Application application) {
        if (mInstance == null) {
            synchronized (CoreRouter.class) {
                if (mInstance == null) {
                    ARouter.init(application);
                    mInstance = new CoreRouter();
                }
            }
        }
        return mInstance;
    }

    public void setNativeScheme(String scheme) {
        SCHEME_NATIVE = scheme;
    }

    public boolean shouldHandleUrl(String url, boolean isFromWebView) {
        /**
         *true 表示已经处理，不需要进一步处理
         */
        boolean re = true;

        if (TextUtils.isEmpty(url)) {
            return false;
        }
        Uri uri = Uri.parse(url);
        String scheme = uri.getScheme();
        String path = uri.getPath();

        if (url.startsWith(SCHEME_NATIVE)) {
            ARouter.getInstance().build(Uri.parse(url)).navigation();
        } else if ("tel".equals(scheme)) {
            makeCall(url);
        } else if ("sms".equals(scheme)) {
            sendSms(url);
        } else if (scheme != null && scheme.startsWith("http")) {
            String openWith = uri.getQueryParameter(CoreRouterQuery.KEY_OPEN_WITH);
            final boolean closeThis = uri.getBooleanQueryParameter(CoreRouterQuery.KEY_CLOSE_THIS, false);

            /*
             * 不包含openWith时，如果是从内置浏览器里打开，则继续在内部浏览器打开，否则打开系统浏览器
             * */
            if (TextUtils.isEmpty(openWith)) {
                if (isFromWebView) {
                    re = false;
                } else {
                    openSystemBrowser(url);
                }
            } else if (CoreRouterQuery.VALUE_OPEN_WITH_SYS.equals(openWith)) { //打开系统浏览器
                openSystemBrowser(url);
            } else if (CoreRouterQuery.VALUE_OPEN_WITH_BLANK.equals(openWith)) { // 打开内置浏览器的一个新页面
                ARouter.getInstance()
                        .build(CoreRouterPath1.Companion.getWEB_VIEW())
                        .withString(CoreRouterQuery.KEY_URL,
                                removeParameter(url, CoreRouterQuery.KEY_OPEN_WITH))
                        .navigation(CoreApp.getInstance(), new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                                if (closeThis) {
                                    CoreApp.getInstance().currentActivity().finish();
                                }
                            }
                        });
            } else {
                re = false;
            }
        } else {
            re = false;
        }
        return re;
    }

    private Bundle generateBundle(Uri uri) {
        Bundle bundle = new Bundle();
        Set<String> parameterNames = uri.getQueryParameterNames();
        if (parameterNames != null && parameterNames.size() > 0) {
            for (String key : parameterNames) {
                bundle.putString(key, uri.getQueryParameter(key));
            }
        }
        return bundle;
    }

    @SuppressWarnings("CheckResult")
    @SuppressLint("MissingPermission")
    private void makeCall(final String url) {
        Activity activity = CoreApp.getInstance().currentActivity();
        if (activity != null) {
            new RxPermissions(activity)
                    .request(Manifest.permission.CALL_PHONE)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) {
                            if (aBoolean) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                CoreApp.getInstance().startActivity(intent);
                            }
                        }
                    });
        }
    }

    @SuppressWarnings("CheckResult")
    private void sendSms(final String url) {
        Activity activity = CoreApp.getInstance().currentActivity();
        if (activity != null) {
            new RxPermissions(activity)
                    .request(Manifest.permission.SEND_SMS)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) {
                            if (aBoolean) {
                                String number;
                                String message = null;
                                int end = url.indexOf("?body=");
                                if (end <= 0) {
                                    number = url.substring("sms:".length());
                                } else {
                                    number = url.substring("sms:".length(), end);
                                    message = url.substring(end + "?body=".length());
                                }
                                Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + number));
                                if (message != null) {
                                    sendIntent.putExtra("sms_body", message);
                                }
                                sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                CoreApp.getInstance().startActivity(sendIntent);
                            }
                        }
                    });
        }

    }

    private void openSystemBrowser(String url) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            CoreApp.getInstance().startActivity(intent);
        } catch (Exception ignore) {

        }
    }

    private String removeParameter(String url,String key) {
        Uri uri = Uri.parse(url);
        Set<String> names = uri.getQueryParameterNames();
        if (names != null && names.contains(key)) {
            Uri.Builder builder = null;
            builder = new Uri.Builder()
                    .scheme(uri.getScheme())
                    .encodedAuthority(uri.getAuthority())
                    .path(uri.getPath());
            for (String name : names) {
                if (!name.equals(key)) {
                    builder.appendQueryParameter(name, uri.getQueryParameter(name));
                }
            }
            url = builder.build().toString();
        }
        return url;
    }

}
