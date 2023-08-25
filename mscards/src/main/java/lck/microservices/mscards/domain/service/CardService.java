package lck.microservices.mscards.domain.service;

import lck.microservices.mscards.api.dto.CardDTO;
import lck.microservices.mscards.domain.model.Card;
import lck.microservices.mscards.domain.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<CardDTO> getCardsByIncome(Long clientIncome){
        var income = BigDecimal.valueOf(clientIncome);
        List<Card> cards = cardRepository.findByIncomeLessThanEqual(income);

        return cards.stream()
                .map(CardDTO::new)
                .toList();
    }

    public CardDTO saveCard(CardDTO dto){
        var card = new Card(dto);
        card = cardRepository.save(card);
        return new CardDTO(card);
    }

}
