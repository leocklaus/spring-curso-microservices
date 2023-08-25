package lck.microservices.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lck.microservices.mscards.api.dto.CardEmissionRequestData;
import lck.microservices.mscards.domain.exception.InvalidCardIdException;
import lck.microservices.mscards.domain.model.ClientCard;
import lck.microservices.mscards.domain.repository.CardRepository;
import lck.microservices.mscards.domain.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardEmissionSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.card-emission}")
    public void receiveCardEmission(@Payload String payload){
        var mapper = new ObjectMapper();
        try{
            CardEmissionRequestData dataInput =  mapper.readValue(payload, CardEmissionRequestData.class);
            System.out.println(payload);
            System.out.println("imprime");
            System.out.println(dataInput);
            var card = cardRepository.findById(dataInput.getCardId()).orElseThrow(()-> new InvalidCardIdException());
            var clientCard = ClientCard.builder()
                    .card(card)
                    .cpf(dataInput.getCpf())
                    .cardLimit(dataInput.getLimit())
                    .build();
            clientCardRepository.save(clientCard);

        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

    }

}
