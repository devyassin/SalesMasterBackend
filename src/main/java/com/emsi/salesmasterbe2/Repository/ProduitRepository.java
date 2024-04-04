package com.emsi.salesmasterbe2.Repository;


import com.emsi.salesmasterbe2.Entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
