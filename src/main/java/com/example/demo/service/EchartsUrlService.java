package com.example.demo.service;

import com.example.demo.entity.EchartsUrl;
import com.example.demo.entity.Position;

import java.util.List;
import java.util.Map;

public interface EchartsUrlService {

    /**
     * 添加一个用户的图表
     * @param echartsUrl
     * @return
     */
    int putEchartsUrl(EchartsUrl echartsUrl);

    /**
     * 更新用户的图表
     * @param echartsUrl
     * @return
     */
    int updateEchartsUrl(EchartsUrl echartsUrl);

    /**
     * 删除图表
     * @param id
     * @return
     */
    int deleteEchartsUrl(Integer id);

    /**
     * 查询个人图表
     * @return
     */
    List<Map> queryEchartsUrlList(String username,Integer page,Integer limit);
    Integer queryEchartsUrlListCount(String username);

    /**
     * 在页面上查询个人的图表
     * @param username
     * @param type
     * @return
     */
    List<Map> queryEchartsUrlToIndex(String username,String type);

    /**
     * 查询折线图
     *
     * @param username
     * @return
     */
    List<String> queryEchartoDate(String username);

    Map<String, Object> queryEcharttwDate(String username);

    List<Map<String, Object>> queryEchartthDate(String username);

    Boolean putEchartsPosition(Position position);

    List<Map<String, Object>> queryEchartsPosition(String username);

}
