package com.example.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {

    //테스트용 main() 메소드 작성
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        //UserDao가 사용할 connectionMaker의 구현 클래스를 결정하여 오브젝트 생성.
//        //즉, 실제 기능을 실행할 클라이언트 클래스에서 connectionMaker의 구현 클래스를 지정해줌.
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//
//        //지정된 connectionMaker을 받아서 UserDao를 생성.
//        UserDao dao = new UserDao(connectionMaker);

        //DaoFactory를 설정정보로 사용하는 애플리케이션 컨텍스트 오브젝트 생성.
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("test3");
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
