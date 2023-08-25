package lck.microservices.creditevaluator.model.exception;

public class CardEmissionRequestFailedException extends RuntimeException{
    public CardEmissionRequestFailedException(String msg){super(msg);}
}
