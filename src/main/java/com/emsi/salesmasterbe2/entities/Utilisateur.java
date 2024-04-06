package com.emsi.salesmasterbe2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UtilisateurID;
    @NotBlank(message = "Nom est obligatoire")
    @Size(max = 50, message = "Nom ne peut pas dépasser 50 caractères")
    @Column(unique = true)
    private String Nom;
    @Email(message = "Email invalide")
    @NotBlank(message = "Email est obligatoire")
    @Column(unique = true)
    private String Email;
    @NotBlank(message = "Mot de passe est obligatoire")
//    @Min(value = 8,message = "Mot de passe doit contenir au moins 8 caractères")
    private String MotDePasse;
    @Enumerated(EnumType.STRING)
   // @NotBlank(message = "Rôle est obligatoire")
    private Role role;
}
