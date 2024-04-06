package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.entities.Facture;

import java.util.List;

public interface FactureService {
    List<Facture> getAllFactures();

    Facture getFactureById(Long id);

    Facture saveFacture(Facture facture);

    void deleteFacture(Long id);
}
