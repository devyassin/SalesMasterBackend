package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventes")
public class VenteController {

    private final VenteService venteService;

    @Autowired
    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @PostMapping
    public ResponseEntity<VenteDao> createVente(@RequestBody VenteDao venteDao) {
        VenteDao createdVente = venteService.saveVente(venteDao);
        return new ResponseEntity<>(createdVente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenteDao> getVenteById(@PathVariable Long id) {
        VenteDao venteDao = venteService.getVenteById(id);
        if (venteDao != null) {
            return new ResponseEntity<>(venteDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<VenteDao>> getAllVentes() {
        List<VenteDao> ventes = venteService.getAllVentes();
        return new ResponseEntity<>(ventes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVente(@PathVariable Long id) {
        venteService.deleteVente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
