package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "echarts_url")
public class EchartsUrl implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    @Column(name = "name")
    protected String name;
    @Column(name = "type")
    protected String type;
    @Column(name = "url")
    protected String url;
    @Column(name = "username")
    protected String username;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
