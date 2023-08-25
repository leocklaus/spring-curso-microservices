package lck.microservices.creditevaluator.core.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.card-emission}")
    private String cardEmissionQueue;

    @Bean
    public Queue queueCardEmission(){
        return new Queue(cardEmissionQueue, true);
    }

}
