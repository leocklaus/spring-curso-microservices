package lck.microservices.creditevaluator.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientData {

    private Long id;
    private String cpf;
    private String name;
    private Integer age;

}
