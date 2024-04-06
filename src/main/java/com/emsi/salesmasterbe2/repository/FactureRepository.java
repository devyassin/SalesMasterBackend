package com.emsi.salesmasterbe2.repository;

import com.emsi.salesmasterbe2.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
}
