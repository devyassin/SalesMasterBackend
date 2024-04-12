package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.entities.Vente;
import com.emsi.salesmasterbe2.repository.VenteRepository;
import com.emsi.salesmasterbe2.services.VenteService;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenteServiceImpl implements VenteService {

    private final VenteRepository venteRepository;

    @Autowired
    public VenteServiceImpl(VenteRepository venteRepository) {
        this.venteRepository = venteRepository;
    }

    @Override
    public VenteDao saveVente(VenteDao venteDao) {
        Vente venteEntity = ObjectMapperUtils.map(venteDao, Vente.class);
        venteEntity = venteRepository.save(venteEntity);
        return ObjectMapperUtils.map(venteEntity, VenteDao.class);
    }

    @Override
    public VenteDao getVenteById(Long id) {
        Optional<Vente> venteOptional = venteRepository.findById(id);
        return ObjectMapperUtils.map(venteOptional.get(), VenteDao.class);
    }

    @Override
    public List<VenteDao> getAllVentes() {
        List<Vente> ventes = venteRepository.findAll();
        return ObjectMapperUtils.mapAll(ventes, VenteDao.class);
    }

    @Override
    public void deleteVente(Long id) {
        venteRepository.deleteById(id);
    }

}
