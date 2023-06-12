package com.rst.consumer.repository;

import com.rst.consumer.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    void deleteByEmail(String email);
}
