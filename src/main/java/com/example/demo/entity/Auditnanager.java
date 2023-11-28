package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "auditnanager")
public class Auditnanager implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    @Column(name = "no")
    protected String no;
    @Column(name = "sysmodule")
    protected String sysmodule;
    @Column(name = "operation")
    protected String operation;
    @Column(name = "ipaddress")
    protected String ipaddress;
    @Column(name = "location")
    protected String location;
    @Column(name = "username")
    protected String username;
    @Column(name = "requestmode")
    protected String requestmode;
    @Column(name = "status")
    protected String status;
    @Column(name = "logdate")
    protected Date logdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSysmodule() {
        return sysmodule;
    }

    public void setSysmodule(String sysmodule) {
        this.sysmodule = sysmodule;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequestmode() {
        return requestmode;
    }

    public void setRequestmode(String requestmode) {
        this.requestmode = requestmode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLogdate() {
        return logdate;
    }

    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }
}
