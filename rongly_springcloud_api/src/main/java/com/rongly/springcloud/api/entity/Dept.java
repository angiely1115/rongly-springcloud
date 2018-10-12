package com.rongly.springcloud.api.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/8 20:50
 * @Version: 1.0
 * modified by:
 */
@Data
@Accessors(chain = true)
public class Dept implements Serializable{

    private String deptNo;

    private String deptName;


    public static void main(String[] args) {
        Dept dept = new Dept();
        dept.setDeptName("财富").setDeptNo("01");
        System.out.println(dept);
    }

}
