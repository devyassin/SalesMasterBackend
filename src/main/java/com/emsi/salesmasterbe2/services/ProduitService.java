package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.ProduitDao;

import java.util.List;

public interface ProduitService {
    ProduitDao saveProduit(ProduitDao produitDao);
    ProduitDao getProduitById(Long id);
    List<ProduitDao> getAllProduits();
    void deleteProduit(Long id);
}
