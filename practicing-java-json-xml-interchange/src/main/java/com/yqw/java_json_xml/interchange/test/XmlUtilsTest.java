package com.yqw.java_json_xml.interchange.test;

import com.yqw.java_json_xml.interchange.domain.CityList;
import com.yqw.java_json_xml.interchange.util.XmlUtils;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;


public class XmlUtilsTest {

    @Test
    public void xmlFileToObject() throws JAXBException, FileNotFoundException {
        CityList cityList = XmlUtils.xmlFileToObject("cityList.xml", CityList.class);
        System.out.println(cityList);

        //将Java对象转为xml文件
        XmlUtils.object2Xml(cityList, "d://tmp.xml");
    }

    @Test
    public void xmlStringToObject() {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<c c1=\"0\">\n" +
                "    <d d1=\"101020100\" d2=\"上海\" d3=\"shanghai\" d4=\"上海\"/>\n" +
                "    <d d1=\"101220101\" d2=\"合肥\" d3=\"hefei\" d4=\"安徽\"/>\n" +
                "    <d d1=\"101190101\" d2=\"南京\" d3=\"jiangshu\" d4=\"江苏\"/>\n" +
                "    <d d1=\"101010100\" d2=\"北京\" d3=\"beijing\" d4=\"北京\"/>\n" +
                "    <d d1=\"101270101\" d2=\"成都\" d3=\"chengdu\" d4=\"四川\"/>\n" +
                "</c>";

        CityList cityList = XmlUtils.xmlStringToObject(xmlString, CityList.class);
        System.out.println(cityList);
    }

}