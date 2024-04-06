package com.emsi.salesmasterbe2.repository;


import com.emsi.salesmasterbe2.entities.LigneDeVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface LigneDeVenteRepository extends JpaRepository<LigneDeVente, Long> {
}
