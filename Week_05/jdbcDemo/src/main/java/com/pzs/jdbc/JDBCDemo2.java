package com.pzs.jdbc;


import java.sql.*;

public class JDBCDemo2 {
    public static Connection connection;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test";
        connection = DriverManager.getConnection(url, "root", "root");
        Student student = null;
//        //新增
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
        // 5. 释放资源
        connection.close();

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
