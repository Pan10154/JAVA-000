package com.noonfish.utils;

import com.noonfish.entity.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBeanMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-beanr.xml");
        //启动上下文
        applicationContext.refresh();
        //根据类class查找bean
        Student bean = applicationContext.getBean(Student.class);
        System.out.println(bean.toString());
        // 关闭Spring上下文
        applicationContext.close();
    }
}
