package lck.microservices.msclients.domain.exception;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String cpf){
        super(String.format("Client not found with cpf: %s.", cpf));
    }

}
