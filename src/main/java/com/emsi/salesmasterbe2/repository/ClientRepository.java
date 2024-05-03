package com.emsi.salesmasterbe2.repository;

import com.emsi.salesmasterbe2.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findByNomContains(String name,Pageable pageable);
    boolean existsClientByEmail(String email);
}
