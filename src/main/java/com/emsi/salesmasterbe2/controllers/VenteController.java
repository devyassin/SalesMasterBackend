package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.payload.response.VenteResponse;
import com.emsi.salesmasterbe2.payload.response.VenteResponseDetails;
import com.emsi.salesmasterbe2.payload.response.VenteResponseTable;
import com.emsi.salesmasterbe2.services.VenteService;
import com.emsi.salesmasterbe2.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/ventes")
public class VenteController {

    private final VenteService venteService;



    @PostMapping
    public ResponseEntity<VenteDao> createVente(@RequestBody VenteDao venteDao) {
        VenteDao createdVente = venteService.saveVente(venteDao);
        return new ResponseEntity<>(createdVente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenteResponseDetails> getVenteById(@PathVariable Long id) {
        VenteResponseDetails venteResponseDetails = venteService.getVenteById(id);
        if (venteResponseDetails != null) {
            return new ResponseEntity<>(venteResponseDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<PagedResponse<VenteResponseTable>> getAllVentes(@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                                                          @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        PagedResponse<VenteResponseTable> ventes = venteService.getAllVentes(page, size);
        return new ResponseEntity<>(ventes, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VenteDao> updateVente(@PathVariable Long id, @RequestBody VenteDao venteDao) {
        VenteDao updatedVente = venteService.updateVente(id, venteDao);
        return new ResponseEntity<>(updatedVente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VenteDao> deleteVente(@PathVariable Long id) {
        VenteDao venteDao = venteService.deleteVente(id);
        if (venteDao != null) {
            return new ResponseEntity<>(venteDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
