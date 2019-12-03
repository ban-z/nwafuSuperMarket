package com.banzh.nwafusupermarket.network.myHttp.myResponse;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.banzh.nwafusupermarket.network.myHttp.myHttpClient.CommonHttpClient;
import com.banzh.nwafusupermarket.network.myHttp.myRequest.CommonRequest;
import com.banzh.nwafusupermarket.network.myHttp.myRequest.RequestParams;

/*
 *@className: CommonResponse
 *@description: 封装请求流程（同步/异步），返回Response对象
 *
 *@author: banzh
 *@time: 2019/12/1 9:13
 **/
public class CommonResponse {

    //get方法
    public static Call requestGet(String url, RequestParams params){
        return CommonHttpClient.okHttpClient.newCall(CommonRequest.buildGetRequest(url, params));
    }

    //post方法
    public static Call requestPost(String url, RequestParams params){
        return CommonHttpClient.okHttpClient.newCall(CommonRequest.buildPostRequest(url, params));
    }
}
