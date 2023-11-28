package com.example.demo.entity;

import java.util.List;

/**
 * 用于echarts柱状图
 * 作者：王晓丰
 * 日期：2020-10-19
 * ----------------------------------
 * 1、创建   王晓丰   2020-10-19
 */

public class Echart2 {
    private String name;
    private String type;
    private List<Double> sindata;
    private List<Double> cosdata;

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

    public List<Double> getSindata() {
        return sindata;
    }

    public void setSindata(List<Double> sindata) {
        this.sindata = sindata;
    }

    public List<Double> getCosdata() {
        return cosdata;
    }

    public void setCosdata(List<Double> cosdata) {
        this.cosdata = cosdata;
    }

}
