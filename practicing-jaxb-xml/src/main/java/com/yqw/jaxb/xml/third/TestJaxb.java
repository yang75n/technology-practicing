package com.yqw.jaxb.xml.third;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class TestJaxb {

    @Test
    public void beanToXML() {
        Classroom classroom = new Classroom(1, "软件工程", 4);
        Student student = new Student(101, "张三", 22, classroom);

        try {
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            marshaller.marshal(student, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void XMLStringToBean() {
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>22</age><classroom><grade>4</grade><id>1</id><name>软件工程</name></classroom><id>101</id><name>张三</name></student>";
        try {
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Student student = (Student) unmarshaller.unmarshal(new StringReader(xmlStr));
            System.out.println(student);
            System.out.println(student.getAge());
            System.out.println(student.getClassroom().getName());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
} 