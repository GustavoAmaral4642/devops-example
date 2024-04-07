package com.devops.example.devopsexample.controller;

import com.devops.example.devopsexample.domain.Client;
import com.devops.example.devopsexample.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> listClient(){
        return clientRepository.findAll();
    }

    @PostMapping(value = "/add")
    public Client addClient(@RequestBody Client client){
        return clientRepository.save(client);
    }

}
