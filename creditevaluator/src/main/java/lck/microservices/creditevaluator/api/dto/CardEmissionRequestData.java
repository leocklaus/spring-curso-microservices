package lck.microservices.creditevaluator.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardEmissionRequestData {
    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal limit;
}
