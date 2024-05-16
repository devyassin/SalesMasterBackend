package com.emsi.salesmasterbe2.repository;


import com.emsi.salesmasterbe2.entities.Client;
import com.emsi.salesmasterbe2.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    Page<Produit> findByNomContains(String name, Pageable pageable);
}
