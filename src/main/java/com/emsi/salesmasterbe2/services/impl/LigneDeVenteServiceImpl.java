package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.daos.LigneDeVenteDao;
import com.emsi.salesmasterbe2.entities.LigneDeVente;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.LigneDeVenteRepository;
import com.emsi.salesmasterbe2.services.LigneDeVenteService;
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
public class LigneDeVenteServiceImpl implements LigneDeVenteService {

    private final LigneDeVenteRepository ligneDeVenteRepository;
    private ApiServiceUtils apiServiceUtils;



    @Override
    public LigneDeVenteDao saveLigneDeVente(LigneDeVenteDao ligneDeVenteDao) {
        LigneDeVente ligneDeVenteEntity = ObjectMapperUtils.map(ligneDeVenteDao, LigneDeVente.class);
        ligneDeVenteEntity = ligneDeVenteRepository.save(ligneDeVenteEntity);
        return ObjectMapperUtils.map(ligneDeVenteEntity, LigneDeVenteDao.class);
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
            ligneDeVenteRepository.deleteById(id);
            return ObjectMapperUtils.map(ligneDeVenteOptional.get(), LigneDeVenteDao.class);
        } else {
            throw new IllegalArgumentException("Ligne de vente with ID " + id + " not found");
        }
    }
}
