package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.payload.response.VenteResponse;
import com.emsi.salesmasterbe2.payload.response.VenteResponseDetails;
import com.emsi.salesmasterbe2.payload.response.VenteResponseTable;

public interface VenteService {
    VenteDao saveVente(VenteDao venteDao);
    VenteResponseDetails getVenteById(Long id);
    PagedResponse<VenteResponseTable> getAllVentes(int page, int size);
    VenteDao deleteVente(Long id);
}
