package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.LigneDeVenteDao;
import com.emsi.salesmasterbe2.daos.ProduitQauntiteDao;
import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.entities.LigneDeVente;
import com.emsi.salesmasterbe2.entities.Vente;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;

import java.util.List;

public interface LigneDeVenteService {

    List<LigneDeVente> saveLigneDeVentes(Vente venteEntity, VenteDao venteDao);

    LigneDeVenteDao getLigneDeVenteById(Long id);
    PagedResponse<LigneDeVenteDao> getAllLignesDeVente(int page, int size);
    LigneDeVenteDao deleteLigneDeVente(Long id);
}
