package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.entities.Produit;
import com.emsi.salesmasterbe2.repository.ProduitRepository;
import com.emsi.salesmasterbe2.services.ProduitService;
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
        Produit produitEntity = convertToProduitEntity(produitDao);
        produitEntity = produitRepository.save(produitEntity);
        return convertToProduitDao(produitEntity);
    }

    @Override
    public ProduitDao getProduitById(Long id) {
        Optional<Produit> produitOptional = produitRepository.findById(id);
        return produitOptional.map(this::convertToProduitDao).orElse(null);
    }

    @Override
    public List<ProduitDao> getAllProduits() {
        List<Produit> produits = produitRepository.findAll();
        return produits.stream().map(this::convertToProduitDao).collect(Collectors.toList());
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    private Produit convertToProduitEntity(ProduitDao produitDao) {
        Produit produitEntity = new Produit();
        produitEntity.setProduitId(produitDao.getProduitId());
        produitEntity.setNom(produitDao.getNom());
        produitEntity.setDescription(produitDao.getDescription());
        produitEntity.setPrix(produitDao.getPrix());
        produitEntity.setQuantitéEnStock(produitDao.getQuantiteEnStock());
        return produitEntity;
    }

    private ProduitDao convertToProduitDao(Produit produitEntity) {
        ProduitDao produitDao = new ProduitDao();
        produitDao.setProduitId(produitEntity.getProduitId());
        produitDao.setNom(produitEntity.getNom());
        produitDao.setDescription(produitEntity.getDescription());
        produitDao.setPrix(produitEntity.getPrix());
        produitDao.setQuantiteEnStock(produitEntity.getQuantitéEnStock());
        return produitDao;
    }
}
