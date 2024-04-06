package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.entities.Produit;

import java.util.List;

public interface ProduitService {
    List<Produit> getAllProduits();

    Produit getProduitById(Long id);

    Produit saveProduit(Produit produit);

    void deleteProduit(Long id);
}
