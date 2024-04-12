package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.FactureDao;
import com.emsi.salesmasterbe2.entities.Facture;
import com.emsi.salesmasterbe2.repository.FactureRepository;
import com.emsi.salesmasterbe2.services.FactureService;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;

    @Autowired
    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public FactureDao saveFacture(FactureDao factureDao) {
        Facture factureEntity = ObjectMapperUtils.map(factureDao,Facture.class);
        factureEntity = factureRepository.save(factureEntity);
        return ObjectMapperUtils.map(factureEntity,FactureDao.class);
    }

    @Override
    public FactureDao getFactureById(Long id) {
        Optional<Facture> factureOptional = factureRepository.findById(id);
        return ObjectMapperUtils.map(factureOptional.get(),FactureDao.class);
    }

    @Override
    public List<FactureDao> getAllFactures() {
        List<Facture> factures = factureRepository.findAll();
        return ObjectMapperUtils.mapAll(factures,FactureDao.class);
    }

    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }


}
