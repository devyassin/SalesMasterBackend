package com.emsi.salesmasterbe2.Repository;


import com.emsi.salesmasterbe2.Entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
}
