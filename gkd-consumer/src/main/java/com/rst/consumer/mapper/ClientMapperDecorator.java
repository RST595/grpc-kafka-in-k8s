package com.rst.consumer.mapper;

import com.rst.consumer.ClientServiceOuterClass;
import com.rst.consumer.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class ClientMapperDecorator implements ClientMapper {
    @Autowired
    @Qualifier("delegate")
    private ClientMapper delegate;

    @Override
    public Client toEntity(ClientServiceOuterClass.CreateClientRequest request) {
        Client client = delegate.toEntity(request);

        if (client == null) {
            return null;
        }

        client.setUsername(request.getUsername());
        client.setEmail(request.getEmail());

        return client;
    }
}
