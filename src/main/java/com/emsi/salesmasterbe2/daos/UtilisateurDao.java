package com.emsi.salesmasterbe2.daos;

import com.emsi.salesmasterbe2.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UtilisateurDao {
    private Long utilisateurId;
    private String nom;
    private String email;
    private String motDePasse;
    private Role role;
}
