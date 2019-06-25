package com.yqw.my.zuul.filter;

public abstract class EatuulFilter {

    abstract public String filterType();

    abstract public int filterOrder();

    abstract public void run();
}