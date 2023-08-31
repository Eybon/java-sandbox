package fr.forge.sandbox.java.sandbox.controller;

import fr.forge.sandbox.java.sandbox.client.ClientService;
import fr.forge.sandbox.java.sandbox.model.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormationController {

    @Autowired
    ClientService clientService;

    @GetMapping("/api/formation/springboot-loadbalancer")
    public Formation getFormation() {
        return clientService.appelRestClient();
    }
}
