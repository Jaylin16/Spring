package com.example.spring;

import java.sql.*;

public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {

        //관심사 1. DB연결
        Connection c = getConnection();

        //관심사 2. 사용자 등록을 위한 SQL문장을 담을 statement 생성 및 실행
        PreparedStatement ps = c.prepareStatement("insert into `user`.users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        //관심사 3. 작업이 종료된 후 사용한 리소스를 닫아 시스템에 돌려줌
        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement("select * from `user`.users WHERE id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    //관심사 분리. '관심사 1 - DB연결'에 대한 기능을 리팩토링의 "메소드 추출 기법"을 통해 관심사 분리
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost", "유저이름", "비밀번호");

        return c;
    }

    //테스트용 main() 메소드 작성
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("test2");
        user.setName("me");
        user.setPassword("choco");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User userCheck = dao.get(user.getId());
        System.out.println(userCheck.getName());
        System.out.println(userCheck.getPassword());

        System.out.println(userCheck.getId() + "조회 성공");
    }
}
