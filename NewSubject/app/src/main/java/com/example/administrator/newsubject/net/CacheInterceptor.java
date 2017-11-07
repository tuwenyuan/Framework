package com.example.administrator.newsubject.net;

import android.text.TextUtils;


import com.example.administrator.newsubject.base.BaseApplication;
import com.example.administrator.newsubject.utils.NetworkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @描述:   云端响应头拦截器，用来配置缓存策略
 *            Dangerous interceptor that rewrites the server's cache-control header.
 * @项目名: ugou
 * @包名: com.ugou88.ugou.retrofit
 * @类名: ServiceFeedbackActivity
 * @作者: soongkun
 * @创建时间: 2016/5/11 17:22
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(!NetworkUtil.isAvailable(BaseApplication.getInstance())){
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            //ILog.e("没有网络");
        }
        Response originalResponse = chain.proceed(request);
        if(NetworkUtil.isAvailable(BaseApplication.getInstance())){
            String cacheControl;
            int maxAge = 60 * 10;

            String cacheHeaders = request.cacheControl().toString();
            if(TextUtils.isEmpty(cacheHeaders)){
                cacheControl = "public, max-age=" + maxAge;// 有网络时 设置缓存超时时间10分钟
               // ILog.e("有网的时候的配置:"+cacheControl);
            }else {
                cacheControl = cacheHeaders;//有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                //ILog.e("有网的时候读接口上的@Headers里的配置:"+cacheControl);
            }
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        }else{
            // 无网络时，设置超时为4周
            //ILog.e("无网络时，设置超时为4周");
            int maxStale = 60 * 60 * 24 * 28;
            return  originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale="+maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
