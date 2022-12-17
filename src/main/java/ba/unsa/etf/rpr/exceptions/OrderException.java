package ba.unsa.etf.rpr.exceptions;

public class OrderException extends Exception{
    public OrderException(String message, Exception reason){
        super(message,reason);
    }
    public OrderException(String message){
        super(message);
    }
}
