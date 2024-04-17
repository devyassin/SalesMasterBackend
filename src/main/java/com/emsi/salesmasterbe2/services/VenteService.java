package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;

public interface VenteService {
    VenteDao saveVente(VenteDao venteDao);
    VenteDao getVenteById(Long id);
    PagedResponse<VenteDao> getAllVentes(int page, int size);
    VenteDao deleteVente(Long id);
}
