package com.example.demo.controller;

import com.example.demo.service.AuditnanagerService;
import com.example.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/audit")
public class AuditnanagerController {

    @Autowired
    private AuditnanagerService auditnanagerService;


    //删除
    @GetMapping("/deleteAuditnanager")
    @ResponseBody
    public ResultUtil deleteEchartsUrl(@RequestParam("id") String id) {
        String[] ids=id.split(",");
        for(String ida : ids){
            auditnanagerService.deleteAuditnanager(Integer.valueOf(ida));
        }
        return ResultUtil.success();
    }


    //查询
    @GetMapping("/queryAuditnanagerList")
    @ResponseBody
    public ResultUtil queryEchartsUrlList(@RequestParam("page") Integer page,
                                          @RequestParam("limit") Integer limit,
                                          String sysmodule,
                                          String username,
                                          String time1,
                                          String time2,
                                          String status
    ) {
        List<Map> resultData = auditnanagerService.queryAuditnanagerList(sysmodule, username, status, time1, time2, page, limit);
        Integer count = auditnanagerService.queryAuditnanagerListCount(sysmodule, username, status, time1, time2);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(count);
        resultUtil.setData(resultData);

        return resultUtil;
    }


    //查询
    @GetMapping("/updateEchartsUrlList")
    @ResponseBody
    public void updateEchartsUrlList(
                                     String sysmodule,
                                     String username,
                                     String time1,
                                     String time2,
                                     String status,
                                     HttpServletResponse response,
                                     HttpServletRequest request
    ) {
        List<Map> resultData = auditnanagerService.queryAuditnanagerList2(sysmodule, username, status, time1, time2);
        StringBuffer stringBuffer = new StringBuffer();
        //添加表头
        stringBuffer.append("日志编号,系统模快,操作类型,主机,操作地点,用户名称,请求方式,登录状态,登录日期");
        stringBuffer.append("\r\n");
        for (Map map : resultData) {
            stringBuffer.append(map.get("no") + ",");
            stringBuffer.append(map.get("sysmodule") + ",");
            stringBuffer.append(map.get("operation") + ",");
            stringBuffer.append(map.get("ipaddress") + ",");
            stringBuffer.append(map.get("location") + ",");
            stringBuffer.append(map.get("username") + ",");
            stringBuffer.append(map.get("requestmode") + ",");
            if ("0".equals(map.get("status"))) {
                stringBuffer.append("失败,");
            } else if ("1".equals(map.get("status"))) {
                stringBuffer.append("成功,");
            }
//            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(map.get("logdate").toString()).toString()
            stringBuffer.append(map.get("logdate").toString());
            stringBuffer.append("\r\n");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=log.csv");
        try {
            OutputStreamWriter  outputStream =  new OutputStreamWriter(response.getOutputStream(), "UTF-8");


            outputStream.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
            outputStream.write(stringBuffer.toString()); //result是要导出的csv的字符串
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
