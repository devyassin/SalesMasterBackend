package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.LigneDeVenteDao;
import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.entities.LigneDeVente;
import com.emsi.salesmasterbe2.entities.Produit;
import com.emsi.salesmasterbe2.entities.Vente;
import com.emsi.salesmasterbe2.repository.LigneDeVenteRepository;
import com.emsi.salesmasterbe2.services.LigneDeVenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LigneDeVenteServiceImpl implements LigneDeVenteService {

    private final LigneDeVenteRepository ligneDeVenteRepository;

    @Autowired
    public LigneDeVenteServiceImpl(LigneDeVenteRepository ligneDeVenteRepository) {
        this.ligneDeVenteRepository = ligneDeVenteRepository;
    }

    @Override
    public LigneDeVenteDao saveLigneDeVente(LigneDeVenteDao ligneDeVenteDao) {
        LigneDeVente ligneDeVenteEntity = convertToLigneDeVenteEntity(ligneDeVenteDao);
        ligneDeVenteEntity = ligneDeVenteRepository.save(ligneDeVenteEntity);
        return convertToLigneDeVenteDao(ligneDeVenteEntity);
    }

    @Override
    public LigneDeVenteDao getLigneDeVenteById(Long id) {
        Optional<LigneDeVente> ligneDeVenteOptional = ligneDeVenteRepository.findById(id);
        return ligneDeVenteOptional.map(this::convertToLigneDeVenteDao).orElse(null);
    }

    @Override
    public List<LigneDeVenteDao> getAllLignesDeVente() {
        List<LigneDeVente> lignesDeVente = ligneDeVenteRepository.findAll();
        return lignesDeVente.stream().map(this::convertToLigneDeVenteDao).collect(Collectors.toList());
    }

    @Override
    public void deleteLigneDeVente(Long id) {
        ligneDeVenteRepository.deleteById(id);
    }

    private LigneDeVente convertToLigneDeVenteEntity(LigneDeVenteDao ligneDeVenteDao) {
        LigneDeVente ligneDeVenteEntity = new LigneDeVente();
        ligneDeVenteEntity.setLigneDeVenteId(ligneDeVenteDao.getLigneDeVenteId());
        ligneDeVenteEntity.setVente(convertToVenteEntity(ligneDeVenteDao.getVente()));
        ligneDeVenteEntity.setProduit(convertToProduitEntity(ligneDeVenteDao.getProduit()));
        ligneDeVenteEntity.setQuantité(ligneDeVenteDao.getQuantite());
        ligneDeVenteEntity.setPrixUnitaire(ligneDeVenteDao.getPrixUnitaire());
        return ligneDeVenteEntity;
    }

    private LigneDeVenteDao convertToLigneDeVenteDao(LigneDeVente ligneDeVenteEntity) {
        LigneDeVenteDao ligneDeVenteDao = new LigneDeVenteDao();
        ligneDeVenteDao.setLigneDeVenteId(ligneDeVenteEntity.getLigneDeVenteId());
        ligneDeVenteDao.setVente(convertToVenteDao(ligneDeVenteEntity.getVente()));
        ligneDeVenteDao.setProduit(convertToProduitDao(ligneDeVenteEntity.getProduit()));
        ligneDeVenteDao.setQuantite(ligneDeVenteEntity.getQuantité());
        ligneDeVenteDao.setPrixUnitaire(ligneDeVenteEntity.getPrixUnitaire());
        return ligneDeVenteDao;
    }

    private Vente convertToVenteEntity(VenteDao venteDao) {
        Vente venteEntity = new Vente();
        venteEntity.setVenteId(venteDao.getVenteId());
        venteEntity.setDateVente(venteDao.getDateVente());
        venteEntity.setStatut(venteDao.getStatut());
        venteEntity.setTotal(venteDao.getTotal());
        return venteEntity;
    }

    private Produit convertToProduitEntity(ProduitDao produitDao) {
        Produit produitEntity = new Produit();
        produitEntity.setProduitId(produitDao.getProduitId());
        produitEntity.setNom(produitDao.getNom());
        produitEntity.setDescription(produitDao.getDescription());
        produitEntity.setPrix(produitDao.getPrix());
        produitEntity.setQuantitéEnStock(produitDao.getQuantiteEnStock());
        produitEntity.setImage(produitDao.getImage());
        return produitEntity;
    }
    private VenteDao convertToVenteDao(Vente venteEntity) {
        VenteDao venteDao = new VenteDao();
        venteDao.setVenteId(venteEntity.getVenteId());
        venteDao.setDateVente(venteEntity.getDateVente());
        venteDao.setStatut(String.valueOf(venteEntity.getStatut()));
        venteDao.setTotal(venteEntity.getTotal());
        return venteDao;
    }
    private ProduitDao convertToProduitDao(Produit produitEntity) {
        ProduitDao produitDao = new ProduitDao();
        produitDao.setProduitId(produitEntity.getProduitId());
        produitDao.setNom(produitEntity.getNom());
        produitDao.setDescription(produitEntity.getDescription());
        produitDao.setPrix(produitEntity.getPrix());
        produitDao.setQuantiteEnStock(produitEntity.getQuantitéEnStock());
        produitDao.setImage(produitEntity.getImage());
        return produitDao;
    }




}
