package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.FactureDao;
import com.emsi.salesmasterbe2.entities.Facture;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.FactureRepository;
import com.emsi.salesmasterbe2.services.FactureService;
import com.emsi.salesmasterbe2.utils.AppConstants;
import com.emsi.salesmasterbe2.utils.AppUtils;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;

    @Autowired
    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public FactureDao saveFacture(FactureDao factureDao) {
        Facture factureEntity = ObjectMapperUtils.map(factureDao, Facture.class);
        factureEntity = factureRepository.save(factureEntity);
        return ObjectMapperUtils.map(factureEntity, FactureDao.class);
    }

    @Override
    public FactureDao getFactureById(Long id) {
        Optional<Facture> factureOptional = factureRepository.findById(id);
        if (factureOptional.isEmpty()) {
            throw new IllegalArgumentException("Facture with ID " + id + " not found");
        }
        return ObjectMapperUtils.map(factureOptional.get(), FactureDao.class);
    }

    @Override
    public PagedResponse<FactureDao> getAllFactures(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Facture> facturesPage = factureRepository.findAll(pageable);
        return new PagedResponse<>(
                ObjectMapperUtils.mapAll(facturesPage.getContent(), FactureDao.class),
                page,
                size,
                facturesPage.getNumberOfElements(),
                facturesPage.getTotalPages()
        );
    }

    @Override
    public FactureDao deleteFacture(Long id) {
        Optional<Facture> factureOptional = factureRepository.findById(id);
        if (factureOptional.isPresent()) {
            factureRepository.deleteById(id);
            return ObjectMapperUtils.map(factureOptional.get(), FactureDao.class);
        } else {
            throw new IllegalArgumentException("Facture with ID " + id + " not found");
        }
    }
}
