package com.emsi.salesmasterbe2.Repository;


import com.emsi.salesmasterbe2.Entities.LigneDeVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface LigneDeVenteRepository extends JpaRepository<LigneDeVente, Long> {
}
