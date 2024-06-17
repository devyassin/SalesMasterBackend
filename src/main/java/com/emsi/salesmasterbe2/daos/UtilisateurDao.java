package com.emsi.salesmasterbe2.daos;

import com.emsi.salesmasterbe2.entities.Role;
import lombok.*;

@Getter
@Setter
@ToString
@Builder @AllArgsConstructor @NoArgsConstructor
public class UtilisateurDao {

    private String nom;
    private String email;
    private String motDePasse;
    private Role role;

}
