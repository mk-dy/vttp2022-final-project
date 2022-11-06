package vttp.csf.server.supportportal.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432000000; // 5 days expressed in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String ISAAC = "Isaac"; // the name of your application
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal"; // who is this token for?
    public static final String AUTHORITIES = "authorities"; // this is going to hold all of the authorities of the user
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page"; // use this to send a message to users who are forbidden to access this resource
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page"; // access denied message
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS"; // if the http method is not GET or POST or etc, if it is option, you want to let it go through
    public static final String[] PUBLIC_URLS = {
        "/user/login", 
        "/user/register", 
        "/user/resetpassword/**",
    }; // all of the urls that you want to allow without security

}
