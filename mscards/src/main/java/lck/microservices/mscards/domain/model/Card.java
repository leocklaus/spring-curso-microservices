package lck.microservices.mscards.domain.model;

import jakarta.persistence.*;
import lck.microservices.mscards.api.dto.CardDTO;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;

    private BigDecimal income;

    private BigDecimal cardLimit;

    public Card(CardDTO dto){
        this.id = dto.getId();
        this.name = dto.getName();
        this.cardBrand = dto.getCardBrand();
        this.income = dto.getIncome();
        this.cardLimit = dto.getLimit();
    }

}
