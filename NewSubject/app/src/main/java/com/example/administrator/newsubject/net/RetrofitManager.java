package com.example.administrator.newsubject.net;

import android.util.SparseArray;

import com.example.administrator.newsubject.BuildConfig;
import com.example.administrator.newsubject.utils.FileUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @author 涂文远
 * @version $Rev$
 * @time 2016/12/24 10:50
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class RetrofitManager {

    //超时时间
    private static final int DEFAULT_TIMEOUT = 5;

    // 管理不同HostType的单例
    private static SparseArray<RetrofitManager> mInstanceManager = new SparseArray<>(
            HostType.TYPE_COUNT);
    public final ApiManagerService mApiService;

    public static RetrofitManager builder(@HostTypeChecker int hostType) {
        RetrofitManager instance = mInstanceManager.get(hostType);
        if (instance == null) {
            instance = new RetrofitManager(hostType);
            mInstanceManager.put(hostType, instance);
        }
        return instance;
    }


    private RetrofitManager(@HostTypeChecker int hostType) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        /*//清理缓存
        new Request.Builder().cacheControl(new CacheControl.Builder().maxAge(101, TimeUnit.MINUTES).build());
        //不会清理缓存
        new Request.Builder().cacheControl(new CacheControl.Builder().maxStale(100, TimeUnit.MINUTES).build());*/
        //缓存
        /*Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=60";//60s
                }
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
        };*/
        CacheInterceptor cacheInterceptor = new CacheInterceptor();
        Cache catche = new Cache(new File(FileUtil.getcacheDirectory(), "responses"), 100 * 1024 * 1024);
        okHttpClient.cache(catche).addNetworkInterceptor(cacheInterceptor);
        /**
         * 公共参数
         */
        QueryParameterInterceptor queryParameterInterceptor = new QueryParameterInterceptor();
        okHttpClient.addInterceptor(queryParameterInterceptor);
        /**
         * Log信息拦截器
         */
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? LoggingInterceptor.Level.BODY : LoggingInterceptor.Level.BASIC);
        okHttpClient.addInterceptor(loggingInterceptor);

        //设置超时
        okHttpClient.connectTimeout(15, TimeUnit.SECONDS);
        okHttpClient.readTimeout(20, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        okHttpClient.retryOnConnectionFailure(true);

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient.build())
                .addConverterFactory(ResponseConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(getHost(hostType))
                .build();
        mApiService = retrofit.create(ApiManagerService.class);
    }

    private String getHost(int hostType) {
        switch (hostType) {
            case HostType.JIAO_LIAN:
                return ApiManagerService.DOU_BAN_HOST;
            case HostType.MOKAO:
                return ApiManagerService.MOKAO;
            case HostType.SSB:
                return ApiManagerService.SSB;
            case HostType.UGOU:
                return ApiManagerService.UGOU;
            case HostType.TWY:
                return ApiManagerService.TWY;
            default:
                return null;
        }
    }
}
