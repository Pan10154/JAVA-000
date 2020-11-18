package com.pzs.jdbc;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;

public class JDBCHikariDemo3 {
    public static Connection connection;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //配置文件
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");//oracle
//        hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//        hikariConfig.setUsername("whg");
//        hikariConfig.setPassword("whg");
//        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
//        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
//        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        HikariDataSource ds = new HikariDataSource(hikariConfig);
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //启动上下文
        applicationContext.refresh();
        //根据类class查找bean
        HikariDataSource ds = applicationContext.getBean(HikariDataSource.class);
        Student student = null;
        try{
            //创建connection
            connection = ds.getConnection();
            //新增
//        student = new Student();
//        student.setName("李骑");
//        student.setAge(25);
//        int row = save(student);
//        System.out.println(row);
            //删除
//        boolean b = deleteById(4);
//        System.out.println(b);

            //查询
            student = getById(5);
            System.out.println(student);

            //修改
            student.setName("王五");
            update(student);
            //关闭connection
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int save(Student student)  {
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement("INSERT INTO student(name,age) values (?,? )");
            prst.setString(1, student.getName());
            prst.setInt(2, student.getAge());
            int row =  prst.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    public static int update(Student student)  {
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement("update student set name = ?, age = ? where id = ?");
            prst.setString(1, student.getName());
            prst.setInt(2, student.getAge());
            prst.setLong(3, student.getId());
            int row =  prst.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean deleteById(long id)  {
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement(" delete from student where id = ?");
            prst.setLong(1, id);
            prst.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static Student getById(long id)  {
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement(" select id,name, age from student where id = ? ");
            prst.setLong(1, id);
            ResultSet resultSet = prst.executeQuery();
            Student student = new Student();
            if (resultSet != null){
                while(resultSet.next()){
                    student.setId(resultSet.getLong("id"));
                    student.setName(resultSet.getString("name"));
                    student.setAge(resultSet.getInt("age"));
                }
                return student;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
