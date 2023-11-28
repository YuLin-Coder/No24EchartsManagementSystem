package com.example.demo.entity;


import java.io.Serializable;
import java.util.List;

/**
 * 用于echarts折线图
 * 作者：王晓丰
 * 日期：2020-10-19
 * ----------------------------------
 * 1、创建   王晓丰   2020-10-19
 */

public class Echart1 implements Serializable {
    private List<Integer> data;
    private String type;

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
