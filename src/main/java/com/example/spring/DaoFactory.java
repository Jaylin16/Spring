package com.example.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

//애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정 정보라는 표시.
@Configuration
public class DaoFactory {
    //오브젝트 생성을 담당하는 IoC용 메소드라는 표시.
//    @Bean
//    public UserDao userDao() {
//
//        //UserDao타입의 오브젝트를 어떻게 만들고 준비시켜 반환할지를 책임짐.
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//        return userDao;
//    }

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        return connectionMaker;
    }
}
