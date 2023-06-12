package com.rst.producer.mapper;

import com.rst.consumer.ClientServiceOuterClass;
import com.rst.producer.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientMapper {
    @Mapping(target = "username", expression = "java(clientResponse.getUsername())")
    @Mapping(target = "email", expression = "java(clientResponse.getEmail())")
    ClientDTO dtoFromClientResponse(ClientServiceOuterClass.ClientResponse clientResponse);
}
