package com.rongly.springcloud.feign.config.okhttp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/31 15:46
 * @Version: 1.0
 * modified by:
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyOkHttpBuilder {
    private String url;
    private Map<String,String> headParams;
    private Map<String,String> params ;
    private Object tag;
    private String body;
}
