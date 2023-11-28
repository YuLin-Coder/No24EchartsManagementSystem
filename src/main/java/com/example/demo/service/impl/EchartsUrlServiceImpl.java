package com.example.demo.service.impl;

import com.example.demo.dao.EchartsUrlDao;
import com.example.demo.entity.EchartsUrl;
import com.example.demo.entity.Position;
import com.example.demo.service.EchartsUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EchartsUrlServiceImpl  implements EchartsUrlService {

    @Autowired
    private EchartsUrlDao echartsUrlDao;

    @Override
    public int putEchartsUrl(EchartsUrl echartsUrl) {
        return echartsUrlDao.putEchartsUrl(echartsUrl.getName(),
                echartsUrl.getUrl(),
                echartsUrl.getType(),
                echartsUrl.getUsername());
    }

    @Override
    public int updateEchartsUrl(EchartsUrl echartsUrl) {
        return echartsUrlDao.updateEchartsUrl(echartsUrl.getName(),
                echartsUrl.getUrl(),
                echartsUrl.getType(),
                echartsUrl.getUsername(),echartsUrl.getId());
    }

    @Override
    public int deleteEchartsUrl(Integer id) {
        return echartsUrlDao.deleteEchartsUrl(id);
    }

    @Override
    public Integer queryEchartsUrlListCount(String username) {
        return echartsUrlDao.queryEchartsUrlListCount(username);
    }

    @Override
    public List<Map> queryEchartsUrlToIndex(String username, String type) {
        return echartsUrlDao.queryEchartsUrlToIndex(username,type);
    }

    @Override
    public List<Map> queryEchartsUrlList(String username,Integer page,Integer limit) {
        return echartsUrlDao.queryEchartsUrlList(username,page,limit);
    }

    @Override
    public  List<String> queryEchartoDate(String username) {
        return echartsUrlDao.queryEchartoDate(username);
    }

    @Override
    public Map<String, Object> queryEcharttwDate(String username) {
        List<Map<String,Object>> echart2List= echartsUrlDao.queryEcharttwDate(username);

        return math(echart2List);
    }

    @Override
    public List<Map<String,Object>> queryEchartthDate(String username) {
        return echartsUrlDao.queryEchartthDate(username);
    }

    @Override
    public Boolean putEchartsPosition(Position position) {
        try{
            Map<String,Object> p = echartsUrlDao.queryEchartsPosition2(position.getUsername(),position.getUrl(),position.getType());
            if (p==null||p.size()==0){
                echartsUrlDao.putEchartsPosition(
                        position.getUsername()
                        ,position.getEchartawidth()
                        ,position.getEchartaheight()
                        ,position.getEchartaxaxis()
                        ,position.getEchartayaxis()
                        ,position.getUrl()
                        ,position.getDisplay()
                        ,position.getType()
                );
            } else {
                echartsUrlDao.updateEchartsPosition(

                        position.getEchartawidth()
                        ,position.getEchartaheight()
                        ,position.getEchartaxaxis()
                        ,position.getEchartayaxis()
                        ,position.getDisplay()
                        ,position.getUsername()
                        ,position.getUrl()
                        ,position.getType());
            }
        }catch (Exception e){

        }
        return true;
    }

    @Override
    public List<Map<String, Object>> queryEchartsPosition(String username) {
        return echartsUrlDao.queryEchartsPosition(username);
    }

    private HashMap<String, Object> math(List<Map<String,Object>> echart2){
        HashMap<String, Object> math = new HashMap<>();
        List<Double> data1 = new ArrayList<>();
        List<Double> data2 = new ArrayList<>();
        List<String> xAxisData = new ArrayList<>();
        if (!ObjectUtils.isEmpty(echart2) && echart2.size()>0){
            echart2.forEach(e -> {
                if(!ObjectUtils.isEmpty(e)){
                    int cosLength = 0;
                    int sinLength = 0;
                    if (!ObjectUtils.isEmpty(e.get("cosdata")))  cosLength = echart2.size();
                    if (!ObjectUtils.isEmpty(e.get("sindata")))  sinLength = echart2.size();

                    int num = Math.max(sinLength, cosLength);
                    for (int i = 0; i < num; i++) {
                        Double s=Double.valueOf((echart2.get(i).get("cosdata").toString()));
                        Double c=Double.valueOf((echart2.get(i).get("cosdata").toString()));
                        xAxisData.add("类目" + i);
                        data1.add((Math.sin(s / 5.0) * (s / 5.0 - 10.0) + s / 6.0) * 5);
                        data2.add((Math.cos(c / 5.0) * (c / 5.0 - 10.0) + c / 6.0) * 5);

                    }
                }
            });

        }
        math.put("data1",data1);
        math.put("data2",data2);
        //横坐标信息
        math.put("xAxisData",xAxisData);
        return math;
    }

}
