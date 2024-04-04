package com.emsi.salesmasterbe2.Repository;

import com.emsi.salesmasterbe2.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
