package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.LigneDeVenteDao;
import com.emsi.salesmasterbe2.daos.ProduitQauntiteDao;
import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.entities.LigneDeVente;
import com.emsi.salesmasterbe2.entities.Vente;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.LigneDeVenteRepository;
import com.emsi.salesmasterbe2.services.LigneDeVenteService;
import com.emsi.salesmasterbe2.utils.ApiServiceUtils;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class LigneDeVenteServiceImpl implements LigneDeVenteService {

    private final LigneDeVenteRepository ligneDeVenteRepository;
    private ApiServiceUtils apiServiceUtils;



    @Override
    public List<LigneDeVente> saveLigneDeVentes(Vente venteEntity, VenteDao venteDao) {
        List<LigneDeVenteDao> listLigneDeVente = new ArrayList<>();
        venteDao.getProduitQauntiteDao().forEach(produitQauntiteDao -> {
            LigneDeVenteDao ligneDeVenteDao = new LigneDeVenteDao();
            ligneDeVenteDao.setProduit(produitQauntiteDao.getProduit());
            ligneDeVenteDao.setQuantite(produitQauntiteDao.getQuantite());
            ligneDeVenteDao.setPrixUnitaire(produitQauntiteDao.getProduit().getPrix());
            listLigneDeVente.add(ligneDeVenteDao);
        });

        List<LigneDeVente> ligneDeVentes = ObjectMapperUtils.mapAll(listLigneDeVente, LigneDeVente.class);

        // Set the Vente entity on each LigneDeVente
        for (LigneDeVente ligneDeVente : ligneDeVentes) {
            ligneDeVente.setVente(venteEntity);
        }

        return ligneDeVentes;
    }

    @Override
    public LigneDeVenteDao getLigneDeVenteById(Long id) {
        Optional<LigneDeVente> ligneDeVenteOptional = ligneDeVenteRepository.findById(id);
        if (ligneDeVenteOptional.isEmpty()) {
            throw new IllegalArgumentException("Ligne de vente with ID " + id + " not found");
        }
        return ObjectMapperUtils.map(ligneDeVenteOptional.get(), LigneDeVenteDao.class);
    }

    @Override
    public PagedResponse<LigneDeVenteDao> getAllLignesDeVente(int page, int size) {
        return apiServiceUtils.
                getAllEntities(page,size,ligneDeVenteRepository, LigneDeVenteDao.class);
    }

    @Override
    public LigneDeVenteDao deleteLigneDeVente(Long id) {
        Optional<LigneDeVente> ligneDeVenteOptional = ligneDeVenteRepository.findById(id);

        if (ligneDeVenteOptional.isPresent()) {
            System.out.println(ligneDeVenteOptional.isPresent() +"truuuue ligne de vente");
            ligneDeVenteRepository.deleteById(id);
            return ObjectMapperUtils.map(ligneDeVenteOptional.get(), LigneDeVenteDao.class);
        } else {
            throw new IllegalArgumentException("Ligne de vente with ID " + id + " not found");
        }
    }
}
