package lck.microservices.creditevaluator.model.exception;

public class ErrorOnMicroservicesCommunicationException extends RuntimeException{
    public ErrorOnMicroservicesCommunicationException(){
        super("Erro na comunicação com os microserviços");
    }

}
