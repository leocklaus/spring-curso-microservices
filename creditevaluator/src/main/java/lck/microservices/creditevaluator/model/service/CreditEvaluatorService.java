package lck.microservices.creditevaluator.model.service;

import feign.FeignException;
import lck.microservices.creditevaluator.api.dto.*;
import lck.microservices.creditevaluator.infra.mqueue.CardEmissionPublisher;
import lck.microservices.creditevaluator.model.clients.CardResourceClient;
import lck.microservices.creditevaluator.model.clients.ClientResourceClient;
import lck.microservices.creditevaluator.model.exception.CardEmissionRequestFailedException;
import lck.microservices.creditevaluator.model.exception.ClientNotFoundException;
import lck.microservices.creditevaluator.model.exception.ErrorOnMicroservicesCommunicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientResourceClient clientResourceClient;
    private final CardResourceClient cardResourceClient;
    private final CardEmissionPublisher cardEmissionPublisher;

    public ClientSituationDTO getClientSituation(String cpf){

        try{
            var clientData = clientResourceClient.getClientByCpf(cpf);
            var clientCards = cardResourceClient.getCardsByClientCpf(cpf);

            return ClientSituationDTO.builder()
                    .clientData(clientData.getBody())
                    .clientCards(clientCards.getBody())
                    .build();
        }catch (Exception e){
            if(e instanceof FeignException.FeignClientException){
                if(((FeignException.FeignClientException) e).status() == HttpStatus.NOT_FOUND.value()){
                    throw new ClientNotFoundException();
                }
            }

            throw new ErrorOnMicroservicesCommunicationException();
        }


    }

    public ClientEvaluationDTO getClientEvaluation(EvaluationDataInputDTO data){
        try{
            var clientData = clientResourceClient.getClientByCpf(data.getCpf()).getBody();
            var approvedCards = cardResourceClient.getCardByIncome(data.getIncome()).getBody();

            List<ApprovedCard> clientCards = approvedCards.stream()
                    .map(card -> {
                        var cardLimit = calculateCardLimit(card.getLimit(), clientData.getAge());
                        var approvedCard = new ApprovedCard(card.getName(), card.getCardBrand(), cardLimit);
                        return approvedCard;
                    }).toList();

            var clientEvaluation = ClientEvaluationDTO.builder()
                    .approvedCards(clientCards)
                    .build();

            return clientEvaluation;


        }catch (Exception e){
            if(e instanceof FeignException.FeignClientException){
                if(((FeignException.FeignClientException) e).status() == HttpStatus.NOT_FOUND.value()){
                    throw new ClientNotFoundException();
                }
            }

            throw new ErrorOnMicroservicesCommunicationException();
        }
    }

    public RequestProtocolDTO requestCardEmission (CardEmissionRequestData data){
        var protocol = generateProtocol();

        try{
            cardEmissionPublisher.EmitCard(data);
        } catch (Exception e){
            throw new CardEmissionRequestFailedException(e.getMessage());
        }

        return protocol;
    }

    private BigDecimal calculateCardLimit(BigDecimal baseLimit, Integer age) {
        BigDecimal factor = BigDecimal.valueOf(age).divide(BigDecimal.TEN);
        BigDecimal cardLimit = baseLimit.multiply(factor);
        return cardLimit;
    }

    private RequestProtocolDTO generateProtocol(){
        var protocol =  UUID.randomUUID().toString();
        var requestProtocol = new RequestProtocolDTO(protocol);
        return requestProtocol;
    }

}
