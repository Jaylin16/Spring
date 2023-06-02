package com.example.spring;

public class DaoFactory {
    public UserDao userDao() {

        //UserDao타입의 오브젝트를 어떻게 만들고 준비시켜 반환할지를 책임짐.
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }
}
