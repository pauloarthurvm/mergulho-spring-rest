package paulo.spring.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import paulo.spring.domain.model.Client;
import paulo.spring.domain.repository.ClientRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController {

    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<Client> list() {
        return clientRepository.findAll();
    }
}
