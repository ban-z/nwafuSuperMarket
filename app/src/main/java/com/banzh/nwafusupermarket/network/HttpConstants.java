package com.banzh.nwafusupermarket.network;

public class HttpConstants {

     //服务器地址
    private static final String ROOT_URL = "http://zmz121.cn:8888";

     //登陆接口
    public static String LOGIN = ROOT_URL + "/user/login";

     //注册接口
    public static String REGISTER = ROOT_URL + "/user/register";

     //请求本地产品列表
    public static String PRODUCT_LIST = ROOT_URL + "/fund/search.php";

     //本地产品列表更新时间措请求
    public static String PRODUCT_LATESAT_UPDATE = ROOT_URL + "/fund/upsearch.php";

     //检查更新接口
    public static String CHECK_UPDATE = ROOT_URL + "/config/check_update.php";
}
