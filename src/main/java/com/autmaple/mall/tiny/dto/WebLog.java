package com.autmaple.mall.tiny.dto;

/**
 * @ClassName WebLog
 * @Description Controller 层日志信息封装类
 * @Author AutMaple
 * @Date 2022/6/26 17:52
 * @Version 1.0
 **/
public class WebLog {

    /**
     * @Author AutMaple
     * @Description 操作描述
     * @Date 2022/6/26 17:56
     **/
    private String description;

    /**
     * @Author AutMaple
     * @Description 操作用户
     * @Date 2022/6/26 17:56
     **/
    private String username;

    /**
     * @Author AutMaple
     * @Description 操作开始时间
     * @Date 2022/6/26 17:57
     **/
    private Long startTime;

    /**
     * @Author AutMaple
     * @Description 操作消耗的时间
     * @Date 2022/6/26 17:57
     **/
    private Integer spendTime;

    /**
     * @Author AutMaple
     * @Description 根路径
     * @Date 2022/6/26 17:58
     **/
    private String basePath;

    /**
     * @Author AutMaple
     * @Description URI
     * @Date 2022/6/26 17:58
     **/
    private String uri;

    /**
     * @Author AutMaple
     * @Description 请求的 URL
     * @Date 2022/6/26 18:30
     **/
    private String url;

    /**
     * @Author AutMaple
     * @Description 请求类型
     * @Date 2022/6/26 17:58
     **/
    private String method;

    /**
     * @Author AutMaple
     * @Description 请求的 IP 地址
     * @Date 2022/6/26 17:59
     **/
    private String ip;


    /**
     * @Author AutMaple
     * @Description 请求携带的参数
     * @Date 2022/6/26 17:59
     **/
    private Object parameter;

    /**
     * @Author AutMaple
     * @Description 请求返回的结果
     * @Date 2022/6/26 18:00
     **/
    private Object result;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Integer spendTime) {
        this.spendTime = spendTime;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Object getParameter() {
        return parameter;
    }
    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
