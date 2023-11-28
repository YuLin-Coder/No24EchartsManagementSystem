package com.example.demo.dao;

import com.example.demo.entity.Auditnanager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Repository
public interface AuditnanagerDao extends JpaRepository<Auditnanager, Integer> {
    /**
     * 删除
     * @param id
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from  auditnanager where id=?1", nativeQuery = true)
    int deleteAuditnanager(@Param("id") Integer id);


    @Query(value = "SELECT * FROM auditnanager s  where 1 = 1 " +
            "and (:sysmodule = null or :sysmodule = '' or  s.sysmodule like concat(concat('%',:sysmodule),'%')) " +
            "and (:username = null or :username = '' or  s.username like concat(concat('%',:username),'%')) " +
            "and (:status = null or :status = '' or  s.status=:status )" +
            "and (:time1 = null or :time1 = '' or :time2 = null or :time2 = '' or  s.logdate  between :time1 and :time2)" +
            " limit :page , :limit ", nativeQuery = true)
    public List<Map> queryAuditnanagerList(
            @Param("sysmodule") String sysmodule,
            @Param("username") String username,
            @Param("status") String status,@Param("time1") String time1,
            @Param("time2")  String time2,
            @Param("page") Integer page,
            @Param("limit") Integer limit);

    @Query(value = "SELECT count(*) FROM auditnanager s  where 1 = 1 " +
            "and (:sysmodule = null or :sysmodule = '' or  s.sysmodule like concat(concat('%',:sysmodule),'%')) " +
            "and (:username = null or :username = '' or  s.username like concat(concat('%',:username),'%')) " +
            "and (:status = null or :status = '' or  s.status=:status )" +
            "and (:time1 = null or :time1 = '' or :time2 = null or :time2 = '' or  s.logdate  between :time1 and :time2)" +
            "", nativeQuery = true)
    public Integer queryAuditnanagerListCount( @Param("sysmodule") String sysmodule,
                                               @Param("username") String username,
                                               @Param("status") String status,@Param("time1") String time1,
                                               @Param("time2")  String time2);
    @Query(value = "SELECT * FROM auditnanager s  where 1 = 1 " +
            "and (:sysmodule = null or :sysmodule = '' or  s.sysmodule like concat(concat('%',:sysmodule),'%')) " +
            "and (:username = null or :username = '' or  s.username like concat(concat('%',:username),'%')) " +
            "and (:status = null or :status = '' or  s.status=:status )" +
            "and (:time1 = null or :time1 = '' or :time2 = null or :time2 = '' or  s.logdate  between :time1 and :time2)" +
            "", nativeQuery = true)
    public List<Map> queryAuditnanagerList2(
            @Param("sysmodule") String sysmodule,
            @Param("username") String username,
            @Param("status") String status,
            @Param("time1") String time1,
            @Param("time2")  String time2
          );
}
