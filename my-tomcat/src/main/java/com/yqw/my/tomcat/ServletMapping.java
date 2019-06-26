package com.yqw.my.tomcat;

/**
 * Created by iQiwen on 2019/6/26.
 */
public class ServletMapping {
    private String servletName;
    private String url;
    private String clazz;


    public ServletMapping(String servletName, String url, String clazz) {
        this.servletName = servletName;
        this.clazz = clazz;
        this.url = url;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }


}
