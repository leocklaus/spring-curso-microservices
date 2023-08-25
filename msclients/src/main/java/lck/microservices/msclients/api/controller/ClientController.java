package lck.microservices.msclients.api.controller;

import lck.microservices.msclients.domain.exception.ClientNotFoundException;
import lck.microservices.msclients.domain.service.ClientService;
import lck.microservices.msclients.api.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RequestMapping("/clients")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;


    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody ClientDTO client){

            client = clientService.saveClient(client);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(client.getId()).toUri();

            return ResponseEntity
                    .created(uri)
                    .build();

    }

    @GetMapping
    public ResponseEntity<ClientDTO> getClientByCpf(@RequestParam String cpf){
        var clientDTO = clientService.getClientByCPF(cpf);
        return ResponseEntity
                .ok(clientDTO);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> clientNotFound(){
        return ResponseEntity
                .notFound()
                .build();
    }
}
