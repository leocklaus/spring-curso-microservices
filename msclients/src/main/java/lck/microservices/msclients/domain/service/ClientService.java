package lck.microservices.msclients.domain.service;

import jakarta.transaction.Transactional;
import lck.microservices.msclients.domain.exception.ClientNotFoundException;
import lck.microservices.msclients.domain.model.Client;
import lck.microservices.msclients.domain.repository.ClientRepository;
import lck.microservices.msclients.api.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public ClientDTO saveClient(ClientDTO clientDTO) {
        var client = new Client(clientDTO);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO getClientByCPF(String cpf) {
        var client = clientRepository.findByCpf(cpf)
                .orElseThrow(()-> new ClientNotFoundException(cpf));
        return new ClientDTO(client);
    }
}
