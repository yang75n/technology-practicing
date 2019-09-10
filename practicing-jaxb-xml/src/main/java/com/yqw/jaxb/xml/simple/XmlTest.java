package com.yqw.jaxb.xml.simple;

import com.yqw.jaxb.xml.XMLUtil;
import org.junit.Test;

import java.util.Date;

/**
 * JAXB（Java Architecture for XML Binding) 是一个业界的标准，
 * 是一项可以根据XML Schema产生Java类的技术。该过程中，
 * JAXB也提供了将XML实例文档反向生成Java对象树的方法，
 * 并能将Java对象树的内容重新写到XML实例文档。从另一方面来讲，
 * JAXB提供了快速而简便的方法将XML模式绑定到Java表示，
 * 从而使得Java开发者在Java应用程序中能方便地结合XML数据和处理函数。
 * <p>
 * <p>
 * <p>
 * JAXBContext 类提供到 JAXB API 的客户端入口点。
 * 它提供了管理实现 JAXB 绑定框架操作所需的 XML/Java 绑定信息的抽象，
 * 这些操作包括：解组、编组和验证。
 *
 * @author iQiwen
 */
public class XmlTest {
    @Test
    public void test1() {
        // 创建需要转换的对象  
        User user = new User(1, "iQiwen", "@Java123", new Date(), 1000.0);
        System.out.println("---将对象转换成string类型的xml Start---");
        // 将对象转换成string类型的xml  
        String str = XMLUtil.convertToXmlString(user);
        // 输出  
        System.out.println(str);
        System.out.println("---将对象转换成string类型的xml End---");
        System.out.println();
        System.out.println("---将String类型的xml转换成对象 Start---");
        User userTest = (User) XMLUtil.convertXmlStrToObject(User.class, str);
        System.out.println(userTest);
        System.out.println("---将String类型的xml转换成对象 End---");
    }

    @Test
    public void test2() {
        // 创建需要转换的对象
        User user = new User(1, "iQiwen", "@Java123", new Date(), 1000.0);
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