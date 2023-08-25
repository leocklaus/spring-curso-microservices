package lck.microservices.mscards.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardEmissionRequestData {
    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal limit;
}
