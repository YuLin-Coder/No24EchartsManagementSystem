package com.example.demo.service.impl;

import com.example.demo.dao.AuditnanagerDao;
import com.example.demo.service.AuditnanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuditnanagerServiceImpl implements AuditnanagerService {

    @Autowired
    private AuditnanagerDao auditnanagerDao;


    @Override
    public int deleteAuditnanager(Integer id) {
        return auditnanagerDao.deleteAuditnanager(id);
    }

    @Override
    public List<Map> queryAuditnanagerList(String sysmodule,
                                           String username,
                                           String status,String time1,
                                           String time2, Integer page, Integer limit) {
        page=(page - 1) * limit;
        return auditnanagerDao.queryAuditnanagerList(sysmodule,username,status,time1,time2,page, limit);
    }
    @Override
    public List<Map> queryAuditnanagerList2(String sysmodule,
                                           String username,
                                           String status,String time1,
                                           String time2) {
        return auditnanagerDao.queryAuditnanagerList2(sysmodule,username,status,time1,time2);
    }
    @Override
    public Integer queryAuditnanagerListCount(String sysmodule,
                                              String username,
                                              String status,String time1,
                                              String time2) {
        return auditnanagerDao.queryAuditnanagerListCount(sysmodule,username,status,time1,time2);

    }
}
