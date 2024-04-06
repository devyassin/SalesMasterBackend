package com.emsi.salesmasterbe2.repository;


import com.emsi.salesmasterbe2.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
}
