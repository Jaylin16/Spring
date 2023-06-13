package com.example.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

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
        userDao.setDataSource(dataSource());
        return userDao;
    }

    //위의 bean정보를 xml로 변환시 아래와 같음.
//    <bean id="userDao" class="com.example.spring.UserDao">
//        <property name="dataSource" ref="dataSource" />
//    </bean>

//    @Bean
//    public ConnectionMaker connectionMaker() {
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        return connectionMaker;
//    }

    //위의 bean정보를 xml로 변환시 아래와 같음.
//    <bean id="connectionMaker" class="com.example.spring.DConnectionMaker" />

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost");
        dataSource.setUsername("유저이름");
        dataSource.setPassword("비밀번호");

        return dataSource;
    }

    //위의 bean정보를 xml로 변환시 아래와 같음.
//    <bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
//        <property name="driverClass" value="com.mysql.cj.jdbc.Driver" />
//        <property name="url" value="jdbc:mysql://localhost" />
//        <property name="username" value="유저이름" />
//        <property name="password" value="비밀번호" />
//    </bean>

}
