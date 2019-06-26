package com.yqw.my.tomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置Servlet
 * 我们在servlet开发中，
 * 会在web.xml中通过<servlet></servlet>和<servlet-mapping></servlet-mapping>
 * 来进行指定哪个URL交给哪个servlet进行处理。
 */
public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<>();


    static {
        servletMappingList.add(new ServletMapping("firstServelt", "/first", FirstServlet.class.getName()));
        servletMappingList.add(new ServletMapping("helloServelt", "/hello", HelloWorldServelt.class.getName()));
    }

}
