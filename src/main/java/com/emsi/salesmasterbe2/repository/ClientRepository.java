package com.emsi.salesmasterbe2.repository;

import com.emsi.salesmasterbe2.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
