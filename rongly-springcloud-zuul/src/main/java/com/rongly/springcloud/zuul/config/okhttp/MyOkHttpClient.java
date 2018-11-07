package com.rongly.springcloud.zuul.config.okhttp;


import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/31 15:25
 * @Version: 1.0
 * modified by:
 */
public class MyOkHttpClient {
    private OkHttpClient okHttpClient;
    private static final MediaType JSON_PARSE = MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE);

    public MyOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public Response get(MyOkHttpBuilder myOkHttpBuilder){
        Map<String,String> params = myOkHttpBuilder.getParams();
        StringBuffer stringBuffer = new StringBuffer();
        Optional.ofNullable(params).ifPresent((c->{
            c.forEach((k,v)->{
                if(stringBuffer.length()==0){
                    stringBuffer.append(myOkHttpBuilder.getUrl());
                    stringBuffer.append("?");
                }else {
                    stringBuffer.append("&");
                }
                stringBuffer.append(k).append("=").append(v);
            });
        }));

        Request.Builder builder = this.createRequestBuilder(stringBuffer.toString(),myOkHttpBuilder.getHeadParams()).get();
        Response response = null;
        try {
              response = okHttpClient.newCall(builder.build()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response post(MyOkHttpBuilder myOkHttpBuilder){
        Request.Builder builder = this.createRequestBuilder(myOkHttpBuilder.getUrl(),myOkHttpBuilder.getHeadParams());
        FormBody.Builder formBody = new FormBody.Builder();
        Map<String,String> params = myOkHttpBuilder.getParams();
        Optional.ofNullable(params).ifPresent(p->p.forEach(formBody::add));
        builder.post(formBody.build());
        Response response = null;
        try {
            response = okHttpClient.newCall(builder.build()).execute();
          } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }



    public Response postJsonBody(MyOkHttpBuilder myOkHttpBuilder){
        Request.Builder builder = this.createRequestBuilder(myOkHttpBuilder.getUrl(),myOkHttpBuilder.getHeadParams());
        RequestBody requestBody = RequestBody.create(JSON_PARSE,myOkHttpBuilder.getBody());
        builder.post(requestBody);
        Response response = null;
        try {
            response = okHttpClient.newCall(builder.build()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    private Request.Builder createRequestBuilder(String url,Map<String,String> headMaps){
        Request.Builder builder = new Request.Builder().url(url);
        Map<String,String> headParams = headMaps;
        Optional.ofNullable(headParams).ifPresent(h->{
            h.forEach(builder::addHeader);
        });
        return builder;
    }
}
