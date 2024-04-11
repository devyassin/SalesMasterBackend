package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.LigneDeVenteDao;

import java.util.List;

public interface LigneDeVenteService {
    LigneDeVenteDao saveLigneDeVente(LigneDeVenteDao ligneDeVenteDao);
    LigneDeVenteDao getLigneDeVenteById(Long id);
    List<LigneDeVenteDao> getAllLignesDeVente();
    void deleteLigneDeVente(Long id);
}
