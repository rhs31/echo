package com.stetsonhacks.echo.daos;


public class MessageDAOFactory {
    public static MessageDAO get(){
        return new FakeMessageDAOImpl();
    }
}
