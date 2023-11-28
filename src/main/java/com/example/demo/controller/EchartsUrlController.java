package com.example.demo.controller;

import com.example.demo.entity.EchartsUrl;
import com.example.demo.entity.Position;
import com.example.demo.service.EchartsUrlService;
import com.example.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/echarts")
public class EchartsUrlController {

    @Autowired
    private EchartsUrlService echartsUrlService;


    /**
     * 添加或者修改一个用户图表
     * @param echartsUrl
     * @return
     */
    @PostMapping("/putEchartsUrl")
    @ResponseBody
    public ResultUtil putEchartsUrl(EchartsUrl echartsUrl){

        //更新程序
        if(echartsUrl!=null&& echartsUrl.getId()!=null && echartsUrl.getId()!=0){
            echartsUrlService.updateEchartsUrl(echartsUrl);
            return ResultUtil.success("修改成功!");
        }else{
            echartsUrlService.putEchartsUrl(echartsUrl);
            return ResultUtil.success("添加成功!");
        }
    }
    //删除个人图表
    @GetMapping("/deleteEchartsUrl")
    @ResponseBody
    public ResultUtil deleteEchartsUrl(@RequestParam("id") Integer id) {
        return ResultUtil.success( echartsUrlService.deleteEchartsUrl(id));
    }


    //查询个人图表
    @GetMapping("/queryEchartsUrlList")
    @ResponseBody
    public ResultUtil queryEchartsUrlList(@RequestParam("page") Integer page,
                                          @RequestParam("username") String username,
                                          @RequestParam("limit") Integer limit) {
        List<Map> resultData=echartsUrlService.queryEchartsUrlList( username,(page-1)*limit, limit);

        Integer count = echartsUrlService.queryEchartsUrlListCount(username);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(count);
        resultUtil.setData(resultData);

        return resultUtil;
    }
    //查询个人图表
    @GetMapping("/queryEchartsUrlToIndex")
    @ResponseBody
    public ResultUtil queryEchartsUrlToIndex(String username) {
        //    [
//    {type:1,title:'折线图',valueList:[{value:"/echarts/echartto?username=",title:"折线图"}]},
//    {type:2,title:'柱状图',valueList:[{value:"/echarts/echarttw?username=",title:"柱状图"}]},
//    {type:3,title:'饼状图',valueList:[{value:"/echarts/echartth?username=",title:"饼状图"}]},
//            ]
        List<Map> result=new ArrayList<>();
        Map map=new HashMap();
        map.put("type",1);
        map.put("title","折线图");
        map.put("valueList",echartsUrlService.queryEchartsUrlToIndex(username,"1"));
        result.add(map);
        map=new HashMap();
        map.put("type",2);
        map.put("title","柱状图");
        map.put("valueList",echartsUrlService.queryEchartsUrlToIndex(username,"2"));
        result.add(map);
        map=new HashMap();
        map.put("type",3);
        map.put("title","饼状图");
        map.put("valueList",echartsUrlService.queryEchartsUrlToIndex(username,"3"));
        result.add(map);

        return ResultUtil.success(result);
    }







    //从数据库中得到折线图的数据
    @GetMapping("/echartto")
    @ResponseBody
    public ResultUtil echarto(@RequestParam("username") String username) {
        return ResultUtil.success(echartsUrlService.queryEchartoDate(username));
    }

    //从数据库中得到柱状图的数据
    @GetMapping("/echarttw")
    @ResponseBody
    public ResultUtil echarttw(@RequestParam("username") String username) {
        return ResultUtil.success(echartsUrlService.queryEcharttwDate(username));
    }

    //从数据库中得到饼状图的数据
    @GetMapping("/echartth")
    @ResponseBody
    public ResultUtil echartth(@RequestParam("username") String username) {
        return ResultUtil.success(echartsUrlService.queryEchartthDate(username));
    }
    //将echarts图表的位置和大小的信息保存到数据库中
    @PostMapping("/putPosition")
    @ResponseBody
    public ResultUtil putEchartsPosition(Position position){
        return ResultUtil.success(echartsUrlService.putEchartsPosition(position));
    }

    //将echarts图表的位置和大小的信息从数据库中获得
    @GetMapping("/queryPosition")
    @ResponseBody
    public ResultUtil queryEchartsPosition(@RequestParam("username") String username){
        return ResultUtil.success(echartsUrlService.queryEchartsPosition(username));
    }
}
