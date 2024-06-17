package com.emsi.salesmasterbe2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long utilisateurID;
    @NotBlank(message = "Nom est obligatoire")
    @Size(max = 50, message = "Nom ne peut pas dépasser 50 caractères")
    @Column(unique = true)
    private String nom;
    @Email(message = "Email invalide")
    @NotBlank(message = "Email est obligatoire")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Mot de passe est obligatoire")
//    @Min(value = 8,message = "Mot de passe doit contenir au moins 8 caractères")
    private String motDePasse;
    @Enumerated(EnumType.STRING)
   // @NotBlank(message = "Rôle est obligatoire")
    private Role role;
}
