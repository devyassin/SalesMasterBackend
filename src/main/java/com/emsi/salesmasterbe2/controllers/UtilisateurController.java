package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.UtilisateurDao;
import com.emsi.salesmasterbe2.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

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
    public ResponseEntity<List<UtilisateurDao>> getAllUtilisateurs() {
        List<UtilisateurDao> utilisateurs = utilisateurService.getAllUtilisateurs();
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
