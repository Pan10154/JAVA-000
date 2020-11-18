package com.pzs.jdbc;

public class Student {
    private long id;
    private String name;
    private int age;

    public Student() {
    }


    public void init(){
        System.out.println("student init");
    }
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
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

    public void run(){
        System.out.println("student run");
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
