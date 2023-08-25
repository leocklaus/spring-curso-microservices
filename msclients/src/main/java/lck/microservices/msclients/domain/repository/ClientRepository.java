package lck.microservices.msclients.domain.repository;

import jakarta.annotation.Resource;
import lck.microservices.msclients.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Resource
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCpf(String cpf);
}
