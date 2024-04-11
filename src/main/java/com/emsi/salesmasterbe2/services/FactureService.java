package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.FactureDao;

import java.util.List;

public interface FactureService {
    FactureDao saveFacture(FactureDao factureDao);
    FactureDao getFactureById(Long id);
    List<FactureDao> getAllFactures();
    void deleteFacture(Long id);
}
