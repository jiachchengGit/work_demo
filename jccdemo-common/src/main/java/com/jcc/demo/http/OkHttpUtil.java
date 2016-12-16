package com.jcc.demo.http;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenjiacheng on 2016-12-16.
 */
public class OkHttpUtil {

    public  static  void  main(String[] args) throws IOException {
        String url = "http://api.map.baidu.com/geocoder/v2/";//?output=json&ak=你从百度申请到的Key&address=北京市
        Map<String,String> param = new HashMap<String,String>();
        param.put("output","json");
        param.put("ak","G6OznXDUd9qAF0FCdIYz0l3UvzQuSbam");
        param.put("address","咸阳国际机场T3航站楼");
        System.out.println(syncGet(url,param));
    }
    public static String syncGet(String url, Map<String, String> param) throws IOException {
        String paramStr = null;
        if (param != null && !param.isEmpty()) {
            StringBuffer sb = new StringBuffer();
            Set<Map.Entry<String, String>> entrySet = param.entrySet();
            for (Map.Entry<String, String> en : entrySet) {
                String key = en.getKey();
                String value = en.getValue();
                sb.append(key);
                sb.append("=");
                sb.append(value);
                sb.append("&");
            }
            String p = sb.toString();
            if (p != null) {
                int lastIndexOf = p.lastIndexOf("&");
                paramStr = p.substring(0, lastIndexOf);
            }
        }
        url = url + "?" + paramStr;
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10,TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).build();
        okhttp3.Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        }
        return null;
    }

    public  static  String post(String url,Map<String,String> param) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10,TimeUnit.SECONDS).build();
        FormBody.Builder builder = new FormBody.Builder();
        if(param != null && !param.isEmpty()){
            for (Map.Entry<String,String> m: param.entrySet()) {
                builder.add(m.getKey(),m.getValue());
            }
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        if(response != null && response.isSuccessful()){
            return response.body().toString();
        }
        return null;
    }
}
