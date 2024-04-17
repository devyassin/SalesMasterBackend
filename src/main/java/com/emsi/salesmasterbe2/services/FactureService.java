package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.FactureDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;

public interface FactureService {
    FactureDao saveFacture(FactureDao factureDao);
    FactureDao getFactureById(Long id);
    PagedResponse<FactureDao> getAllFactures(int page, int size);
    FactureDao deleteFacture(Long id);
}
