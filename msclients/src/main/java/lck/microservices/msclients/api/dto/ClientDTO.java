package lck.microservices.msclients.api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lck.microservices.msclients.domain.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;

    private String cpf;

    private String name;

    private Integer age;

    public ClientDTO(Client client){
        this.id = client.getId();
        this.cpf = client.getCpf();
        this.name = client.getName();
        this.age = client.getAge();
    }
}
