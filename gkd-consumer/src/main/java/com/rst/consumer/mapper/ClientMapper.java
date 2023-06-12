package com.rst.consumer.mapper;

import com.rst.consumer.ClientServiceOuterClass;
import com.rst.consumer.dto.ClientDTO;
import com.rst.consumer.entity.Client;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(ClientMapperDecorator.class)
public interface ClientMapper {
    Client toEntity(ClientServiceOuterClass.CreateClientRequest request);
    Client toEntityFromDto(ClientDTO clientDTO);
}
