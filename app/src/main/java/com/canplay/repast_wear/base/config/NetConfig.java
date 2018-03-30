package com.canplay.repast_wear.base.config;

/**
 * Created by leo on 2016/9/17.
 */

public class NetConfig{
    /**
     * 默认超时时间
     */
    public static final int HTTP_TIMEOUT = 60 * 1000;
    /**
     * 加密密钥
     */
    public static final String APP_KEY = "";

    /**
     * 数组分隔符
     */
    public static final char CHARKEY = 7;

    /**
     * 加密密钥截取开始
     */
    public static final int startNum = 2;
//    public static final int startNum = 0;

    /**
     * 加密密钥截取结束
     */
    public static final int endNum = 19;
//    public static final int endNum = 11;

    /*
 * 服务端地址
 */
    //public static final String SERVER_URL = "http://wx.canplay.com.cn/medical/doctor";//测试
    //public static final String SERVER_URL = "http://192.168.0.104:12071";//测试
//    public static final String SERVER_URL = "http://app.gd-yijiaren.com/medical/doctor";//正式
            public static final String SERVER_URL = "http://app.yicss.com/meal/customer";//测试
}
