package lck.microservices.mscards.api.controller;

import com.netflix.discovery.converters.Auto;
import lck.microservices.mscards.api.dto.CardDTO;
import lck.microservices.mscards.api.dto.ClientCardDTO;
import lck.microservices.mscards.domain.exception.InvalidCardIdException;
import lck.microservices.mscards.domain.service.CardService;
import lck.microservices.mscards.domain.service.ClientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private ClientCardService clientCardService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<CardDTO>> getCardByIncome(@RequestParam("income") Long income){
        List<CardDTO> cards = cardService.getCardsByIncome(income);

        return ResponseEntity
                .ok(cards);
    }

    @PostMapping
    public ResponseEntity<?> saveCard(@RequestBody CardDTO cardDTO){
        cardDTO = cardService.saveCard(cardDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(cardDTO.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClientCardDTO>> getCardsByClientCpf(@RequestParam("cpf") String cpf){
        var cards = clientCardService.getCardsByClientCpf(cpf);
        return ResponseEntity.ok(cards);
    }

    @ExceptionHandler(InvalidCardIdException.class)
    public ResponseEntity<?> invalidCardIdExceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
