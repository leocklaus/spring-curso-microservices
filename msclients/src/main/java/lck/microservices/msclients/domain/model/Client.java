package lck.microservices.msclients.domain.model;

import jakarta.persistence.*;
import lck.microservices.msclients.api.dto.ClientDTO;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;

    public Client(ClientDTO dto){
        this.id = dto.getId();
        this.cpf = dto.getCpf();
        this.name = dto.getName();
        this.age = dto.getAge();
    }
}
