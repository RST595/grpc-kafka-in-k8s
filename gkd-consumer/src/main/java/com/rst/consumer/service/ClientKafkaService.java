package com.rst.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rst.consumer.dto.ClientDTO;
import com.rst.consumer.mapper.ClientMapper;
import com.rst.consumer.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientKafkaService {
    private final ClientRepository repository;
    private final ClientMapper clientMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "createTopic")
    @Transactional
    public void saveClient(String data) throws JsonProcessingException {
        ClientDTO client = objectMapper.readValue(data, ClientDTO.class);
        repository.save(clientMapper.toEntityFromDto(client));
        log.info("Client with email: " + client.getEmail() + " saved. Kafka");
    }

    @KafkaListener(topics = "deleteTopic")
    @Transactional
    public void deleteClient(String data) throws JsonProcessingException {
        ClientDTO client = objectMapper.readValue(data, ClientDTO.class);
        String clientEmail = client.getEmail();
        repository.deleteByEmail(clientEmail);
        log.info("Client with email: " + clientEmail + " deleted. Kafka");
    }
}
