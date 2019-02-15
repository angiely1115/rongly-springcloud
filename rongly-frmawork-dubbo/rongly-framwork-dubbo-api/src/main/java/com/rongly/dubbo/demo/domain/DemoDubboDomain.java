package com.rongly.dubbo.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: lvrongzhuan
 * @Description: 就是一个demo 没有其他的
 * @Date: 2019/1/30 10:45
 * @Version: 1.0
 * modified by:
 */
@Getter
@Setter
@ToString
public class DemoDubboDomain implements Serializable {
    private static final long serialVersionUID = -1360480788923035321L;
    private String demoName;

    private Long id;

}
