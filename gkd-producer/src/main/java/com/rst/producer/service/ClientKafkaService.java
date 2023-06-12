package com.rst.producer.service;

import com.rst.producer.dto.ClientDTO;
import com.rst.producer.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientKafkaService {
    @Value("${app.topic.create}")
    private String createTopic;
    @Value("${app.topic.delete}")
    private String deleteTopic;

    private final ClientMapper mapper;
    private final KafkaTemplate<String, ClientDTO> kafkaTemplate;

    public void crateClient(ClientDTO clientDTO) {
        kafkaTemplate.send(createTopic, clientDTO);
        log.info("Send createClient request through kafka. Email: " + clientDTO.getEmail());
    }

    public void deleteClientByEmail(ClientDTO clientDTO) {
        kafkaTemplate.send(deleteTopic, clientDTO);
        log.info("Send deleteClient request through kafka. Email: " + clientDTO.getEmail());
    }
}
