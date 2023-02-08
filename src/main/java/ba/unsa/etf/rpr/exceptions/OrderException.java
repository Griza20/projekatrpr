package ba.unsa.etf.rpr.exceptions;

/**
 * Custom made exception which has been throwed from other classes
 * during some unexpected problems
 */
public class OrderException extends Exception{
    public OrderException(String message, Exception reason){
        super(message,reason);
    }
    public OrderException(String message){
        super(message);
    }
}
