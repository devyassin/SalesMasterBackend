package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.entities.Vente;

import java.util.List;

public interface VenteService {
    List<Vente> getAllVentes();

    Vente getVenteById(Long id);

    Vente saveVente(Vente vente);

    void deleteVente(Long id);
}
