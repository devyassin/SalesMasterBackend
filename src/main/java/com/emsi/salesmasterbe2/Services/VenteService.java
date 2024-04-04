package com.emsi.salesmasterbe2.Services;
import com.emsi.salesmasterbe2.Entities.Vente;
import com.emsi.salesmasterbe2.Repository.VenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VenteService {


    private VenteRepository venteRepository;

    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    public Vente getVenteById(Long id) {
        return venteRepository.findById(id).orElse(null);
    }

    public Vente saveVente(Vente vente) {
        return venteRepository.save(vente);
    }

    public void deleteVente(Long id) {
        venteRepository.deleteById(id);
    }
}
