package com.rst.consumer.service;

import com.rst.consumer.ClientServiceGrpc;
import com.rst.consumer.entity.Client;
import com.rst.consumer.mapper.ClientMapper;
import com.rst.consumer.repository.ClientRepository;
import org.lognet.springboot.grpc.GRpcService;

import java.util.Optional;

import static com.rst.consumer.ClientServiceOuterClass.ClientByIdRequest;
import static com.rst.consumer.ClientServiceOuterClass.ClientResponse;
import static com.rst.consumer.ClientServiceOuterClass.CreateClientRequest;
import static com.rst.consumer.ClientServiceOuterClass.DeleteClientByIdResponse;

@GRpcService
public class ClientGrpcService extends ClientServiceGrpc.ClientServiceImplBase {
    private final ClientRepository repository;
    private final ClientMapper clientMapper;

    public ClientGrpcService(ClientRepository repository, ClientMapper clientMapper) {
        this.repository = repository;
        this.clientMapper = clientMapper;
    }

    @Override
    public void saveClient(CreateClientRequest request,
                           io.grpc.stub.StreamObserver<ClientResponse> responseObserver) {

        Client entity = clientMapper.toEntity(request);
        repository.save(entity);

        ClientResponse clientResponse = ClientResponse.newBuilder()
                .setEmail(request.getEmail())
                .setUsername(request.getUsername())
                .setId(entity.getId().intValue())
                .build();

        responseObserver.onNext(clientResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getClient(ClientByIdRequest request,
                          io.grpc.stub.StreamObserver<ClientResponse> responseObserver) {
        Optional<Client> clientOptional = repository.findById((long) request.getId());

        ClientResponse clientResponse;
        if (clientOptional.isEmpty()) {
            clientResponse = ClientResponse.newBuilder().build();
        } else {
            Client client = clientOptional.get();
            clientResponse = ClientResponse.newBuilder()
                    .setEmail(client.getEmail())
                    .setUsername(client.getUsername())
                    .setId(client.getId().intValue())
                    .build();
        }

        responseObserver.onNext(clientResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteClient(ClientByIdRequest request,
                             io.grpc.stub.StreamObserver<DeleteClientByIdResponse> responseObserver) {

        repository.deleteById((long) request.getId());
        DeleteClientByIdResponse clientResponse = DeleteClientByIdResponse.newBuilder()
                .setSuccess(true)
                .build();

        responseObserver.onNext(clientResponse);
        responseObserver.onCompleted();
    }
}
