package com.example.spring;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

//    //인터페이스를 통한 오브젝트 접근.
//    //초기 설정 후 사용 중에는 바뀌지 않는 읽기 전용 인스턴스 변수.
//    private ConnectionMaker connectionMaker;

    private DataSource dataSource;

    //수정자 메소드 DI방식 사용
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {

        //관심사 1. DB연결
        //makeConnection 메소드는 인터페이스에 정의된 메소드를 사용하므로 클래스가 변경되도 상관없음.
        Connection c = dataSource.getConnection();

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

    public User get(String id) throws SQLException {
        Connection c = dataSource.getConnection();

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

//    //관심사 분리. '관심사 1 - DB연결'에 대한 기능을 리팩토링의 "메소드 추출 기법"을 통해 관심사 분리
//    private Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection c = DriverManager.getConnection("jdbc:mysql://localhost", "유저이름", "비밀번호");
//
//        return c;
//    }

//    //추상 메소드.
//    //DB 연결에 대한 기능을 각 상황에 맞게 적용할 수 있도록 추상 메소드화.
//    //템플릿 메소드 패턴 : 상속을 통해 슈퍼클래스의 기능을 확장할 때 사용하는 디자인 패턴.
//    protected abstract Connection getConnection() throws ClassNotFoundException, SQLException;

}