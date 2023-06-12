package com.rst.producer.controller;

import com.rst.producer.dto.ClientDTO;
import com.rst.producer.service.ClientGrpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grpc")
@RequiredArgsConstructor
public class ClientGrpcController {
    private final ClientGrpcService service;

    @PostMapping
    public String crateClient(@RequestBody ClientDTO clientDTO) {
        return service.crateClient(clientDTO);
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable("id") Integer id) {
        return service.getClientById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteClient(@PathVariable("id") Integer id) {
        return service.deleteClientById(id);
    }
}
