package com.banzh.nwafusupermarket.network.myHttp.myRequest;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 *@className: RequestParams
 *@description: 封装请求参数，方便后续使用
 *
 *@author: banzh
 *@time: 2019/12/1 9:00
 **/
public class RequestParams {

    //线程安全HashMap
    public ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<String, String>();
    public ConcurrentHashMap<String, Object> fileParams = new ConcurrentHashMap<String, Object>();

    public RequestParams(){
        this((Map<String, String>) null);
    }

    public RequestParams(Map<String, String> source){
        if (source != null){
            for (Map.Entry<String, String> entry : source.entrySet()){
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public RequestParams(final String key, final String value){
        this(new HashMap<String, String>(){
            {
                put(key, value);
            }
        });
    }

    public void put(String key, String value){
        if (key != null && value != null){
            urlParams.put(key, value);
        }
    }

    public void put(String key, Object object) throws FileNotFoundException {
        if (key != null){
            fileParams.put(key, object);
        }
    }

    public boolean hasParams(){
        if (urlParams.size() > 0 || fileParams.size() > 0){
            return true;
        }
        return false;
    }
}
