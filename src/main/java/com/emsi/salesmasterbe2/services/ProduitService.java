package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;

public interface ProduitService {
    ProduitDao saveProduit(ProduitDao produitDao);
    ProduitDao getProduitById(Long id);
    PagedResponse<ProduitDao> getAllProduits(int page, int size);
    ProduitDao deleteProduit(Long id);
}
