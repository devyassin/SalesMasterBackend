package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.entities.Produit;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.ProduitRepository;
import com.emsi.salesmasterbe2.services.ProduitService;
import com.emsi.salesmasterbe2.utils.ApiServiceUtils;
import com.emsi.salesmasterbe2.utils.AppUtils;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private ApiServiceUtils apiServiceUtils;


    @Override
    public ProduitDao saveProduit(ProduitDao produitDao) {
        Produit produitEntity = ObjectMapperUtils.map(produitDao, Produit.class);
        produitEntity = produitRepository.save(produitEntity);
        return ObjectMapperUtils.map(produitEntity, ProduitDao.class);
    }

    @Override
    public ProduitDao getProduitById(Long id) {
        Optional<Produit> produitOptional = produitRepository.findById(id);
        if (produitOptional.isEmpty()) {
            throw new IllegalArgumentException("Produit with ID " + id + " not found");
        }
        return ObjectMapperUtils.map(produitOptional.get(), ProduitDao.class);
    }

    @Override
    public PagedResponse<ProduitDao> getAllProduits(int page, int size) {
        return apiServiceUtils.getAllEntities(page,size,produitRepository, ProduitDao.class);
    }

    @Override
    public ProduitDao deleteProduit(Long id) {
        Optional<Produit> produitOptional = produitRepository.findById(id);
        if (produitOptional.isPresent()) {
            produitRepository.deleteById(id);
            return ObjectMapperUtils.map(produitOptional.get(), ProduitDao.class);
        } else {
            throw new IllegalArgumentException("Produit with ID " + id + " not found");
        }
    }
}
