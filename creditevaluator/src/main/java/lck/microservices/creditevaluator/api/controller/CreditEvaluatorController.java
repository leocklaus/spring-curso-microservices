package lck.microservices.creditevaluator.api.controller;

import lck.microservices.creditevaluator.api.dto.*;
import lck.microservices.creditevaluator.model.exception.CardEmissionRequestFailedException;
import lck.microservices.creditevaluator.model.exception.ClientNotFoundException;
import lck.microservices.creditevaluator.model.exception.ErrorOnMicroservicesCommunicationException;
import lck.microservices.creditevaluator.model.service.CreditEvaluatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-evaluator")
@RequiredArgsConstructor
public class CreditEvaluatorController {

    private final CreditEvaluatorService creditEvaluatorService;

    @GetMapping
    public String stats(){
        return "ok";
    }

    @GetMapping(value = "/client-situation", params = "cpf")
    public ResponseEntity<ClientSituationDTO> clientSituation(@RequestParam("cpf") String cpf){
        var clientSituation = creditEvaluatorService.getClientSituation(cpf);
        return ResponseEntity
                .ok(clientSituation);
    }

    @PostMapping
    public ResponseEntity<ClientEvaluationDTO> clientEvaluation(@RequestBody EvaluationDataInputDTO data){
        var clientEvaluationData = creditEvaluatorService.getClientEvaluation(data);
        return ResponseEntity
                .ok(clientEvaluationData);
    }

    @PostMapping("/request-card")
    public ResponseEntity<RequestProtocolDTO> requestCard(@RequestBody CardEmissionRequestData data){
        var protocol = creditEvaluatorService.requestCardEmission(data);
        return ResponseEntity.ok(protocol);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> clientNotFoundHandler(){
       return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(ErrorOnMicroservicesCommunicationException.class)
    public ResponseEntity<?> communicationErrorHandler(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(CardEmissionRequestFailedException.class)
    public ResponseEntity<?> cardEmissionFailedHandler(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
