package com.example.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        UserDao dao = context.getBean("userDao", UserDao.class);

        //User Dao 사용 코드
        User user = new User();
        user.setId("test");
        user.setName("me");
        user.setPassword("choco");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User userCheck = dao.get(user.getId());
        System.out.println(userCheck.getName());
        System.out.println(userCheck.getPassword());

        System.out.println(userCheck.getId() + "조회 성공");

        //Connection 횟수를 카운트 해주는 부가 기능 추가.
        CountingConnectionMaker countingConnectionMaker = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection counter = " + countingConnectionMaker.getCounter());
    }
}
