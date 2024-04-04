package com.emsi.salesmasterbe2.Repository;

import com.emsi.salesmasterbe2.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
}
