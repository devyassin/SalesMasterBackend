package com.emsi.salesmasterbe2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Entity
@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
@Table(name = "clients")
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ClientID;
    @NotBlank(message = "Nom est obligatoire")
    @Size(max = 50, message = "Nom ne peut pas dépasser 50 caractères")
    private String Nom;
    @Email(message = "Email invalide")
    @NotBlank(message = "Email est obligatoire")
    @Column(unique = true)
    private String Email;
    @NotBlank(message = "adress est obligatoire")
    @Column(unique = true)
    private String Adresse;
    @NotBlank(message = "Téléphone est obligatoire")
//    @Pattern(regexp = "\\d{10}", message = "Téléphone doit contenir 10 chiffres")
    private String Telephone;
    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private List<Vente> HistoriqueAchats;

}
