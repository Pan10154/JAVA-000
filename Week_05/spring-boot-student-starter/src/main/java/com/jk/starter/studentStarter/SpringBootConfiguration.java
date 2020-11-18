package com.jk.starter.studentStarter;

import com.jk.starter.studentStarter.entity.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({Student.class})
@EnableConfigurationProperties(Student.class)
public class SpringBootConfiguration {
}
