package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.entities.Produit;
import com.emsi.salesmasterbe2.repository.ProduitRepository;
import com.emsi.salesmasterbe2.services.ProduitService;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public ProduitDao saveProduit(ProduitDao produitDao) {
        Produit produitEntity = ObjectMapperUtils.map(produitDao, Produit.class);
        produitEntity = produitRepository.save(produitEntity);
        return ObjectMapperUtils.map(produitEntity, ProduitDao.class);
    }

    @Override
    public ProduitDao getProduitById(Long id) {
        Optional<Produit> produitOptional = produitRepository.findById(id);
        return ObjectMapperUtils.map(produitOptional.get(), ProduitDao.class);
    }

    @Override
    public List<ProduitDao> getAllProduits() {
        List<Produit> produits = produitRepository.findAll();
        return ObjectMapperUtils.mapAll(produits,ProduitDao.class);
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

}
