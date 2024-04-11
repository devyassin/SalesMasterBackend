package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.VenteDao;

import java.util.List;

public interface VenteService {
    VenteDao saveVente(VenteDao venteDao);
    VenteDao getVenteById(Long id);
    List<VenteDao> getAllVentes();
    void deleteVente(Long id);
}
