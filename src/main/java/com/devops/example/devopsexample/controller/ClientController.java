package com.devops.example.devopsexample.controller;

import com.devops.example.devopsexample.domain.Client;
import com.devops.example.devopsexample.repository.ClientRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Gauge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
@RequestMapping
public class ClientController {

    private final MeterRegistry meterRegistry;

    @Autowired
    private ClientRepository clientRepository;

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    public ClientController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping(value="/clients")
    public List<Client> listClient(){
        return clientRepository.findAll();
    }

    @PostMapping(value = "/clients/add")
    public Client addClient(@RequestBody Client client){
        return clientRepository.save(client);
    }

    @GetMapping(value = "/medias")
    public ResponseEntity<?> getMedias(){
        IntStream.range(0,110).forEach(value -> {
            int valor = new Random().nextInt(130);
            Counter counter = Counter.builder("media_idade_clientes_cadastrados")
                    .tag("media_idade", "media")
                    .description("Media da idade de clientes cadastrados")
                    .register(meterRegistry);
            if(valor >= 120){
                logger.error("A idade é invalida: " +  valor);
                counter.increment();
            }else {
                logger.info("Idade é: " +  valor);
            }
            logger.info(counter.getId().toString());
        });
        return ResponseEntity.ok("OK");
    }

    @GetMapping( value ="/valorVendaDiaria" )
    public ResponseEntity valorVendaDiaria(){
        Gauge.builder("valorVendaDiaria", () -> new Random().nextFloat(3000))
                .description("Valor de venda diária")
                .register(meterRegistry);
        return ResponseEntity.ok("OK");
    }
}
