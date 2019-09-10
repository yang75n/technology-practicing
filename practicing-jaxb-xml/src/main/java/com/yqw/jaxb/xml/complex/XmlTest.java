package com.yqw.jaxb.xml.complex;

import com.yqw.jaxb.xml.XMLUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmlTest {
    @Test
    public void test1() {
        User user = new User(1, "Steven", "@sun123", new Date(), 1000.0);
        List<Computer> list = new ArrayList<Computer>();
        list.add(new Computer("xxxMMeedd", "asus", new Date(), 4455.5));
        list.add(new Computer("lenvoXx", "lenvo", new Date(), 4999));
        user.setComputers(list);
        String path = "D:\\user.xml";
        System.out.println("---将对象转换成File类型的xml Start---");
        XMLUtil.convertToXmlFile(user, path);
        System.out.println("---将对象转换成File类型的xml End---");
        System.out.println();
        System.out.println("---将File类型的xml转换成对象 Start---");
        User user2 = (User) XMLUtil.convertXmlFileToObject(User.class, path);
        System.out.println(user2);
        System.out.println("---将File类型的xml转换成对象 End---");

    }
}
