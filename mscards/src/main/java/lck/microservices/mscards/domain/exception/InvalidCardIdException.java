package lck.microservices.mscards.domain.exception;

public class InvalidCardIdException extends RuntimeException{
    public InvalidCardIdException(){
        super("Id do cartão inválido");
    }
}
