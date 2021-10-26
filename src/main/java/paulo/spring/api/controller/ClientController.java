package paulo.spring.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulo.spring.domain.model.Client;
import paulo.spring.domain.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private ClientRepository clientRepository;

    @GetMapping()
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable Long clientId) {
        return clientRepository.findById(clientId)
                .map(client -> ResponseEntity.ok(client))
                .orElse(ResponseEntity.notFound().build());

//        Optional<Client> client = clientRepository.findById(clientId);
//        if (client.isPresent()) {
//            return ResponseEntity.ok(client.get());
//        }
//        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody Client newClient) {
        return clientRepository.save(newClient);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> update(@PathVariable Long clientId, @RequestBody Client client) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }
        client.setId(clientId);
        client = clientRepository.save(client);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }
        clientRepository.deleteById(clientId);
        return ResponseEntity.noContent().build();
    }
}
