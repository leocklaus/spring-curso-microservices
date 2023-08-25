package lck.microservices.creditevaluator.model.clients;

import lck.microservices.creditevaluator.api.dto.CardDTO;
import lck.microservices.creditevaluator.api.dto.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> getCardsByClientCpf(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    ResponseEntity<List<CardDTO>> getCardByIncome(@RequestParam("income") Long income);

}
