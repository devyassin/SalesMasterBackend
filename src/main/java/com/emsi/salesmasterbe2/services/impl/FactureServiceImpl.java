package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.FactureDao;
import com.emsi.salesmasterbe2.entities.Facture;
import com.emsi.salesmasterbe2.repository.FactureRepository;
import com.emsi.salesmasterbe2.services.FactureService;
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
        Facture factureEntity = convertToFactureEntity(factureDao);
        factureEntity = factureRepository.save(factureEntity);
        return convertToFactureDao(factureEntity);
    }

    @Override
    public FactureDao getFactureById(Long id) {
        Optional<Facture> factureOptional = factureRepository.findById(id);
        return factureOptional.map(this::convertToFactureDao).orElse(null);
    }

    @Override
    public List<FactureDao> getAllFactures() {
        List<Facture> factures = factureRepository.findAll();
        return factures.stream().map(this::convertToFactureDao).collect(Collectors.toList());
    }

    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }

    private Facture convertToFactureEntity(FactureDao factureDao) {
        Facture factureEntity = new Facture();
        factureEntity.setFactureId(factureDao.getFactureId());
        factureEntity.setDateFacturation(factureDao.getDateFacturation());
        factureEntity.setMontantTotal(factureDao.getMontantTotal());
        factureEntity.setStatutPaiement(factureDao.getStatutPaiement());
        return factureEntity;
    }

    private FactureDao convertToFactureDao(Facture factureEntity) {
        FactureDao factureDao = new FactureDao();
        factureDao.setFactureId(factureEntity.getFactureId());
        factureDao.setDateFacturation(factureEntity.getDateFacturation());
        factureDao.setMontantTotal(factureEntity.getMontantTotal());
        factureDao.setStatutPaiement(String.valueOf(factureEntity.getStatutPaiement()));
        return factureDao;
    }
}
