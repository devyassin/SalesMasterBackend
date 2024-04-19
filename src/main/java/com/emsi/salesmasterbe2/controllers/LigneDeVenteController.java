package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.LigneDeVenteDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.services.LigneDeVenteService;
import com.emsi.salesmasterbe2.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/lignes-de-vente")
public class LigneDeVenteController {

    private final LigneDeVenteService ligneDeVenteService;

    @PostMapping
    public ResponseEntity<LigneDeVenteDao> createLigneDeVente(@RequestBody LigneDeVenteDao ligneDeVenteDao) {
        LigneDeVenteDao createdLigneDeVente = ligneDeVenteService.saveLigneDeVente(ligneDeVenteDao);
        return new ResponseEntity<>(createdLigneDeVente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneDeVenteDao> getLigneDeVenteById(@PathVariable Long id) {
        LigneDeVenteDao ligneDeVenteDao = ligneDeVenteService.getLigneDeVenteById(id);
        if (ligneDeVenteDao != null) {
            return new ResponseEntity<>(ligneDeVenteDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<PagedResponse<LigneDeVenteDao>> getAllLignesDeVente(@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                                                              @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        PagedResponse<LigneDeVenteDao> lignesDeVente = ligneDeVenteService.getAllLignesDeVente(page, size);
        return new ResponseEntity<>(lignesDeVente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LigneDeVenteDao> deleteLigneDeVente(@PathVariable Long id) {
        LigneDeVenteDao ligneDeVenteDao = ligneDeVenteService.deleteLigneDeVente(id);
        if (ligneDeVenteDao != null) {
            return new ResponseEntity<>(ligneDeVenteDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
