package com.yqw.my.zuul.http;

import com.yqw.my.zuul.filter.EatuulFilter;
import com.yqw.my.zuul.filter.RequestWrapperFilter;
import com.yqw.my.zuul.filter.RoutingFilter;
import com.yqw.my.zuul.filter.SendResponseFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 这个是具体的执行器。需要说明一下，在Zuul中，ZuulRunner在获取具体有哪些过滤器的时候，有一个FileLoader可以动态读取配置加载。博主在实现我们自己的EatuulRunner时候，略去动态读取的过程，直接静态写死。
 */
public class EatRunner {
    //静态写死过滤器
    private ConcurrentHashMap<String, List<EatuulFilter>> hashFiltersByType = new ConcurrentHashMap<String, List<EatuulFilter>>() {{
        put("pre", new ArrayList<EatuulFilter>() {{
            add(new RequestWrapperFilter());
        }});
        put("route", new ArrayList<EatuulFilter>() {{
            add(new RoutingFilter());
        }});
        put("post", new ArrayList<EatuulFilter>() {{
            add(new SendResponseFilter());
        }});
    }};

    public void init(HttpServletRequest req, HttpServletResponse resp) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setRequest(req);
        ctx.setResponse(resp);
    }

    public void preRoute() throws Throwable {
        runFilters("pre");
    }

    public void route() throws Throwable {
        runFilters("route");
    }

    public void postRoute() throws Throwable {
        runFilters("post");
    }

    public void runFilters(String sType) throws Throwable {
        List<EatuulFilter> list = this.hashFiltersByType.get(sType);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                EatuulFilter zuulFilter = list.get(i);
                zuulFilter.run();
            }
        }
    }
}