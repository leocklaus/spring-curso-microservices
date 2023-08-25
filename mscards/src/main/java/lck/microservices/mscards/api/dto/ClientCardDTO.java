package lck.microservices.mscards.api.dto;

import lck.microservices.mscards.domain.model.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientCardDTO {
    private String name;
    private String brand;
    private BigDecimal cardLimit;

    public ClientCardDTO(ClientCard clientCard){
        this.name = clientCard.getCard().getName();
        this.brand = clientCard.getCard().getCardBrand().toString();
        this.cardLimit = clientCard.getCardLimit();
    }
}
