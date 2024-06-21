package com.emsi.salesmasterbe2.repository;


import com.emsi.salesmasterbe2.entities.Statut;
import com.emsi.salesmasterbe2.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
    @Query("SELECT COUNT(v) FROM Vente v WHERE v.statut = :statut")
    long countByStatut(@Param("statut") Statut statut);
}
