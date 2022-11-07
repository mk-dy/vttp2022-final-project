package vttp.csf.server.exceptions;

public class UserException extends Exception {
    
    private String msg;

    public UserException(String message) {
        super(message);
    }

    public String getMsg() {
        return msg;
    }
}
