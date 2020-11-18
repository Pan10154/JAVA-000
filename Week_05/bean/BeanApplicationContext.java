package com.noonfish.service;

import com.noonfish.entity.Student;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class BeanApplicationContext implements ApplicationContextAware {

    static private ApplicationContext applicationContext;

    @Bean
    public Student student(){
        return new Student("jak", 12);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName){
        return (T)applicationContext.getBean(beanName);
    }
}
