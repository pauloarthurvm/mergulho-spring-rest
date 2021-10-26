package paulo.spring.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paulo.spring.domain.exception.ServiceException;
import paulo.spring.domain.model.Client;
import paulo.spring.domain.repository.ClientRepository;

@Service
public class ClientCatalogService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional  // This @ makes the method to executed inside a transaction
                    // If any error, it is NOT persisted in DB
    public Client addClient(Client client) {
        boolean emailNotAvailable = clientRepository.findByEmail(client.getEmail())
                .stream()
                .anyMatch(clientExist -> !clientExist.equals(client));

        if (emailNotAvailable) {
            throw new ServiceException("Client with email  already exist.");
        }

        return clientRepository.save(client);
    }

    @Transactional
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

}
