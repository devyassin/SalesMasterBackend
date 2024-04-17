package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.FactureDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.services.FactureService;
import com.emsi.salesmasterbe2.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/factures")
public class FactureController {

    private final FactureService factureService;

    @PostMapping
    public ResponseEntity<FactureDao> createFacture(@RequestBody FactureDao factureDao) {
        FactureDao createdFacture = factureService.saveFacture(factureDao);
        return new ResponseEntity<>(createdFacture, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureDao> getFactureById(@PathVariable Long id) {
        FactureDao factureDao = factureService.getFactureById(id);
        if (factureDao != null) {
            return new ResponseEntity<>(factureDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<PagedResponse<FactureDao>> getAllFactures(@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                                                    @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        PagedResponse<FactureDao> factures = factureService.getAllFactures(page, size);
        return new ResponseEntity<>(factures, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FactureDao> deleteFacture(@PathVariable Long id) {
        FactureDao factureDao = factureService.deleteFacture(id);
        if (factureDao != null) {
            return new ResponseEntity<>(factureDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
