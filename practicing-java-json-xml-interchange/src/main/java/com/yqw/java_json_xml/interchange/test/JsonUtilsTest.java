package com.yqw.java_json_xml.interchange.test;

import com.yqw.java_json_xml.interchange.domain.CityList;
import com.yqw.java_json_xml.interchange.util.JsonUtils;
import com.yqw.java_json_xml.interchange.util.XmlUtils;
import org.junit.Test;

public class JsonUtilsTest {

    @Test
    public void testO2J() {
        CityList cityList = XmlUtils.xmlFileToObject("cityList.xml", CityList.class);
        //将java对象转换为json对象字符串
        System.out.println(JsonUtils.object2Json(cityList));
    }

    @Test
    public void testJ2O() {
        CityList cityList = XmlUtils.xmlFileToObject("cityList.xml", CityList.class);
        //将java对象转换为json对象字符串
        String jsonStr = JsonUtils.object2Json(cityList);
        //将json字符串转换为Java对象
        CityList cityListNew = JsonUtils.json2Object(jsonStr, CityList.class);
        System.out.println(cityListNew);
    }
}
