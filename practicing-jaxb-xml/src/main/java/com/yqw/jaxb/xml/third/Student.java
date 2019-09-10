package com.yqw.jaxb.xml.third;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 注意：
 * <p>
 * 1、需要转换的model对象一定要添加@XmlRootElement注解，其里面的其他对象则不需要
 * <p>
 * 2、需要转换的model对象一定要有不带参数的构造方法，包括该对象里面引用的对象。
 */
@XmlRootElement
public class Student {
    private int id;
    private String name;
    private int age;
    private Classroom classroom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Student(int id, String name, int age, Classroom classroom) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.classroom = classroom;
    }

    //无参够着函数一定需要，否则JXBContext无法正常解析。  
    public Student() {
        super();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", classroom=" + classroom +
                '}';
    }
}