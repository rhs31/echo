package com.stetsonhacks.echo.daos;


public class MessageDAOFactory {
    private static MessageDAO messageDAO = null;
    public static MessageDAO get(){
        if(messageDAO == null)
            messageDAO =new FakeMessageDAOImpl();
        return messageDAO;
    }
}
