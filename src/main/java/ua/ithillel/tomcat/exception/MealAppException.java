package ua.ithillel.tomcat.exception;

public class MealAppException extends Exception {

    public MealAppException(String message) {
        super(message);
    }

    public MealAppException(String message, Throwable cause) {
        super(message, cause);
    }
}
