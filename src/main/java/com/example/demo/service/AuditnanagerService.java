package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface AuditnanagerService {



    /**
     * 删除列表
     * @param id
     * @return
     */
    int deleteAuditnanager(Integer id);

    /**
     * 查询列表
     * @return
     */
    List<Map> queryAuditnanagerList(  String sysmodule,
                                      String username,
                                      String status,String time1,
                                      String time2,Integer page, Integer limit);
    Integer queryAuditnanagerListCount(  String sysmodule,
                                         String username,
                                         String status,String time1,
                                         String time2);

    List<Map> queryAuditnanagerList2(  String sysmodule,
                                      String username,
                                      String status,String time1,
                                      String time2);
}
