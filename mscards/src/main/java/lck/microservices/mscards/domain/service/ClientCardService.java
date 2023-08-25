package lck.microservices.mscards.domain.service;

import lck.microservices.mscards.api.dto.ClientCardDTO;
import lck.microservices.mscards.domain.model.ClientCard;
import lck.microservices.mscards.domain.repository.ClientCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientCardService {

    @Autowired
    private ClientCardRepository clientCardRepository;

    public List<ClientCardDTO> getCardsByClientCpf(String cpf){
        List<ClientCard> cards = clientCardRepository.findByCpf(cpf);
        return cards.stream()
                .map(ClientCardDTO::new)
                .toList();
    }

}
