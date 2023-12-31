package com.rst.producer.configuratiion;

import com.rst.producer.dto.ClientDTO;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServers;
    @Value("kfk.topic.create")
    private String createTopic;
    @Value("kfk.topic.delete")
    private String deleteTopic;

    @Bean
    NewTopic createClientTopic() {
        return TopicBuilder.name(createTopic).build();
    }

    @Bean
    NewTopic deleteClientTopic() {
        return TopicBuilder.name(deleteTopic).build();
    }

    @Bean
    KafkaTemplate<String, ClientDTO> createKafkaTemplate(ProducerFactory<String, ClientDTO> createProducerFactory) {
        return new KafkaTemplate<>(createProducerFactory);
    }

    @Bean
     ProducerFactory<String, ClientDTO> createProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    private Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }
}
