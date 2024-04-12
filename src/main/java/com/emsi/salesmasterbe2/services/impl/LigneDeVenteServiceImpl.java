package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.LigneDeVenteDao;
import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.entities.LigneDeVente;
import com.emsi.salesmasterbe2.entities.Produit;
import com.emsi.salesmasterbe2.entities.Vente;
import com.emsi.salesmasterbe2.repository.LigneDeVenteRepository;
import com.emsi.salesmasterbe2.services.LigneDeVenteService;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
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
        LigneDeVente ligneDeVenteEntity = ObjectMapperUtils.map(ligneDeVenteDao,LigneDeVente.class);
        ligneDeVenteEntity = ligneDeVenteRepository.save(ligneDeVenteEntity);
        return ObjectMapperUtils.map(ligneDeVenteEntity,LigneDeVenteDao.class);
    }

    @Override
    public LigneDeVenteDao getLigneDeVenteById(Long id) {
        Optional<LigneDeVente> ligneDeVenteOptional = ligneDeVenteRepository.findById(id);
        return ObjectMapperUtils.map(ligneDeVenteOptional.get(),LigneDeVenteDao.class);
    }

    @Override
    public List<LigneDeVenteDao> getAllLignesDeVente() {
        List<LigneDeVente> lignesDeVente = ligneDeVenteRepository.findAll();
        return ObjectMapperUtils.mapAll(lignesDeVente,LigneDeVenteDao.class);
    }

    @Override
    public void deleteLigneDeVente(Long id) {
        ligneDeVenteRepository.deleteById(id);
    }


}
