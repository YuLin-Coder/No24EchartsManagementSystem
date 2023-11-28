package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用于echarts饼状图
 * 作者：王晓丰
 * 日期：2020-10-19
 * ----------------------------------
 * 1、创建   王晓丰   2020-10-19
 */

public class Echart3 implements Serializable {
    private String name;
    private String type;
    private List<Product> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
