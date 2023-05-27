package com.example.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//상속을 통해 getConnection() 메소드 정의 2.
public class DUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost", "유저이름", "비밀번호");

        return c;
    }
}
