package paulo.spring.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import paulo.spring.domain.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class ClientController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/clients")
    public List<Client> list() {
        return manager.createQuery("from Client", Client.class).getResultList();
    }
}
