package apiEngine;

public class Route {


    private static final String BOOKSTORE = "/BookStore";
    private static final String ACCOUNT = "/Account";
    private static final String VERSION = "/v1";
    private static final String VER = "/api";


    public static String generateToken(){
        return ACCOUNT + VERSION + "/GenerateToken";
    }

    public static String authorized(){
        return ACCOUNT + VERSION + "/Authorized";
    }

    public static String books(){
        return BOOKSTORE + VERSION + "/Books";
    }

    public static String book(){
        return BOOKSTORE + VERSION + "/Book";
    }

    public static String userAccount(String userId){
        return ACCOUNT + VERSION + "/User" + "/" + userId;
    }

    public static String login(){return VER + "/login";}


}
