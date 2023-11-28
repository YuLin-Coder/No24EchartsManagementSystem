package com.example.demo.dao;

import com.example.demo.entity.EchartsUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Repository
public interface EchartsUrlDao extends JpaRepository<EchartsUrl, Integer> {
    /**
     * 更新一个图标
     * @param name
     * @param url
     * @param type
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "update  echarts_url set name=?1,url=?2,type=?3,username=?4 where id=?5", nativeQuery = true)
    int updateEchartsUrl(
            @Param("url") String name,
            @Param("url") String url,
            @Param("type") String type,
            @Param("username") String username,
            @Param("id") Integer id
    );
    /**
     * 删除一个图标
     * @param id
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from  echarts_url where id=?1", nativeQuery = true)
    int deleteEchartsUrl(@Param("id") Integer id);

    /**
     * 添加一个图标
     * @param name
     * @param url
     * @param type
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "insert into echarts_url (name,url,type,username)values( ?1, ?2, ?3,?4)", nativeQuery = true)
    int putEchartsUrl(
                           @Param("url") String name,
                           @Param("url") String url,
                           @Param("type") String type,
                           @Param("username") String username
                      );



    @Query(value = "SELECT * FROM echarts_url where username=?1  limit ?2 , ?3 ", nativeQuery = true)
    public List<Map> queryEchartsUrlList(String username,Integer page,Integer limit);

    @Query(value = "SELECT count(*) FROM echarts_url where username=?1 ", nativeQuery = true)
    public Integer queryEchartsUrlListCount(String username);

    @Query(value = "SELECT name as title,url as value FROM echarts_url  where username=?1 and type= ?2", nativeQuery = true)
    public List<Map> queryEchartsUrlToIndex(String username, String type);



    @Query(value = "select data from echart1 where type ='line'", nativeQuery = true)
    List<String> queryEchartoDate(String username);//折线图


    @Query(value = "select name,type,sindata,cosdata from echart2 ", nativeQuery = true)
    List<Map<String, Object>> queryEcharttwDate(String username);//柱状图


    @Query(value = "select value,name from product ", nativeQuery = true)
    List<Map<String, Object>> queryEchartthDate(String username);//饼状图

    @Query(value = "select * from position ", nativeQuery = true)
    List<Map<String, Object>> queryEchartsPosition(String username);//从数据库中获得位置和大小的数据

    @Query(value = "select * from position where username=?1 and url=?2 and type=?3", nativeQuery = true)
    Map<String, Object> queryEchartsPosition2(@Param("username") String username, @Param("url") String url, @Param("type") String type);//从数据库中获得位置和大小的数据

    @Modifying
    @Transactional
    @Query(value = "insert into position value( ?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    int putEchartsPosition(@Param("username") String username,
                            @Param("echartawidth") Double echartawidth,
                            @Param("echartaheight") Double echartaheight,
                            @Param("echartaxaxis") Double echartaxaxis,
                            @Param("echartayaxis") Double echartayaxis,
                            @Param("url") String url,
                            @Param("display") String display,
                            @Param("type") String type);//将echarts图表的大小的位置的信息存到数据库中

    @Modifying
    @Transactional
    @Query(value = "update position set echartawidth=?1, echartaheight=?2,echartaxaxis=?3,echartayaxis=?4,display=?5 where username=?6 and url=?7 and type=?8", nativeQuery = true)
    int updateEchartsPosition(
                               @Param("echartawidth") Double echartawidth,
                               @Param("echartaheight") Double echartaheight,
                               @Param("echartaxaxis") Double echartaxaxis,
                               @Param("echartayaxis") Double echartayaxis,
                               @Param("display") String display,
                               @Param("username") String username,
                               @Param("url") String url,
                               @Param("type") String type
    );//更新数据库中charts图表的大小的位置的信息


}
