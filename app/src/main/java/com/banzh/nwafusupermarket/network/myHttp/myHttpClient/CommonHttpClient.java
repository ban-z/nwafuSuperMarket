package com.banzh.nwafusupermarket.network.myHttp.myHttpClient;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import com.banzh.nwafusupermarket.network.myHttp.myRequest.RequestParams;
import static com.banzh.nwafusupermarket.network.myHttp.myRequest.CommonRequest.*;

/*
 *@className: CommonHttpClient
 *@description: 完成对OkHttpClient的请求
 *
 *@author: banzh
 *@time: 2019/12/1 8:43
 **/
public class CommonHttpClient {

    //参数声明
    private static int TIME_OUT = 30; //超时参数
    public static OkHttpClient okHttpClient;
    //为okHttpClient配置参数
    static {
        //创建我们client对象的构建者
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        //为构建者填充参数——超时时间
        okhttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);

        //允许请求重定向
        okhttpBuilder.followRedirects(true);

        //生成Client对象
        okHttpClient = okhttpBuilder.build();
    }
}
