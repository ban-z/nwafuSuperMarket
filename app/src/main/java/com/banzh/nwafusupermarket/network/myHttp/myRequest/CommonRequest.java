package com.banzh.nwafusupermarket.network.myHttp.myRequest;

import android.util.Log;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Request;

/*
 *@className: CommonRequest
 *@description: 完成对Request对象的封装
 *
 *@author: banzh
 *@time: 2019/12/1 8:44
 **/
public class CommonRequest {

    private static String TAG = "CommonRequest";

    //完成get请求
    public static Request buildGetRequest(String url, RequestParams params){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");

        if (params != null){
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()){
                //将请求参数逐一遍历添加到我们的请求构建类中
                urlBuilder.append(entry.getKey()).append("=")
                        .append(entry.getValue()).append("&");
            }
        }

        //Log.d(TAG, "createGetRequest: url ==========================\n\t\t\t\t " + urlBuilder.substring(0, urlBuilder.length() - 1));
        return new Request.Builder()
                .url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get()
                .build();
    }

    //完成对于键值对方式的Request对象的封装
    public static Request buildPostRequest(String url, RequestParams params){
        //使用FormBody类,存放post请求参数
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (params != null){
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()){
                //将请求参数逐一遍历添加到我们的请求构建类中
                mFormBodyBuild.add(entry.getKey(),entry.getValue());
            }
        }

        //通过Request的构建者方法返回Request对象
        Request request =  new Request.Builder().
                url(url).
                post(mFormBodyBuild.build()).
                build();
        //Log.d(TAG, "buildPostRequest: url ==========================\n\t\t\t\t " + request.url().toString());
        return request;
    }
}
