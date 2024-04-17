package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.entities.Vente;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.VenteRepository;
import com.emsi.salesmasterbe2.services.VenteService;
import com.emsi.salesmasterbe2.utils.AppUtils;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if (venteOptional.isEmpty()) {
            throw new IllegalArgumentException("Vente with ID " + id + " not found");
        }
        return ObjectMapperUtils.map(venteOptional.get(), VenteDao.class);
    }

    @Override
    public PagedResponse<VenteDao> getAllVentes(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Vente> ventesPage = venteRepository.findAll(pageable);
        return new PagedResponse<>(
                ObjectMapperUtils.mapAll(ventesPage.getContent(), VenteDao.class),
                page,
                size,
                ventesPage.getNumberOfElements(),
                ventesPage.getTotalPages()
        );
    }

    @Override
    public VenteDao deleteVente(Long id) {
        Optional<Vente> venteOptional = venteRepository.findById(id);
        if (venteOptional.isPresent()) {
            venteRepository.deleteById(id);
            return ObjectMapperUtils.map(venteOptional.get(), VenteDao.class);
        } else {
            throw new IllegalArgumentException("Vente with ID " + id + " not found");
        }
    }
}
