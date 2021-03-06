package com.stratagile.qlink.application;

import android.app.Application;
import android.webkit.WebSettings;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.stratagile.qlink.constant.ConstantValue;
import com.stratagile.qlink.data.api.HttpInfoInterceptor;
import com.stratagile.qlink.data.api.MainAPI;
import com.stratagile.qlink.data.api.MainHttpApi;
import com.stratagile.qlink.data.api.RequestBodyInterceptor;
import com.stratagile.qlink.data.api.StringConverterFactory;
import com.stratagile.qlink.data.qualifier.Remote;
import com.stratagile.qlink.data.api.API;
import com.stratagile.qlink.data.api.HttpAPIWrapper;
import com.stratagile.qlink.data.api.HttpApi;
import com.stratagile.qlink.entity.Auth;
import com.stratagile.qlink.utils.SpUtil;
import com.stratagile.qlink.utils.SystemUtil;
import com.stratagile.qlink.utils.UuidUtil;
import com.stratagile.qlink.utils.VersionUtil;
import com.today.step.net.CHttpApi;
import com.vondear.rxtools.RxDeviceTool;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @author hu
 * @desc 功能描述
 * @date 2017/5/31 10:04
 */
@Module
public final class APIModule {

    private final Application application;

    @Inject
    public APIModule(Application application) {
        this.application = application;
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(API.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(API.IO_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new RequestBodyInterceptor())
                .addInterceptor(new HttpInfoInterceptor());
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Auth auth = new Auth();
                String uuid = "";
                auth.setAgent(WebSettings.getDefaultUserAgent(AppConfig.getInstance()));
                try {
                    KLog.i(RxDeviceTool.getAndroidId(AppConfig.getInstance()));
                    KLog.i(RxDeviceTool.getDeviceIdIMEI(AppConfig.getInstance()));
                    String serieaId = RxDeviceTool.getSerialNumberNew(AppConfig.getInstance());
                    auth.setUuid(serieaId);
                } catch (Exception e) {
                    e.printStackTrace();
                    auth.setUuid("");
                }
                auth.setPlatform("Android");
                auth.setUuid2(UuidUtil.getUUid2());
                auth.setPhonemodel(RxDeviceTool.getBuildMANUFACTURER() + " " + RxDeviceTool.getBuildBrandModel());
                auth.setAppVersion(VersionUtil.getAppVersionName(AppConfig.instance));
                auth.setAppBuild(VersionUtil.getAppVersionCode(AppConfig.instance) + "");
                KLog.i(auth.toString());
                Request request = chain.request()
                        .newBuilder()
                        .removeHeader("User-Agent")//移除旧的
                        .addHeader("User-Agent", new Gson().toJson(auth))//添加真正的头部
                        .build();
                return chain.proceed(request);
            }
        });
        return builder.build();
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        if (SpUtil.getBoolean(AppConfig.getInstance(), ConstantValue.isMainNet, true)) {
            //主网
            builder.client(okHttpClient)
                    .baseUrl(MainAPI.MainBASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(StringConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            return builder.build();
        } else {
            //测试网
            builder.client(okHttpClient)
                    .baseUrl(API.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(StringConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            return builder.build();
        }
    }

    @Provides
    @Singleton
    public HttpApi provideHttpAPI(Retrofit restAdapter) {
        return restAdapter.create(HttpApi.class);
    }

    @Provides
    @Singleton
    public CHttpApi provideCHttpAPI(Retrofit restAdapter) {
        return restAdapter.create(CHttpApi.class);
    }

    //这里是对外输出部分
    @Provides
    @Singleton
    @Remote
    public HttpAPIWrapper provideHttpAPIWrapper(HttpApi httpAPI, MainHttpApi mainHttpApi) {
        return new HttpAPIWrapper(httpAPI, mainHttpApi);
    }
    @Provides
    @Singleton
    public MainHttpApi provideMainHttpAPI(Retrofit restAdapter) {
        return restAdapter.create(MainHttpApi.class);
    }
}
