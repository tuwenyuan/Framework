package com.example.administrator.newsubject.net;

import android.text.TextUtils;


import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @描述: 公共参数(可能接口有某些参数是公共的，不可能一个个接口都去加吧)
 * @项目名: ugou
 * @包名: com.ugou88.ugou.retrofit.retrofitService
 * @类名: QueryParameterInterceptor
 * @作者: zuojie
 * @创建时间: 16-6-17 上午9:16
 */
public class QueryParameterInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request;
        String method = originalRequest.method();
        Headers headers = originalRequest.headers();

        String loginToken = "token---------";
        HttpUrl.Builder newBuilder = originalRequest.url().newBuilder();
        if (!TextUtils.isEmpty(loginToken)) {
            newBuilder.addQueryParameter("rt", loginToken);
        }
        HttpUrl modifiedUrl = newBuilder.build();

       /* HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                // Provide your custom parameter here
                .addQueryParameter("rt",StringUtil.getLoginToken())
                .build();*/
        request = originalRequest.newBuilder().url(modifiedUrl).build();
        return chain.proceed(request);
    }
}
