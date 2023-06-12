package com.rst.producer.service;

import com.rst.consumer.ClientServiceGrpc;
import com.rst.consumer.ClientServiceOuterClass;
import com.rst.producer.dto.ClientDTO;
import com.rst.producer.mapper.ClientMapper;
import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientGrpcService {
    private final ManagedChannel channel;
    private final ClientMapper mapper;

    public String crateClient(ClientDTO clientDTO) {
        ClientServiceGrpc.ClientServiceBlockingStub stub = ClientServiceGrpc
                .newBlockingStub(channel);

        ClientServiceOuterClass.CreateClientRequest request = ClientServiceOuterClass.CreateClientRequest
                .newBuilder()
                .setUsername(clientDTO.getUsername())
                .setEmail(clientDTO.getEmail())
                .build();

        return "Client created with ID:" + stub.saveClient(request).getId();
    }

    public ClientDTO getClientById(int id) {
        ClientServiceGrpc.ClientServiceBlockingStub stub = ClientServiceGrpc
                .newBlockingStub(channel);

        ClientServiceOuterClass.ClientByIdRequest request = ClientServiceOuterClass.ClientByIdRequest
                .newBuilder()
                .setId(id)
                .build();

        return mapper.dtoFromClientResponse(stub.getClient(request));
    }

    public boolean deleteClientById(int id) {
        ClientServiceGrpc.ClientServiceBlockingStub stub = ClientServiceGrpc
                .newBlockingStub(channel);

        ClientServiceOuterClass.ClientByIdRequest request = ClientServiceOuterClass.ClientByIdRequest
                .newBuilder()
                .setId(id)
                .build();

        return stub.deleteClient(request).getSuccess();
    }
}
