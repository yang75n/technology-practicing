package com.yqw.jaxb.xml.third;

public class Classroom {
    private int id;
    private String name;
    private int grade;

    public Classroom(int id, String name, int grade) {
        super();
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Classroom() {
        super();
    }


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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}