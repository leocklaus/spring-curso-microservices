package lck.microservices.creditevaluator.model.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(){
        super("Cliente não encontrado com o CPF informado");
    }
}
