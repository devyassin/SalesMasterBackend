package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.entities.Vente;
import com.emsi.salesmasterbe2.repository.VenteRepository;
import com.emsi.salesmasterbe2.services.VenteService;
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
        Vente venteEntity = convertToVenteEntity(venteDao);
        venteEntity = venteRepository.save(venteEntity);
        return convertToVenteDao(venteEntity);
    }

    @Override
    public VenteDao getVenteById(Long id) {
        Optional<Vente> venteOptional = venteRepository.findById(id);
        return venteOptional.map(this::convertToVenteDao).orElse(null);
    }

    @Override
    public List<VenteDao> getAllVentes() {
        List<Vente> ventes = venteRepository.findAll();
        return ventes.stream().map(this::convertToVenteDao).collect(Collectors.toList());
    }

    @Override
    public void deleteVente(Long id) {
        venteRepository.deleteById(id);
    }

    private Vente convertToVenteEntity(VenteDao venteDao) {
        Vente venteEntity = new Vente();
        venteEntity.setVenteId(venteDao.getVenteId());
        venteEntity.setDateVente(venteDao.getDateVente());
        venteEntity.setStatut(venteDao.getStatut());
        venteEntity.setTotal(venteDao.getTotal());
        return venteEntity;
    }

    private VenteDao convertToVenteDao(Vente venteEntity) {
        VenteDao venteDao = new VenteDao();
        venteDao.setVenteId(venteEntity.getVenteId());
        venteDao.setDateVente(venteEntity.getDateVente());
        venteDao.setStatut(String.valueOf(venteEntity.getStatut()));
        venteDao.setTotal(venteEntity.getTotal());
        return venteDao;
    }
}
