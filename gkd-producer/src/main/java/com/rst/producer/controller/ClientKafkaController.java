package com.rst.producer.controller;

import com.rst.producer.dto.ClientDTO;
import com.rst.producer.service.ClientKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class ClientKafkaController {
    private final ClientKafkaService service;

    @PostMapping
    public void crateClient(@RequestBody ClientDTO clientDTO) {
        service.crateClient(clientDTO);
    }

    @DeleteMapping
    public void deleteClientByEmail(@RequestBody ClientDTO clientDTO) {
        service.deleteClientByEmail(clientDTO);
    }
}
