package com.rst.consumer.controller;

import com.rst.consumer.entity.Client;
import com.rst.consumer.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ClientController {
    private final ClientRepository repository;

    @GetMapping
    public List<Client> getClient() {
        return repository.findAll();
    }
}
