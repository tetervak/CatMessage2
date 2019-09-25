package ca.javateacher.catmessage2;

public class Constants {

    // used in save instance logic
    public static final String IS_MESSAGE_RECEIVED_KEY = "urgent";

    // used to pass data from InputActivity to MainActivity and to save the instance
    public static final String IS_URGENT_KEY = "urgent";
    public static final String MESSAGE_TEXT_KEY = "message";

    // the request code, used in starting InputActivity for result, and receiving the result
    static final int GET_MESSAGE_REQUEST = 0;
}
