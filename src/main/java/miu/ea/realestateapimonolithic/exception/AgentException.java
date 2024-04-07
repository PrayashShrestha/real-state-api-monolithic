package miu.ea.realestateapimonolithic.exception;

public class AgentException extends Exception{

    private String message;

    public AgentException(String message){
        super(message);
    }
}
