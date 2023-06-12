package com.rst.producer.configuratiion;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Value("${app.server.host}")
    private String host;

    @Bean
    ManagedChannel managedChannel() {
       return ManagedChannelBuilder.forTarget(host).usePlaintext().build();
    }
}
