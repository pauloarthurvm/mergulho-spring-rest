package paulo.spring.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import paulo.spring.domain.model.Client;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    @GetMapping("/clients")
    public List<Client> list() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("Name");
        client1.setEmail("email@email.com");
        client1.setNumber("41 98769876");

        Client client2 = new Client();
        client2.setId(1L);
        client2.setName("Maria");
        client2.setEmail("maria@email.com");
        client2.setNumber("41 67678989");

        return Arrays.asList(client1, client2);
    }

}
