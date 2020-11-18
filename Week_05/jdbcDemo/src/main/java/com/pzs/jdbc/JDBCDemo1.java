package com.pzs.jdbc;


import java.sql.*;

public class JDBCDemo1 {
    public static Connection connection;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test";
        connection = DriverManager.getConnection(url, "root", "root");
        Student student = null;
//        //新增
//        student = new Student();
//        student.setName("李四");
//        student.setAge(25);
//        int row = save(student);
//        System.out.println(row);
        //删除
//        boolean b = deleteById(3);
//        System.out.println(b);

        //查询
        student = getById(4);
        System.out.println(student);

        //修改
        student.setName("王五");
        update(student);
        // 5. 释放资源
        connection.close();

    }

    public static int save(Student student)  {
        Statement stat = null;
        try {
            stat = connection.createStatement();
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO student(name,age) ");
            sql.append(" values ( ");
            sql.append(" '" + student.getName() +"', ");
            sql.append(student.getAge());
            sql.append(")");
            int row =  stat.executeUpdate(sql.toString());
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    public static int update(Student student)  {
        Statement stat = null;
        try {
            stat = connection.createStatement();
            StringBuffer sql = new StringBuffer();
            sql.append("update student ");
            sql.append(" set name = '"+ student.getName() +"', ");
            sql.append(" age = " + student.getAge());
            sql.append(" where id = ").append(student.getId());
            System.out.println(sql
            .toString());
            int row =  stat.executeUpdate(sql.toString());
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean deleteById(long id)  {
        Statement stat = null;
        try {
            stat = connection.createStatement();
            StringBuffer sql = new StringBuffer();
            sql.append(" delete from student ");
            sql.append(" where id = ").append(id);
            stat.execute(sql.toString());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static Student getById(long id)  {
        Statement stat = null;
        try {
            stat = connection.createStatement();
            StringBuffer sql = new StringBuffer();
            sql.append(" select id,name, age from student ");
            sql.append(" where id = ").append(id);
            ResultSet resultSet = stat.executeQuery(sql.toString());
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
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
