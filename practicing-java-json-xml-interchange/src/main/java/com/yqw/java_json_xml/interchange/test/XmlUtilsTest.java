package com.yqw.java_json_xml.interchange.test;

import com.yqw.java_json_xml.interchange.domain.City;
import com.yqw.java_json_xml.interchange.domain.CityList;
import com.yqw.java_json_xml.interchange.util.XmlUtils;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class XmlUtilsTest {


    @Test
    public void object2Xml() throws JAXBException, FileNotFoundException {
        City city = new City("101", "北京", "beijing", "北京");
        City city2 = new City("102", "上海", "shanghai", "上海");
        City city3 = new City("103", "广州", "guangzhou", "关东");

        List list = new ArrayList();
        list.add(city);
        list.add(city2);
        list.add(city3);

        CityList cityList = new CityList();
        cityList.setCityList(list);


        System.out.println(XmlUtils.obejct2XmlString(cityList));

        XmlUtils.object2Xml(cityList,cityList.getClass().getClassLoader().getResource("").getPath()+"/tmp.xml");

    }

    @Test
    public void xmlFileToObject() throws JAXBException, FileNotFoundException {
        CityList cityList = XmlUtils.xmlFileToObject("cityList.xml", CityList.class);
        System.out.println(cityList);

        //将Java对象转为xml文件
        String xmlStr = XmlUtils.obejct2XmlString(cityList);
        System.out.println(xmlStr);
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