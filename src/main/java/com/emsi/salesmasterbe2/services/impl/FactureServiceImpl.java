package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.daos.FactureDao;
import com.emsi.salesmasterbe2.entities.Facture;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.FactureRepository;
import com.emsi.salesmasterbe2.services.FactureService;
import com.emsi.salesmasterbe2.utils.ApiServiceUtils;
import com.emsi.salesmasterbe2.utils.AppConstants;
import com.emsi.salesmasterbe2.utils.AppUtils;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;
    private ApiServiceUtils apiServiceUtils;



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
        return apiServiceUtils.getAllEntities(page,size,factureRepository, FactureDao.class);
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
