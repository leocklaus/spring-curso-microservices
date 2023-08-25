package lck.microservices.mscards.api.dto;

import lck.microservices.mscards.domain.model.Card;
import lck.microservices.mscards.domain.model.CardBrand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private Long id;
    private String name;
    private CardBrand cardBrand;
    private BigDecimal income;
    private BigDecimal limit;

    public CardDTO(Card card){
        this.id = card.getId();
        this.name = card.getName();
        this.cardBrand = card.getCardBrand();
        this.income = card.getIncome();
        this.limit = card.getCardLimit();
    }

}
