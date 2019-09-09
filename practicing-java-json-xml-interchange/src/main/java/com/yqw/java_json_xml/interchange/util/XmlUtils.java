package com.yqw.java_json_xml.interchange.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * @Date 2019/09/09.
 */
public abstract class XmlUtils {

    /**
     * 将指定路径xml文件转换为Java对象.
     */
    public static <T> T xmlFileToObject(String xmlFilePath, Class<T> clazz) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = contextClassLoader.getResourceAsStream(xmlFilePath)) {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("convert xml to POJO failure!", e);
        }
    }

    /**
     * 将xml字符串转换为Java对象.
     */
    public static <T> T xmlStringToObject(String xmlString, Class<T> clazz) {
        try (Reader reader = new StringReader(xmlString)) {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            T result = (T) unmarshaller.unmarshal(reader);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("convert xml string to POJO failure!", e);
        }
    }


    /**
     * 将Java对象转换为xml文件.
     */
    public static <T> void object2Xml(T t, String xmlFilePath) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = context.createMarshaller();
        // 格式化xml输出的格式
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                Boolean.TRUE);
        FileOutputStream outputStream = new FileOutputStream(xmlFilePath);
        marshaller.marshal(t, outputStream);
    }


    /**
     * 将Java对象转换为xml字符串.
     */
    public static <T> String obejct2XmlString(T t) {
        JAXBContext context = null;
        StringWriter stringWriter = new StringWriter();
        try {
            context = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            marshaller.marshal(t, stringWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }
}