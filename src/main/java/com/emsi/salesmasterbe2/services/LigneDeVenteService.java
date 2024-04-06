package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.entities.LigneDeVente;

import java.util.List;

public interface LigneDeVenteService {

    List<LigneDeVente> getAllLignesDeVente();

    LigneDeVente getLigneDeVenteById(Long id);

    LigneDeVente saveLigneDeVente(LigneDeVente ligneDeVente);

    void deleteLigneDeVente(Long id);
}
