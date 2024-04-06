package com.emsi.salesmasterbe2.services.impl;
import com.emsi.salesmasterbe2.entities.Vente;
import com.emsi.salesmasterbe2.repository.VenteRepository;
import com.emsi.salesmasterbe2.services.VenteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VenteServiceImpl implements VenteService {


    private VenteRepository venteRepository;

    @Override
    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    @Override
    public Vente getVenteById(Long id) {
        return venteRepository.findById(id).orElse(null);
    }

    @Override
    public Vente saveVente(Vente vente) {
        return venteRepository.save(vente);
    }

    @Override
    public void deleteVente(Long id) {
        venteRepository.deleteById(id);
    }
}
