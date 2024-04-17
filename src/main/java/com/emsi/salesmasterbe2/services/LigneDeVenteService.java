package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.LigneDeVenteDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;

public interface LigneDeVenteService {
    LigneDeVenteDao saveLigneDeVente(LigneDeVenteDao ligneDeVenteDao);
    LigneDeVenteDao getLigneDeVenteById(Long id);
    PagedResponse<LigneDeVenteDao> getAllLignesDeVente(int page, int size);
    LigneDeVenteDao deleteLigneDeVente(Long id);
}
