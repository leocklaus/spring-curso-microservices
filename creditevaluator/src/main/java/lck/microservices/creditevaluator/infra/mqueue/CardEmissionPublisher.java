package lck.microservices.creditevaluator.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lck.microservices.creditevaluator.api.dto.CardEmissionRequestData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardEmissionPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueCardEmission;

    public void EmitCard(CardEmissionRequestData data) throws JsonProcessingException {
        var json = convertToJson(data);
        System.out.println(data);
        System.out.println(json);
        rabbitTemplate.convertAndSend(queueCardEmission.getName(), json);
    }

    private String convertToJson(CardEmissionRequestData data) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(data);
        return json;
    }
}
