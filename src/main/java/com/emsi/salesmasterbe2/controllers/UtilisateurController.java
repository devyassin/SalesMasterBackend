package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.UtilisateurDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.services.UtilisateurService;
import com.emsi.salesmasterbe2.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;


    @PostMapping
    public ResponseEntity<UtilisateurDao> createUtilisateur( @RequestBody UtilisateurDao utilisateurDao) {
        UtilisateurDao createdUtilisateur = utilisateurService.saveUtilisateur(utilisateurDao);
        return new ResponseEntity<>(createdUtilisateur, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDao> getUtilisateurById(@PathVariable Long id) {
        UtilisateurDao utilisateurDao = utilisateurService.getUtilisateurById(id);
        if (utilisateurDao != null) {
            return new ResponseEntity<>(utilisateurDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<PagedResponse<UtilisateurDao>> getAllUtilisateurs(@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                                                   @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        PagedResponse<UtilisateurDao> utilisateurs = utilisateurService.getAllUtilisateurs(page,size);
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UtilisateurDao> deleteUtilisateur(@PathVariable Long id) {
        UtilisateurDao utilisateurDao= utilisateurService.deleteUtilisateur(id);
        if (utilisateurDao != null) {
            return new ResponseEntity<>(utilisateurDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
